package com.thinkgem.jeesite.modules.tb.tbMoney.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.tbMoney.dao.SpecialDao;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Special;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 专项费用申请
 * @update Mr.dong
 *
 */
@Service
public class SpecialService extends CrudService<SpecialDao, Special>{
	
	@Autowired
	private SpecialDao specialDao;
	@Autowired
	private ActTaskService actTaskService;
	
	
	public Page<Special> findEmployeesPage(Page<Special> page, Special special) {
		special.setPage(page);
		page.setList(dao.findEmployeesList());
		return page;
	}
	public Special getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}
	
	/**
	 * 保存或编辑申请信息
	 */
	@Transactional(readOnly = false)
	public void save(Special special){
		if(StringUtils.isBlank(special.getId())){
			String userName = UserUtils.getUser().getLoginName();
			Boolean a = Arrays.asList(ActUtils.ACT_EXA_LEADER).contains(userName);
			if(a){
				special.setLeader(1);
			}else{
				special.setLeader(0);
			}
			special.preInsert();
			dao.insert(special);
			//启动流程
			startGet(special);
			
		}else{//驳回列表
			special.preUpdate();
			dao.update(special);
			if ("yes".equals(special.getAct().getFlag())) {
				special.getAct().setComment("[重申]" + special.getReason());
				special.setStatu("审核中");
				specialDao.updateStatu(special);
			} else {
				special.getAct().setComment("[销毁]");
				special.setStatu("销毁");
				specialDao.updateStatu(special);
				dao.delete(special);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(special.getAct().getFlag()) ? "1" : "0");
			vars.put("money", special.getMoney());
			String taskId = specialDao.selectTaskIdByProcinsId(special.getAct().getProcInsId());
			actTaskService.complete(taskId, special.getAct().getProcInsId(),
					special.getAct().getComment(), special.getReason(), vars);
		}
		
	}
	@Transactional(readOnly = false)
	public void auditSave(Special special){
		// 设置审核意见
		special.getAct().setComment(
				("yes".equals(special.getAct().getFlag()) ? "[同意]" : "[驳回]") + special.getAct().getComment());
		special.preUpdate();
		
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
			special.setPrfourText(special.getAct().getComment());
			dao.updatePrfourText(special);
		}
		/**办事处级别*/
		if (thd_a&&!thd_b) {
			special.setProneText(special.getAct().getComment());
			dao.updateProneText(special);
		}
		/**财务总监*/
		if(thd_b){
			special.setPrtwoText(special.getAct().getComment());
			dao.updatePrtwoText(special);
		}
		/**总经理*/
		if(thd_c){
			special.setPrthreeText(special.getAct().getComment());
			dao.updatePrthreeText(special);
		}
		
		/**流程正常走向 同意1*/
		vars.put("agree", "yes".equals(special.getAct().getFlag()) ? "1" : "0");
		if(!"yes".equals(special.getAct().getFlag())){
			special.setStatu("驳回");
			specialDao.updateStatu(special);
		}
		if("yes".equals(special.getAct().getFlag())&&"prtwoText".equals(thd_b)){
			special.setStatu("审核通过");
			specialDao.updateStatu(special);
		}
		if("yes".equals(special.getAct().getFlag())&&thd_c){
			special.setStatu("已付款");
			specialDao.updateStatu(special);
			/**流程结束*/
			vars.put("agree", "yes".equals(special.getAct().getFlag()) ? "3" : "0");
		}
		
		actTaskService.complete(special.getAct().getTaskId(), special.getAct().getProcInsId(),
				special.getAct().getComment(), vars);
	}
	public void startGet(Special special){
		Map<String, Object> vars = new HashMap<String,Object>();
		vars.put("money", special.getMoney());
		vars.put("tbDate", special.getTbDate());
		vars.put("statu", "审核中");
		vars.put("assgine", special.getAct().getAssignee());
		
		/**
		 * @update Mr.dong
		 * 专项费用流程思路
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
		actTaskService.startProcess(ActUtils.THD_SPECIALT[0], ActUtils.THD_SPECIALT[1], special.getId(),
					special.getReason(), vars);
	}
	@Transactional(readOnly=false)
	public void uploadSpecial(Special special){
		dao.uploadSpecial(special);
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param business
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Special> findPage2(Page<Special> page, Special special) {
		special.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}
}
