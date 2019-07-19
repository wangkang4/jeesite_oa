package com.thinkgem.jeesite.modules.tb.pay.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.contract.entity.Contract;
import com.thinkgem.jeesite.modules.tb.pay.dao.PayDao;
import com.thinkgem.jeesite.modules.tb.pay.entity.Pay;
import com.thinkgem.jeesite.modules.tb.pay.entity.PayTypeBig;
import com.thinkgem.jeesite.modules.tb.pay.entity.PayTypeSmall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayService extends CrudService<PayDao, Pay> {
	@Autowired
	private PayDao payDao;
	@Autowired
	private ActTaskService actTaskService;
	
	public Page<Pay> findPage(Page<Pay> page, Pay pay) {
		return super.findPage(page, pay);
	}
	public Page<Pay> findEmployeesPage(Page<Pay> page, Pay pay) {
		pay.setPage(page);
		page.setList(payDao.findEmployeesList(pay));
		return page;
	}
	public Pay getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}
	@Transactional(readOnly = false)
	public void save(Pay pay){
		if(StringUtils.isBlank(pay.getId())){//首次提交数据
			pay.preInsert();
			dao.insert(pay);
			startGet(pay);
		}else{//驳回修改页面
			pay.preUpdate();
			dao.update(pay);
			if ("yes".equals(pay.getAct().getFlag())) {
				pay.getAct().setComment("[重申]" + pay.getReason());
				pay.setStatu("审核中");
				payDao.updateStatu(pay);
			} else {
				pay.getAct().setComment("[销毁]");
				pay.setStatu("销毁");
				payDao.updateStatu(pay);
				payDao.delete(pay);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(pay.getAct().getFlag()) ? "1" : "0");
			vars.put("payMoney", pay.getPayMoney());
			String taskId = payDao.selectTaskIdByProcinsId(pay.getAct().getProcInsId());
			actTaskService.complete(taskId, pay.getAct().getProcInsId(),
					pay.getAct().getComment(), pay.getReason(), vars);
		}
	}
	@Transactional(readOnly = false)
	public void auditSave(Pay pay){
		// 设置审核意见
		pay.getAct().setComment(
				("yes".equals(pay.getAct().getFlag()) ? "[同意]" : "[驳回]") + pay.getAct().getComment());
		pay.preUpdate();
		
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛财务总监");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛商务部副部长");
		Boolean thd_d = Arrays.asList(roles).contains("桃花岛总经理");
		Boolean thd_f = Arrays.asList(roles).contains("桃花岛出纳");
		
		// 各个审核环节
		/**主管*/
		if (thd_a&&!thd_b) {
			pay.setPrthreeText(pay.getAct().getComment());
			dao.updatePrthreeText(pay);
		}
		//财务总监
		if (thd_b) {
			pay.setPrfourText(pay.getAct().getComment());
			dao.updatePrfourText(pay);
		}
		//总经理
		if (thd_d) {
			pay.setPrfiveText(pay.getAct().getComment());
			dao.updatePrfiveText(pay);
		}
		//商务主管
		if(thd_c){
			pay.setPrsixText(pay.getAct().getComment());
			dao.updatePrsixText(pay);
		}
		//出纳
		if(thd_f){
			pay.setPrsevText(pay.getAct().getComment());
			dao.updatePrsevText(pay);
		}
		Map<String, Object> vars = Maps.newHashMap();
		/**流程正常放向 agree==1同意*/
		vars.put("agree", "yes".equals(pay.getAct().getFlag()) ? "1" : "0");
		if(!"yes".equals(pay.getAct().getFlag())){
			pay.setStatu("驳回");
			payDao.updateStatu(pay);
		}
		if("yes".equals(pay.getAct().getFlag())&&thd_b){
			pay.setStatu("审核通过");
			payDao.updateStatu(pay);
		}
		if("yes".equals(pay.getAct().getFlag())&&thd_f){
			pay.setStatu("已付款");
			payDao.updateStatu(pay);
			/**流程结束*/
			vars.put("agree", "yes".equals(pay.getAct().getFlag()) ? "3" : "0");
		}
		
		actTaskService.complete(pay.getAct().getTaskId(), pay.getAct().getProcInsId(),
				pay.getAct().getComment(), vars);
	}
	
	public void startGet(Pay pay){
		Map<String, Object> vars = new HashMap<String,Object>();
		vars.put("statu","审核中");
		vars.put("payMoney", pay.getPayMoney());
		
		/**
		 * @update Mr.dong
		 * 对外付款流程思路：
		 * 首先获取当前人的上级领导
		 * 	1.如果直属上级是总裁
		 * 	则：提交人--商务部长--财务总监--俞林伟（总经理）---出纳
		 * 	2.如果直属上级是办事处级别负责人
		 *  则：提交人——直属上级——商务部长——财务总监——俞林伟（总经理）--出纳
		 *  3.如果直属上级是部门级别负责人
		 *  则：提交人——直属上级的上级——商务部长——财务总监——俞林伟（总经理）---出纳
		 *  
		 *  
		 *  对外付款流程中公司层面（通讯费）
			提交人——财务总监——俞林伟（总经理）——出纳
		 */
		/**对外付款流程中公司层面（通讯费）*/
		String tongxun = pay.getPayTypeSmall();
		if(tongxun.equals("706")
			||tongxun.equals("707")
			||tongxun.equals("708")){
			vars.put("userTask1", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_general_manager").get(0));
			vars.put("userTask3", UserUtils.getByRoleEnname("thd_chuna").get(0));
		}else {
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
				vars.put("userTask1", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_general_manager").get(0));
				vars.put("userTask4", UserUtils.getByRoleEnname("thd_chuna").get(0));
			}else if(thd_b){
				vars.put("userTask1", leaderName);
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				vars.put("userTask4", UserUtils.getByRoleEnname("thd_general_manager").get(0));
				vars.put("userTask5", UserUtils.getByRoleEnname("thd_chuna").get(0));
			}else if(thd_c){
				/**获取上级的上级领导姓名*/
				String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
				vars.put("userTask1", leader_leader);
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_shangwu_fu").get(0));
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
				vars.put("userTask4", UserUtils.getByRoleEnname("thd_general_manager").get(0));
				vars.put("userTask5", UserUtils.getByRoleEnname("thd_chuna").get(0));
			}else{
				return;
			}
			
		}
			actTaskService.startProcess(ActUtils.THD_PAY[0], ActUtils.THD_PAY[1], pay.getId(), pay.getReason(), vars);
	}
	public List<PayTypeSmall> selectFromPayTypeSmall(){
		return payDao.selectFromPayTypeSmall();
	}
	public List<PayTypeBig> selectFromPayTypeBig(){
		return payDao.selectFromPayTypeBig();
	}
	//增加回执文件地址
	@Transactional(readOnly = false)
	public void upload(Pay pay){
		payDao.upload(pay);
	}
	public List<Contract> getContract(String userId){
		return dao.getContract(userId);
	}
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param pay
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Pay> findPage2(Page<Pay> page, Pay pay) {
		pay.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}
	/**
	 * 通过流程实例id删除对应数据
	 * @param procInsId
	 */
	@Transactional(readOnly = false)
	public void deletePay(String procInsId) {
		payDao.deletePay(procInsId);
		
		
	}
	/**
	 * 通过流程实例id删除对应流程数据
	 * @param procInsId
	 */
	/*@Transactional(readOnly = false)
	public void deleteTask(String procInsId) {
		payDao.deleteTask(procInsId);
		
	}*/
}
