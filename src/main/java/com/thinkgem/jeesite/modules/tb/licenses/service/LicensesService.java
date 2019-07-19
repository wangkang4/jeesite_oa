package com.thinkgem.jeesite.modules.tb.licenses.service;


import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.licenses.dao.LicensesDao;
import com.thinkgem.jeesite.modules.tb.licenses.entity.Licenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class LicensesService extends CrudService<LicensesDao,Licenses> {

	
	@Autowired
	private ActTaskService actTaskService;
	
	@Transactional(readOnly = false)
	public void save(Licenses licenses){
		if(StringUtils.isBlank(licenses.getId())){
			licenses.preInsert();
			dao.insert(licenses);
			//启动流程
			startGet(licenses);
			
		}else{//驳回列表
			licenses.preUpdate();
			dao.update(licenses);
			if ("yes".equals(licenses.getAct().getFlag())) {
				licenses.getAct().setComment("[重申]" + licenses.getTitle());
				licenses.setStatu("审核中");
				dao.updateStatu(licenses);
			} else {
				licenses.getAct().setComment("[销毁]" + licenses.getAct().getComment());
				licenses.setStatu("销毁");
				dao.updateStatu(licenses);
				dao.delete(licenses);
			}			
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(licenses.getAct().getFlag()) ? "1" : "0");
			String taskId = dao.selectTaskIdByProcinsId(licenses.getAct().getProcInsId());
			actTaskService.complete(taskId, licenses.getAct().getProcInsId(),
					licenses.getAct().getComment(), licenses.getTitle(), vars);
		}
	}
	@Transactional(readOnly = false)
	public void auditSave(Licenses licenses){
		// 设置审核意见
		licenses.getAct().setComment(
				("yes".equals(licenses.getAct().getFlag()) ? "[同意]" : "[驳回]") + licenses.getAct().getComment());
		licenses.preUpdate();		
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛北京证照管理人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛合肥证照管理人");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛财务总监");
		Boolean thd_d = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Map<String, Object> vars = Maps.newHashMap();
		licenses.setStatu("审核中");
		//如当前审核人为北京或合肥区证照管理人
		if(thd_a||thd_b){		
			licenses.setPrthreeText(licenses.getAct().getComment());
			dao.updatePrthreeText(licenses);
			vars.put("agree", "yes".equals(licenses.getAct().getFlag()) ? "3" : "0");
			if("yes".equals(licenses.getAct().getFlag())){
				licenses.setStatu("审核通过");
				dao.updateStatu(licenses);
			}
		}
		if(thd_d&&!thd_c){
			//进入办事处负责人审核
			licenses.setProneText(licenses.getAct().getComment());
			dao.updateProneText(licenses);
			vars.put("agree", "yes".equals(licenses.getAct().getFlag()) ? "1" : "0");
		}
		if(thd_c){
			//进入财务总监审核
			licenses.setPrtwoText(licenses.getAct().getComment());
			dao.updatePrtwoText(licenses);
			vars.put("agree", "yes".equals(licenses.getAct().getFlag()) ? "1" : "0");
		}
		
		if(!"yes".equals(licenses.getAct().getFlag())){
			licenses.setStatu("驳回");
			dao.updateStatu(licenses);
		}
		
		actTaskService.complete(licenses.getAct().getTaskId(), licenses.getAct().getProcInsId(),
				licenses.getAct().getComment(), vars);
		
			
	}
	
	public void startGet(Licenses licenses){
		Map<String, Object> vars = new HashMap<String,Object>();
		/**
		 * @update wangqinghong
		 * 证照申请流程思路：
		 * 首先获取当前人的上级领导
		 * 	1.如果直属上级是总裁
		 * 	则：提交人--财务总监--证照管理人
		 * 	2.如果直属上级是办事处级别负责人
		 *  则：提交人--直属上级--财务总监--证照管理人
		 *  3.如果直属上级是部门级别负责人
		 *  则：提交人--直属上级的上级--财务总监--证照管理人	 */		
		/*获取当前登录人的上级的id*/
		String leaderId = UserUtils.getUser().getLeader();
		/*获取当前登录人的上级的姓名*/
		String leaderName = UserUtils.get(leaderId).getName();			
		/*通过id获取指定人的角色分配*/
		String roleStr = UserUtils.get(leaderId).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		//判断是否为这一级别的人
		Boolean b1 = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean b2 = Arrays.asList(roles).contains("桃花岛部门级别负责人");
		//申请人为普通用户
		if(b2){
			/*获取上级的上级领导姓名*/
			String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
			vars.put("userTask1", leader_leader);
			/*财务总监*/			
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			/*上级的上级的id*/
			String llId=UserUtils.get(UserUtils.get(UserUtils.getUser().getLeader()).getLeader()).getId();
			/*通过id获取指定人的角色分配*/
			String juese = UserUtils.get(llId).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
			String[] jss = juese.split(",");
			Boolean b3 = Arrays.asList(jss).contains("桃花岛合肥区负责人");//陈景
			Boolean b4 = Arrays.asList(jss).contains("桃花岛执行董事");//傅立秦
			if(b3||b4){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_hefei_zhengzhao").get(0));				
			}else{
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_beijing_zhenghao").get(0));
			}
		}else if(b1){
			//申请人为部门级别负责人
			vars.put("userTask1", leaderName);
			/*财务总监*/			
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			Boolean b3 = Arrays.asList(roles).contains("桃花岛合肥区负责人");
			Boolean b4 = Arrays.asList(roles).contains("桃花岛执行董事");
			if(b3||b4){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_hefei_zhengzhao").get(0));				
			}else{
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_beijing_zhenghao").get(0));
			}
		}else{
			/*财务总监*/			
			vars.put("userTask1", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			/*当前用户ID*/
			String useId=UserUtils.getUser().getId();
			/*获取当前用户角色*/
			String r = UserUtils.get(useId).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
			String[] rs = r.split(",");
			//若申请人为合肥区负责人或执行董事，则其证照管理人为合肥区证照管理人
			if(Arrays.asList(rs).contains("桃花岛合肥区负责人")||Arrays.asList(rs).contains("桃花岛执行董事")){
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_hefei_zhengzhao").get(0));				
			}else{
				//否则证照管理人为北京区证照管理人
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_beijing_zhenghao").get(0));
			}
		}
		actTaskService.startProcess(ActUtils.THD_LICENSES[0], ActUtils.THD_LICENSES[1], licenses.getId(),
				licenses.getTitle(), vars);
	}
	public Licenses getByProcInsId(String procInsId){
		return dao.getByProcInsId(procInsId);
	}
	public Page<Licenses> findEmployeesPage(Page<Licenses> page, Licenses licenses) {
		licenses.setPage(page);
		page.setList(dao.findEmployeesList(licenses));
		return page;
	}
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param licenses
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Licenses> findPage3(Page<Licenses> page, Licenses licenses) {
		licenses.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}
}
