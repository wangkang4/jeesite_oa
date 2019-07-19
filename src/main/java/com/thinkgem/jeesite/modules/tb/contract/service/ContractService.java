package com.thinkgem.jeesite.modules.tb.contract.service;


import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.ActDao;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.contract.dao.ContractDao;
import com.thinkgem.jeesite.modules.tb.contract.entity.Contract;
import com.thinkgem.jeesite.sale.entity.GetSaleCount;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class ContractService extends CrudService<ContractDao, Contract>{
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ContractDao contractDao;
	
	@Transactional(readOnly=false)
	public void save(Contract contract,String type,String PY){
		if(StringUtils.isBlank(contract.getId())){//提交申请
			contract.preInsert();
			contract.setContractApply(contract.getCreateBy().getId());
			contract.setContractDate(contract.getCreateDate());
			updateContractNum(contract,type,PY);
			dao.insert(contract);
			startGet(contract);
		}else{//驳回的数据
			contract.preUpdate();
			String[] contractNum = contract.getContractNum().split("-");

			contract.setContractNum(contractNum[0]+"-"+type+"-"
					+PY+"-"
					+contractNum[3]+"-"+contractNum[4]);
			dao.update(contract);
			if ("yes".equals(contract.getAct().getFlag())) {
				contract.getAct().setComment("[重申]" + contract.getContractName());
				contract.setStatu("审核中");
				contractDao.updateStatu(contract);
			} else {
				contract.getAct().setComment("[销毁]" + contract.getAct().getComment());
				contract.setStatu("销毁");
				contractDao.updateStatu(contract);
				dao.delete(contract);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(contract.getAct().getFlag()) ? "1" : "0");
			vars.put("money", contract.getMoney());
			String taskId = dao.selectTaskIdByProcinsId(contract.getAct().getProcInsId());
			actTaskService.complete(taskId, contract.getAct().getProcInsId(),
					contract.getAct().getComment(), contract.getContractNum(), vars);
		}
	}
	public String selectAreaById(String id){
		return dao.selectAreaById(id);
	}
	public List<Contract> selectContractToAssociated(String contractApply){
		return dao.selectContractToAssociated(contractApply);
	}
	public Contract getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}
	@Transactional(readOnly=false)
	public void auditSave(Contract contract){

		contract.getAct().setComment(("yes".equals(contract.getAct().getFlag()) ? "[同意]" : "[驳回]") + contract.getAct().getComment());
		contract.preUpdate();

		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛财务总监");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛商务部副部长");
		Boolean thd_d = Arrays.asList(roles).contains("桃花岛总经理");

		// 各个审核环节
		// 主管
		if (thd_a&&!thd_b&&!thd_c) {
			contract.setPrtwoText(contract.getAct().getComment());
			dao.updatePrtwoText(contract);
		}
		//商务部长
		if (thd_c) {
			contract.setPrthreeText(contract.getAct().getComment());
			dao.updatePrthreeText(contract);
		}
		//财务总监
		if (thd_b) {
			contract.setPrfiveText(contract.getAct().getComment());
			dao.updatePrfiveText(contract);
		}
		//总经理
		if (thd_d) {
			contract.setPrsixText(contract.getAct().getComment());
			dao.updatePrsixText(contract);
		}

		Map<String, Object> vars = new HashMap<String,Object>();
		/**流程正常放向 agree==1同意*/
		vars.put("agree", "yes".equals(contract.getAct().getFlag()) ? "1" : "0");
		if(StringUtils.isBlank(contract.getProcId())){//合同申请
			if(!"yes".equals(contract.getAct().getFlag())){
				contract.setStatu("驳回");
				contractDao.updateStatu(contract);
			}

			//申请者所属部门
			String officeName = contractDao.findOfficeByName(contract.getContractApply());
			if (officeName.equals("财务部")){
				if(("yes".equals(contract.getAct().getFlag()))&&thd_d){//总经理同意
					contract.setStatu("审核通过");
					contractDao.updateStatu(contract);
					/**流程结束*/
					vars.put("agree", "yes".equals(contract.getAct().getFlag()) ? "3" : "0");
				}
			}else {
				if(("yes".equals(contract.getAct().getFlag()))&&thd_b){//财务总监同意
					contract.setStatu("审核通过");
					contractDao.updateStatu(contract);
					/**流程结束*/
					vars.put("agree", "yes".equals(contract.getAct().getFlag()) ? "3" : "0");
				}
			}

		}else{//合同作废
			if(!"yes".equals(contract.getAct().getFlag())){
				contract.setStatus("作废失败");
				contractDao.updateStatus(contract);
			}
//            if("yes".equals(contract.getAct().getFlag())&&thd_b
//                    &&contract.getMoney()<1000000){//财务总监选择同意并且金额小于100万
//                contract.setStatus("已作废");
//                contractDao.updateStatus(contract);
//                contractDao.delete(contract);
//                /**流程结束*/
//                vars.put("agree", "yes".equals(contract.getAct().getFlag()) ? "3" : "0");
//            }
			if(("yes".equals(contract.getAct().getFlag()))&&(thd_d||thd_b)){//总经理同意
				contract.setStatus("已作废");
				contractDao.updateStatus(contract);
				contractDao.delete(contract);
				/**流程结束*/
				vars.put("agree", "yes".equals(contract.getAct().getFlag()) ? "3" : "0");
			}

		}

		actTaskService.complete(contract.getAct().getTaskId(), contract.getAct().getProcInsId(),
				contract.getAct().getComment(), vars);
	}
	public void startGet(Contract contract){
		Map<String, Object> vars = new HashMap<String,Object>();
		vars.put("money", contract.getMoney());

		/**
		 * @update Mr.dong
		 * 合同申请流程思路：
		 * 首先获取当前人的上级领导
		 * 	1.如果直属上级是总裁
		 * 	则：提交人--商务部长--财务总监--俞林伟（总经理）
		 * 	2.如果直属上级是办事处级别负责人
		 *  则：提交人——直属上级——商务部长——财务总监——俞林伟（总经理）
		 *  3.如果直属上级是部门级别负责人
		 *  则：提交人——直属上级的上级——商务部长——财务总监——俞林伟（总经理）
		 *
		 */

		/**
		 * 获取当前用户所属部门
		 */
		String office = UserUtils.getUser().getOffice().toString();
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
		if (office.equals("财务部")){
			vars.put("sigin",1);
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_general_manager").get(0));
		}else {
			//没有产品经理
			if (contract.getManager().isEmpty()){
				if(thd_a){
					vars.put("sigin",1);
					vars.put("userTask2", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}else if(thd_b){
					vars.put("sigin",1);
					vars.put("userTask2", leaderName);
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask5", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}else if(thd_c){
					/**获取上级的上级领导姓名*/
					String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
					vars.put("sigin",1);
					vars.put("userTask2", leader_leader);
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask5", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}
			}else {
				//有产品经理
				//产品经理名字
				String productId = contract.getManager();
				//根据id查询产品经理名字
				String productName = contractDao.getManagerName(productId);
				if(thd_a){
					vars.put("sigin",2);
					vars.put("userTask1",productName);
					vars.put("userTask2", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}else if(thd_b){
					vars.put("sigin",2);
					vars.put("userTask1",productName);
					vars.put("userTask2", leaderName);
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask5", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}else if(thd_c){
					/**获取上级的上级领导姓名*/
					String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
					vars.put("sigin",2);
					vars.put("userTask1",productName);
					vars.put("userTask2", leader_leader);
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask5", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		actTaskService.startProcess(ActUtils.THD_CONTRACT[0], ActUtils.THD_CONTRACT[1], contract.getId(),
				contract.getContractName()+"-"+contract.getMoney()+"元"+"-"+sdf.format(contract.getCreateDate()), vars);
	}
	public void updateContractNum(Contract contract,String type,String PY){
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(day);
		GetSaleCount gsc = new GetSaleCount();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy");
		String tim = sd.format(day);
		synchronized(gsc){
			gsc = dao.selectCount();
			if(gsc.getTime().equals(tim)){
				gsc.setCount(gsc.getCount()+1);
			}else{
				gsc.setTime(tim);
				gsc.setCount(1);
			}
		}
		
		contract.setContractNum("BJTHD-"+type+"-"
				+PY+"-"
				+time+"-"+String.format("%04d", gsc.getCount()));
		dao.updateCount(gsc);
	}
	public List<Contract> findContract(Contract contract){
		return dao.findContract(contract);
	}
	@Transactional(readOnly=false)
	public void abandon(Contract contract){
		Map<String, Object> vars = new HashMap<String,Object>();
		vars.put("money", contract.getMoney());
		/**
		 * 获取当申请者所属部门
		 */
		String office = contractDao.findOfficeByName(contract.getContractApply());
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
		if (office.equals("财务部")){
			vars.put("sigin",1);
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_general_manager").get(0));
		}else {
			//没有产品经理
			if (contract.getManager().isEmpty()){
				if(thd_a){
					vars.put("sigin",1);
					vars.put("userTask2", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}else if(thd_b){
					vars.put("sigin",1);
					vars.put("userTask2", leaderName);
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask5", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}else if(thd_c){
					/**获取上级的上级领导姓名*/
					String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
					vars.put("sigin",1);
					vars.put("userTask2", leader_leader);
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask5", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}
			}else {
				//有产品经理
				//产品经理名字
				String productId = contract.getManager();
				String productName = contractDao.getManagerName(productId);
				if(thd_a){
					vars.put("sigin",2);
					vars.put("userTask1",productName);
					vars.put("userTask2", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}else if(thd_b){
					vars.put("sigin",2);
					vars.put("userTask1",productName);
					vars.put("userTask2", leaderName);
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask5", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}else if(thd_c){
					/**获取上级的上级领导姓名*/
					String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
					vars.put("sigin",2);
					vars.put("userTask1",productName);
					vars.put("userTask2", leader_leader);
					vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
					vars.put("userTask4", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
					vars.put("userTask5", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		startProcess(ActUtils.THD_CONTRACT[0], ActUtils.THD_CONTRACT[1], contract.getId(),
				"作废"+contract.getContractName()+"-"+contract.getMoney()+"元"+"-"+sdf.format(contract.getCreateDate()), vars,contract);
	}
	@Autowired
	private IdentityService identityService;
	@Autowired
	private ActDao actDao;
	@Autowired
	private RuntimeService runtimeService;
	
	public String startProcess(String procDefKey, String businessTable, String businessId, String title, Map<String, Object> vars,Contract contract) {
		String userId = UserUtils.getUser().getLoginName();//ObjectUtils.toString(UserUtils.getUser().getId())
		
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(userId);
		
		// 设置流程变量
		if (vars == null){
			vars = Maps.newHashMap();
		}
		
		// 设置流程标题
		if (StringUtils.isNotBlank(title)){
			vars.put("title", title);
		}
		
		// 启动流程
		ProcessInstance procIns = runtimeService.startProcessInstanceByKey(procDefKey, businessTable+":"+businessId, vars);
		
		// 更新业务表流程实例ID
		Act act = new Act();
		act.setBusinessTable(businessTable);// 业务表名
		act.setBusinessId(businessId);	// 业务表ID
		act.setProcInsId(procIns.getId());
		
		if(vars != null && vars.get("assgine") != null){
			String assgin = vars.get("assgine").toString();
			if(assgin != null && !"".equals(assgin)){
				act.setAssignee(assgin);
				act.setAssigneeName(assgin);
//				act.setAssigneeName(assigneeName);
				actDao.updateAssgineByProcInsId(act);//修改办理人
				actDao.updateUserIDByProcInsId(act);
			}
		}
		contract.setProcId(procIns.getId());
		contract.setStatus("作废中");
		contractDao.abandon(contract);
		return act.getProcInsId();
	}
	//实现合同撤回功能
	@Transactional(readOnly = false)
	public void withdraw(Contract contract){
		contract.setStatu("撤回");
		contractDao.updateStatu(contract);
		dao.withdraw(contract.getId());
	}
	public Page<Contract> findEmployeesPage(Page<Contract> page, Contract contract) {
		contract.setPage(page);
		page.setList(dao.findEmployeesList(contract));
		return page;
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param contract
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Contract> findPage2(Page<Contract> page, Contract contract) {
		contract.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}

}
