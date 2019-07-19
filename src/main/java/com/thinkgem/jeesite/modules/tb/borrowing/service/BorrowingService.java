package com.thinkgem.jeesite.modules.tb.borrowing.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.borrowing.dao.BorrowingDao;
import com.thinkgem.jeesite.modules.tb.borrowing.entity.Borrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;
@Service
public class BorrowingService extends CrudService<BorrowingDao, Borrowing>{
	@Autowired
	private ActTaskService actTaskService;
	
	public Page<Borrowing> findEmployeesPage(Page<Borrowing> page, Borrowing borrowing) {
		borrowing.setPage(page);
		page.setList(dao.findEmployeesList(borrowing));
		return page;
	}
	@Override
	@Transactional(readOnly=false)
	public void save(Borrowing borrowing){
		if(StringUtils.isBlank(borrowing.getId())){//提交申请
			borrowing.preInsert();
			borrowing.setStatu("审核中");
			dao.insert(borrowing);
			startGet(borrowing);			
		}else{
			borrowing.preUpdate();
			dao.update(borrowing);
			if ("yes".equals(borrowing.getAct().getFlag())) {
				borrowing.getAct().setComment("[重申]" + borrowing.getTitle());
				borrowing.setStatu("审核中");
				dao.updateStatu(borrowing);
			} else {
				borrowing.getAct().setComment("[销毁]" + borrowing.getAct().getComment());
				borrowing.setStatu("销毁");
				dao.updateStatu(borrowing);
				dao.delete(borrowing);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("money", borrowing.getMoney());
			vars.put("rebut", "yes".equals(borrowing.getAct().getFlag()) ? "1" : "0");
			String taskId = dao.selectTaskIdByProcinsId(borrowing.getAct().getProcInsId());
			actTaskService.complete(taskId, borrowing.getAct().getProcInsId(),
					borrowing.getAct().getComment(), borrowing.getTitle(), vars);
		}
	}
	@Transactional(readOnly=false)
	public void auditSave(Borrowing borrowing){
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛财务总监");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛总经理");
		Boolean thd_d = Arrays.asList(roles).contains("桃花岛出纳");
		Map<String, Object> vars = Maps.newHashMap();
		
		borrowing.getAct().setComment(("yes".equals(borrowing.getAct().getFlag()) ? "[同意]" : "[驳回]") + borrowing.getAct().getComment());
		borrowing.preUpdate();
		/**流程正常走向 同意则agree==1*/
		vars.put("agree", "yes".equals(borrowing.getAct().getFlag()) ? "1" : "0");
		// 各个审核环节
		//主管
		if (thd_a&&!thd_c) {
			borrowing.setPrthreeText(borrowing.getAct().getComment());
			dao.updatePrthreeText(borrowing);
		}
		//财务总监
		if (thd_b) {
			borrowing.setPrsixText(borrowing.getAct().getComment());
			dao.updatePrsixText(borrowing);
		}
		//总经理
		if (thd_c) {
			borrowing.setPrfourText(borrowing.getAct().getComment());
			dao.updatePrfourText(borrowing);
		}
		//出纳
		if (thd_d) {
			borrowing.setPrsixText(borrowing.getAct().getComment());
			dao.updatePrsixText(borrowing);
		}
		if(!"yes".equals(borrowing.getAct().getFlag())){
			borrowing.setStatu("驳回");
			dao.updateStatu(borrowing);
		}
		if("yes".equals(borrowing.getAct().getFlag())&&thd_b){
			borrowing.setStatu("审核通过");
			dao.updateStatu(borrowing);
		}
		if("yes".equals(borrowing.getAct().getFlag())&&thd_c){
			borrowing.setStatu("同意付款");
			dao.updateStatu(borrowing);
		}
		if("yes".equals(borrowing.getAct().getFlag())&&thd_d){
			borrowing.setStatu("已付款");
			dao.updateStatu(borrowing);
			vars.put("agree", "yes".equals(borrowing.getAct().getFlag()) ? "3" : "0");
		}
		actTaskService.complete(borrowing.getAct().getTaskId(), borrowing.getAct().getProcInsId(),
				borrowing.getAct().getComment(), vars);
	}
	
	public void startGet(Borrowing borrowing){
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("money", borrowing.getMoney());
		
		/**
		 * @update Mr.dong
		 * 借款申请流程思路：
		 * 1.如果提交人直属上级是总裁：
		 * 则：提交人--财务总监--总经理（俞林伟）---出纳
		 * 2.如果直属上级是办事处级别：
		 * 则：提交人--直属上级--财务总监--总经理  ---出纳
		 * 3.如果直属上级是部门级别负责人： 
		 * 则：提交人--直属上级的上级--财务总监--总经理 ---出纳
		 */
		
		/**获取当前人的直属上级*/
		String leaderId = UserUtils.getUser().getLeader();//直属上级id
		String leaderName = UserUtils.get(leaderId).getName();//直属上级姓名
		/**获取直属上级的角色*/
		String roleStr = UserUtils.get(leaderId).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");//
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛总裁");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛部门级别负责人");
		
		if(thd_a){
			vars.put("userTask1", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_general_manager").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_chuna").get(0));
		}else if(thd_b){
			vars.put("userTask1", leaderName);
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_general_manager").get(0));
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_chuna").get(0));
		}else if(thd_c){
			/**获取上级的上级领导姓名*/
			String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
			vars.put("userTask1", leader_leader);
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_general_manager").get(0));
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_chuna").get(0));
		}else{
			return;
		}
		actTaskService.startProcess(ActUtils.THD_BORROWING[0], ActUtils.THD_BORROWING[1], borrowing.getId(),
				borrowing.getTitle(), vars);
	}
	public Borrowing getByProcInsId(String procInsId){
		return dao.getByProcInsId(procInsId);
	}
	@Transactional(readOnly=false)
	public void upload(Borrowing borrowing){
		dao.upload(borrowing);
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param borrowing
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Borrowing> findPage2(Page<Borrowing> page, Borrowing borrowing) {
		borrowing.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}
	
}
