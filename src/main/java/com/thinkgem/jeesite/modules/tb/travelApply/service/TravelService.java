package com.thinkgem.jeesite.modules.tb.travelApply.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.travelApply.dao.TravelDao;
import com.thinkgem.jeesite.modules.tb.travelApply.entity.TravelApply;
import com.thinkgem.jeesite.modules.tb.travelApply.entity.TravelPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TravelService extends CrudService<TravelDao,TravelApply> implements ITravelService{
	
	/**
	 * 依赖注入dao层
	 */
	@Autowired
	private TravelDao travelDao;
	
	/**
	 * 依赖注入actTaskServic属性
	 */
	@Autowired
	private ActTaskService actTaskService;
	
	
	/**
	 * 向负表中插入数据
	 * @param travelPlan
	 */
	@Transactional(readOnly=false)
	public void insertPlan(TravelPlan travelPlan) {
		travelDao.insertPlan(travelPlan);
	}
	
	/**
	 * 删除负表信息
	 * @param planId
	 */
	@Transactional(readOnly=false)
	public void deletePlan(String planId) {
		travelDao.deletePlan(planId);
		
	}
	
	/**
	 * 表单提交/修改/销毁
	 */
	@Transactional(readOnly=false)
	public void save(TravelApply travelApply){
		if(StringUtils.isBlank(travelApply.getId())){//提交申请
			travelApply.preInsert();
			travelApply.setStatu("审核中");
			dao.insert(travelApply);//插入主表数据
			startGet(travelApply);			
		}else{
			travelApply.preUpdate();
			dao.update(travelApply);//修改表的数据
			if ("yes".equals(travelApply.getAct().getFlag())) {
				travelApply.getAct().setComment("[重申]" + travelApply.getUser().getLoginName()+"-出差申请");
				travelApply.setStatu("审核中");
				dao.updateStatu(travelApply);
			} else {
				travelApply.getAct().setComment("[销毁]" + travelApply.getAct().getComment());
				travelApply.setStatu("销毁");
				dao.updateStatu(travelApply);
				dao.delete(travelApply);
			}
			Map<String, Object> vars = Maps.newHashMap();
			
			vars.put("rebut", "yes".equals(travelApply.getAct().getFlag()) ? "1" : "0");
			String taskId = dao.selectTaskIdByProcinsId(travelApply.getAct().getProcInsId());
			
			// 启动部署成功的工作流
			String userName = UserUtils.getUser().getLoginName();
			//出差申请中研发只需一级审批的人员
			Boolean a = Arrays.asList(ActUtils.ACT_EXA_TRAVEL).contains(userName);
			if(a){
				vars.put("pass","2");
			}else{
				vars.put("pass","1");
			}
			actTaskService.complete(taskId, travelApply.getAct().getProcInsId(),
					travelApply.getAct().getComment(), travelApply.getUser().getLoginName()+"-出差申请", vars);
		}
	}
	
	/**
	 * 启动流程
	 * @param travelApply
	 */
	private void startGet(TravelApply travelApply) {
		
		/**
		 * @updat Mr.dong
		 * 出差申请流程思路
		 * 1.如果当前提交人的直属上级是总裁
		 * 提交人--总裁
		 * 2.如果当前提交人的直属上级是办事处级别
		 * 提交人--直属上级
		 * 3.如果当前提交人的直属上级是部门级别
		 * 提交人--直属上级的上级
		 * 
		 */
		Map<String, Object> vars = Maps.newHashMap();
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
		}else if(thd_b){
			vars.put("userTask1", leaderName);
		}else if(thd_c){
			/**获取上级的上级领导姓名*/
			String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
			vars.put("userTask1", leader_leader);
		}
			actTaskService.startProcess(ActUtils.THD_TRAVEL[0],ActUtils.THD_TRAVEL[1], travelApply.getId(),
					travelApply.getUser().getLoginName()+"-出差申请", vars);
	}

	/**
	 * 流程审核
	 * @param travelApply
	 */
	@Transactional(readOnly = false)
	public void auditSave(TravelApply travelApply){
		// 设置审核意见
		travelApply.getAct().setComment(
				("yes".equals(travelApply.getAct().getFlag()) ? "[同意]" : "[驳回]") + travelApply.getAct().getComment());
		travelApply.preUpdate();
			
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛总裁");
		Map<String, Object> vars = Maps.newHashMap();
		if (thd_a) {
			travelApply.setProneText(travelApply.getAct().getComment());
			dao.updateProneText(travelApply);
		}
		if(thd_b){
			travelApply.setPrtwoText(travelApply.getAct().getComment());
			dao.updatePrtwoText(travelApply);
		}
		
		if(!"yes".equals(travelApply.getAct().getFlag())){
			travelApply.setStatu("驳回");
			dao.updateStatu(travelApply);
		}
		/**
		 * 如果是最后一个人的节点并且又是同意，则更改流程状态为审核通过
		 */
		if("yes".equals(travelApply.getAct().getFlag())){
				travelApply.setStatu("审核通过");
				dao.updateStatu(travelApply);
			}
		vars.put("agree", "yes".equals(travelApply.getAct().getFlag()) ? "3" : "0");
		actTaskService.complete(travelApply.getAct().getTaskId(), travelApply.getAct().getProcInsId(),
				travelApply.getAct().getComment(), vars);
		
	}
	
	/**
	 * 通过流程实例id获取信息
	 * @param procInsId
	 * @return
	 */
	@Transactional(readOnly=false)
	public TravelApply getByProcInsId(String procInsId) {
		
		return dao.getByProcInsId(procInsId);
	}

	/**
	 * 根据p_id查询负表信息
	 * @param Id
	 * @return 
	 */
	@Transactional(readOnly=false)
	public List<TravelPlan> findPlan(String planId) {
		
		return travelDao.findPlan(planId);
	}
	
	/**
	 * 删除申请的数据
	 * @param procInsId
	 */
	@Transactional(readOnly=false)
	public void deletetravelApply(String procInsId) {
		
		travelDao.deletetravelApply(procInsId);
	}
	
	/**
	 * 删除流程数据
	 * @param procInsId
	 */
	@Transactional(readOnly=false)
	public void deleteTask(String procInsId) {
		
		travelDao.deleteTask(procInsId);
	}

	/**
	 * 关联出差报销申请，查询是否有改编号的出差申请信息
	 * @param num 编号
	 * @return 没有则返回0，有，则返回数字
	 */
	public Integer selectNum(String num) {
		
		return travelDao.selectNum(num);
	}

	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param travelApply
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<TravelApply> findPage3(Page<TravelApply> page, TravelApply travelApply) {
		travelApply.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList3(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}


	/**
	 * 财务人员查看员工申请列表
	 * @param page
	 * @param travelApply
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<TravelApply> findListCaiWu(Page<TravelApply> page, TravelApply travelApply) {
		travelApply.setPage(page);
		page.setList(dao.findListCaiWu(travelApply));
		return page;
	}
	
}
