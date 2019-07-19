package com.thinkgem.jeesite.modules.tb.induction.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.induction.dao.InductionDao;
import com.thinkgem.jeesite.modules.tb.induction.entity.Induction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class InductionService extends CrudService<InductionDao, Induction> {
	@Autowired
	private InductionDao inductionDao;
	@Autowired
	private ActTaskService actTaskService;

	@Transactional(readOnly = false)
	public void save(Induction induction) {
		induction.setTitle("录用/转正申请-" + induction.getEmployedName());
		if (StringUtils.isBlank(induction.getId())) {// 首次提交
			induction.preInsert();
			inductionDao.insert(induction);
			startGet(induction);
		} else {// 驳回页面
			induction.preUpdate();
			inductionDao.update(induction);
			if ("yes".equals(induction.getAct().getFlag())) {
				induction.getAct().setComment("[重申]" + induction.getTitle());
				induction.setStatu("审核中");
				dao.updateStatu(induction);
			} else {
				induction.getAct().setComment("[销毁]" + induction.getAct().getComment());
				induction.setStatu("销毁");
				dao.updateStatu(induction);
				dao.delete(induction);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(induction.getAct().getFlag()) ? "1" : "0");
			vars.put("money", induction.getPositiveMoney());
			vars.put("money", induction.getPositiveMoney());
			String taskId = dao.selectTaskIdByProcinsId(induction.getAct().getProcInsId());
			actTaskService.complete(taskId, induction.getAct().getProcInsId(), induction.getAct().getComment(),
					induction.getTitle(), vars);
		}
	}

	@Transactional(readOnly = false)
	public void auditSave(Induction induction) {
		// 设置审核意见
		induction.getAct().setComment(
				("yes".equals(induction.getAct().getFlag()) ? "[同意]" : "[驳回]") + induction.getAct().getComment());
		induction.preUpdate();
		
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛财务总监");
		Map<String, Object> vars = Maps.newHashMap();
		/**流程正常走向 同意1*/
		vars.put("agree", "yes".equals(induction.getAct().getFlag()) ? "1" : "0");
		/**主管*/
		if (thd_a&&!thd_b) {
			induction.setPrfourText(induction.getAct().getComment());
			dao.updatePrfourText(induction);
		}
		/**财务总监*/
		if (thd_b) {
			induction.setPrsixText(induction.getAct().getComment());
			dao.updatePrsixText(induction);
		}
		if (!"yes".equals(induction.getAct().getFlag())) {
			induction.setStatu("驳回");
			dao.updateStatu(induction);
		}
		if ("yes".equals(induction.getAct().getFlag()) && thd_b) {// 同意并且当前审批人为研发总监，主管，财务总监则通过
			induction.setStatu("审核通过");
			dao.updateStatu(induction);
			/**流程结束走向 同意1*/
			vars.put("agree", "yes".equals(induction.getAct().getFlag()) ? "3" : "0");
		}
		actTaskService.complete(induction.getAct().getTaskId(), induction.getAct().getProcInsId(),
				induction.getAct().getComment(), vars);
	}

	/**
	 * 
	 * @Title: getByProcInsId @Description: 通过流程实例id获取业务表实体 @author:
	 * WangFucheng @param procInsId @return Signet 返回类型 @throws
	 */
	public Induction getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}

	public void startGet(Induction induction) {
		/**
		 * @update Mr.dong 
		 * 入职申请流程思路： 
		 * 首先获取当前人的上级领导 
		 * 1.如果直属上级是总裁 则：提交人--财务总监
		 * 2.如果直属上级是办事处级别负责人 则：提交人--直属上级--财务总监 
		 * 3.如果直属上级是部门级别负责人 则：提交人--直属上级的上级--财务总监
		 */
		Map<String, Object> vars = new HashMap<String, Object>();
		/** 获取当前人的直属上级 */
		String leaderId = UserUtils.getUser().getLeader();// 直属上级id
		String leaderName = UserUtils.get(leaderId).getName();// 直属上级姓名
		/** 获取直属上级的角色 */
		String roleStr = UserUtils.get(leaderId).getRoleNames();// 如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");//
		/** 判断是否为这一角色的人 */
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛总裁");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛部门级别负责人");
		if (thd_a) {
			vars.put("userTask1", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
		} else if (thd_b) {
			vars.put("userTask1", leaderName);
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
		} else if (thd_c) {
			/** 获取上级的上级领导姓名 */
			String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
			vars.put("userTask1", leader_leader);
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
		}

		actTaskService.startProcess(ActUtils.THD_INDUCTION[0], ActUtils.THD_INDUCTION[1], induction.getId(),
				induction.getTitle(), vars);
	}

	/**
	 * 行政人员查看所属区域申请列表
	 *
	 * @param page
	 * @param induction
	 * @return
	 */
	@Transactional(readOnly = false)
	public Page<Induction> findPage3(Page<Induction> page, Induction induction) {
		induction.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}

	/**
	 * 财务人员查看员工录用列表
	 *
	 * @param page
	 * @param induction
	 * @return
	 */
	@Transactional(readOnly = false)
	public Page<Induction> findPage4(Page<Induction> page, Induction induction) {
		induction.setPage(page);
		page.setList(dao.findList3(induction));
		return page;
	}
}
