package com.thinkgem.jeesite.modules.tb.tbMoney.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.tbMoney.dao.BusinessDao;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Business;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.ReceptionStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 业务招待
 * @update Mr.dong
 *
 */
@Service
public class BusinessService extends CrudService<BusinessDao, Business>{
	
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private ActTaskService actTaskService;
	
	public Page<Business> findEmployeesPage(Page<Business> page, Business business) {
		business.setPage(page);
		page.setList(dao.findEmployeesList());
		return page;
	}
	public Business getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}
	
	/**
	 * 保存或编辑申请信息
	 */
	@Transactional(readOnly = false)
	public void save(Business business){
		if(StringUtils.isBlank(business.getId())){
			String userName = UserUtils.getUser().getLoginName();
			Boolean a = Arrays.asList(ActUtils.ACT_EXA_LEADER).contains(userName);
			if(a){
				business.setLeader(1);
			}else{
				business.setLeader(0);
			}
			business.preInsert();
			dao.insert(business);
			//启动流程
			startGet(business);
			
		}else{//驳回列表
			business.preUpdate();
			dao.update(business);
			if ("yes".equals(business.getAct().getFlag())) {
				business.getAct().setComment("[重申]" + business.getReason());
				business.setStatu("审核中");
				businessDao.updateStatu(business);
			} else {
				business.getAct().setComment("[销毁]");
				business.setStatu("销毁");
				businessDao.updateStatu(business);
				dao.delete(business);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(business.getAct().getFlag()) ? "1" : "0");
			vars.put("money", business.getMoney());
			String taskId = businessDao.selectTaskIdByProcinsId(business.getAct().getProcInsId());
			actTaskService.complete(taskId, business.getAct().getProcInsId(),
					business.getAct().getComment(), business.getReason(), vars);
		}
	}
	@Transactional(readOnly = false)
	public void auditSave(Business business){
		// 设置审核意见
		business.getAct().setComment(
				("yes".equals(business.getAct().getFlag()) ? "[同意]" : "[驳回]") + business.getAct().getComment());
		business.preUpdate();
		
		
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛财务总监");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛总经理");
		Boolean thd_d = Arrays.asList(roles).contains("桃花岛总裁");
		Map<String, Object> vars = Maps.newHashMap();
		/**总裁审核意见*/
		if(thd_d){
			business.setPrfourText(business.getAct().getComment());
			dao.updatePrfourText(business);
		}
		/**办事处级别*/
		if (thd_a&&!thd_b) {
			business.setProneText(business.getAct().getComment());
			dao.updateProneText(business);
		}
		/**财务总监*/
		if(thd_b){
			business.setPrtwoText(business.getAct().getComment());
			dao.updatePrtwoText(business);
		}
		/**总经理*/
		if(thd_c){
			business.setPrthreeText(business.getAct().getComment());
			dao.updatePrthreeText(business);
		}
		/**流程正常走向 同意1*/
		vars.put("agree", "yes".equals(business.getAct().getFlag()) ? "1" : "0");
		if(!"yes".equals(business.getAct().getFlag())){
			business.setStatu("驳回");
			businessDao.updateStatu(business);
		}
		if("yes".equals(business.getAct().getFlag())&&"prtwoText".equals(thd_b)){
			business.setStatu("审核通过");
			businessDao.updateStatu(business);
		}
		if("yes".equals(business.getAct().getFlag())&&thd_c){
			business.setStatu("已付款");
			businessDao.updateStatu(business);
			/**流程结束*/
			vars.put("agree", "yes".equals(business.getAct().getFlag()) ? "3" : "0");
		}
		
		actTaskService.complete(business.getAct().getTaskId(), business.getAct().getProcInsId(),
				business.getAct().getComment(), vars);
		
			
	}
	@Transactional(readOnly = false)
	public void insertReceptionStaff(ReceptionStaff receptionStaff){
		businessDao.insertReceptionStaff(receptionStaff);
	}
	public void startGet(Business business){
		Map<String, Object> vars = new HashMap<String,Object>();
		vars.put("money", business.getMoney());
		vars.put("statu", "审核中");
		vars.put("assgine", business.getAct().getAssignee());
		
		/**
		 * @update Mr.dong
		 * 业务招待费用流程思路
		 * 1.若当前提交人的上级为总裁级别，则：
		 * 提交人——俞跃舒（总裁）——财务总监——俞林伟（总经理）
		 * 2.若当前提交人的上级为桃花岛办事处领导级别，则：
		 * 提交人——直属上级——俞跃舒（总裁）——财务总监——俞林伟（总经理）
		 * 3.若当前提交人的上级为桃花岛部门领导级别，则：
		 * 提交人——直属上级的上级——俞跃舒（总裁）——财务总监——俞林伟（总经理）
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
			vars.put("userTask1", UserUtils.getByRoleEnname("CEO").get(0));
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_general_manager").get(0));
		}else if(thd_b){
			vars.put("userTask1", leaderName);
			vars.put("userTask2", UserUtils.getByRoleEnname("CEO").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_general_manager").get(0));
		}else if(thd_c){
			/**获取上级的上级领导姓名*/
			String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
			vars.put("userTask1", leader_leader);
			vars.put("userTask2", UserUtils.getByRoleEnname("CEO").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_general_manager").get(0));
		}
		actTaskService.startProcess(ActUtils.THD_BUSINESS[0], ActUtils.THD_BUSINESS[1], business.getId(),
					business.getReason(), vars);
	}
	public List<ReceptionStaff> selectReceptionStaff(String id){
		return dao.selectReceptionStaff(id);
	}
	@Transactional(readOnly = false)
	public void deleteReceptionStaffById(String id){
		dao.deleteReceptionStaffById(id);
	}
	@Transactional(readOnly=false)
	public void uploadBusiness(Business business){
		dao.uploadBusiness(business);
	}
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param business
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Business> findPage2(Page<Business> page, Business business) {
		business.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}
}
