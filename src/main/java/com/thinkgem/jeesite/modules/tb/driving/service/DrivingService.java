package com.thinkgem.jeesite.modules.tb.driving.service;


import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.driving.dao.DrivingDao;
import com.thinkgem.jeesite.modules.tb.driving.entity.Driving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @ClassName: DrivingService 
 * @Description: 自驾车申请service层
 * @author: WangFucheng
 * @date 2018年7月16日 下午3:06:00 
 * @update Mr.dong
 *
 */
@Service
public class DrivingService extends CrudService<DrivingDao, Driving>{
	@Autowired
	private ActTaskService actTaskService;
	/**
	 * 
	 * @Title: save
	 * @Description: 提交申请时调用
	 * @author: WangFucheng
	 * @param driving 自驾车申请表实体类
	 * @throws
	 */
	@Transactional(readOnly=false)
	public void save(Driving driving){
		driving.setTitle("自驾车出差申请-"+UserUtils.getUser().getName()+"-"+
				driving.getOrigin()+"-"+driving.getDestination()+"-"+
				driving.getEstimatedCost());
		if(StringUtils.isBlank(driving.getId())){//提交申请
			driving.preInsert();
			dao.insert(driving);
			startGet(driving);			
		}else{//驳回的数据
			dao.update(driving);
			if ("yes".equals(driving.getAct().getFlag())) {
				driving.getAct().setComment("[重申]" + driving.getTitle());
				driving.setStatu("审核中");
				dao.updateStatu(driving);
			} else {
				driving.getAct().setComment("[销毁]" + driving.getAct().getComment());
				driving.setStatu("销毁");
				dao.updateStatu(driving);
				dao.delete(driving);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(driving.getAct().getFlag()) ? "1" : "0");
			vars.put("money", driving.getEstimatedCost());
			String taskId = dao.selectTaskIdByProcinsId(driving.getAct().getProcInsId());
			actTaskService.complete(taskId, driving.getAct().getProcInsId(),
					driving.getAct().getComment(), driving.getTitle(), vars);
		}
	}
	/**
	 * 
	 * @Title: auditSave
	 * @Description: 审批同意或驳回调用该方法
	 * @author: WangFucheng
	 * @param driving void   返回类型 
	 * @throws
	 */
	@Transactional(readOnly=false)
	public void auditSave(Driving driving){
		// 设置审核意见
		driving.getAct().setComment(
				("yes".equals(driving.getAct().getFlag()) ? "[同意]" : "[驳回]") + driving.getAct().getComment());
		driving.preUpdate();
		
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛总裁");
		Map<String, Object> vars = Maps.newHashMap();
		if (thd_a) {
			driving.setProneText(driving.getAct().getComment());
			dao.updateProneText(driving);
		}
		if(thd_b){
			driving.setPrtwoText(driving.getAct().getComment());
			dao.updatePrtwoText(driving);
		}
		/**同意，则流程结束，否则驳回*/
		vars.put("agree", "yes".equals(driving.getAct().getFlag()) ? "3" : "0");
		vars.put("money", driving.getEstimatedCost());
		if(!"yes".equals(driving.getAct().getFlag())){
			driving.setStatu("驳回");
			dao.updateStatu(driving);
		}
		if("yes".equals(driving.getAct().getFlag())){//主管同意则通过
			driving.setStatu("审核通过");
			dao.updateStatu(driving);
		}
		
		actTaskService.complete(driving.getAct().getTaskId(), driving.getAct().getProcInsId(),
				driving.getAct().getComment(), vars);
				
	}
	/**
	 * 
	 * @Title: startGet
	 * @Description: 启动流程
	 * @author: WangFucheng
	 * @param driving 自驾车申请表实体类
	 * @throws
	 */
	public void startGet(Driving driving){
		
		/**
		 * @updat Mr.dong
		 * 自驾申请流程思路
		 * 1.如果当前提交人的直属上级是总裁
		 * 提交人--总裁
		 * 2.如果当前提交人的直属上级是办事处级别
		 * 提交人--直属上级
		 * 3.如果当前提交人的直属上级是部门级别
		 * 提交人--直属上级的上级
		 * 
		 */
		Map<String, Object> vars = new HashMap<String,Object>();
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
		actTaskService.startProcess(ActUtils.THD_DRVING[0], ActUtils.THD_DRVING[1], driving.getId(),
				driving.getTitle(), vars);
	}
	/**
	 * 
	 * @Title: getByProcInsId
	 * @Description: 通过流程实例id获取业务表实体 
	 * @author: WangFucheng
	 * @param procInsId
	 * @return Signet   返回类型 
	 * @throws
	 */
	public Driving getByProcInsId(String procInsId){
		return dao.getByProcInsId(procInsId);
	}
	/**
	 * 
	 * @Title: findEmployeesPage
	 * @Description: 获取所有员工列表
	 * @author: WangFucheng
	 * @param page
	 * @param driving
	 * @return Page<Driving>   返回类型 
	 * @throws
	 */
	public Page<Driving> findEmployeesPage(Page<Driving> page, Driving driving) {
		driving.setPage(page);
		page.setList(dao.findEmployeesList(driving));
		return page;
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param driving
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Driving> findPage2(Page<Driving> page, Driving driving) {
		driving.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}
}
