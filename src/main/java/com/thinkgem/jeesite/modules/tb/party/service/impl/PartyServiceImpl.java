package com.thinkgem.jeesite.modules.tb.party.service.impl;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.party.dao.PartyDao;
import com.thinkgem.jeesite.modules.tb.party.entity.Party;
import com.thinkgem.jeesite.modules.tb.party.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @ClassName: PartyServiceImpl 
 * @Description: 团建申请服务层
 * @author: WangFucheng
 * @date 2018年8月7日 下午7:54:59 
 *
 */
@Service
public class PartyServiceImpl extends CrudService<PartyDao, Party> implements PartyService{
	@Autowired
	private ActTaskService actTaskService;
	@Autowired 
	private PartyDao partyDao;
	
	@Transactional(readOnly = false)
	public void save(Party party){
		if(StringUtils.isBlank(party.getId())){
			party.preInsert();
			dao.insert(party);
			startGet(party);
		}else{//驳回列表
			party.preUpdate();
			dao.update(party);
			if ("yes".equals(party.getAct().getFlag())) {
				party.getAct().setComment("[重申]" + party.getTitle());
				party.setStatu("审核中");
				partyDao.updateStatu(party);
			} else {
				party.getAct().setComment("[销毁]");
				party.setStatu("销毁");
				partyDao.updateStatu(party);
				dao.delete(party);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(party.getAct().getFlag()) ? "1" : "0");
			String taskId = partyDao.selectTaskIdByProcinsId(party.getAct().getProcInsId());
			actTaskService.complete(taskId, party.getAct().getProcInsId(),
					party.getAct().getComment(), party.getTitle(), vars);
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public void auditSave(Party party){
		Map<String, Object> vars = Maps.newHashMap();
		// 设置审核意见
		party.getAct().setComment(
				("yes".equals(party.getAct().getFlag()) ? "[同意]" : "[驳回]") + party.getAct().getComment());
		party.preUpdate();
		
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛财务总监");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛人事经理");
		/**主管*/
		if(thd_a&&!thd_b){
		}
		/**财务总监*/
		if(thd_b){
			
		}
		/**人事经理*/
		if(thd_c){
			
		}
		/**流程正常走向 同意1*/
		vars.put("agree", "yes".equals(party.getAct().getFlag()) ? "1" : "0");
		if(!"yes".equals(party.getAct().getFlag())){
			party.setStatu("驳回");
			partyDao.updateStatu(party);
		}
		/**如果为行政级别人员审核则改变流程走向 同意则agree==3结束*/
		if("yes".equals(party.getAct().getFlag())&&thd_b){
			party.setStatu("审核通过");
			partyDao.updateStatu(party);
			vars.put("agree", "yes".equals(party.getAct().getFlag()) ? "3" : "0");
		}
		
		actTaskService.complete(party.getAct().getTaskId(), party.getAct().getProcInsId(),
				party.getAct().getComment(), vars);
	
	}
	/**
	 * 
	 * @param party
	 */
	public void startGet(Party party){
		/**
		 * @update Mr.dong
		 * 团建流程思路
		 * 首先获取当前人的上级领导
		 * 1.如果直属上级是总裁
		 * 	则：提交人--人事经理--财务总监
		 * 	2.如果直属上级是办事处级别负责人
		 *  则：提交人--直属上级--人事经理--财务总监
		 *  3.如果直属上级是部门级别负责人
		 *  则：提交人--直属上级的上级--人事经理--财务总监
		 */
		Map<String, Object> vars = new HashMap<String,Object>();
		vars.put("money", party.getBudget());
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
			vars.put("userTask1",  UserUtils.getByRoleEnname("thd_renshi").get(0));
			vars.put("userTask2",  UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
		}else if(thd_b){
			vars.put("userTask1",  leaderName);
			vars.put("userTask2",  UserUtils.getByRoleEnname("thd_renshi").get(0));
			vars.put("userTask3",  UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
		}else if(thd_c){
			/**获取上级的上级领导姓名*/
			String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
			vars.put("userTask1",  leader_leader);
			vars.put("userTask2",  UserUtils.getByRoleEnname("thd_renshi").get(0));
			vars.put("userTask3",  UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
		}else{
			return;
		}
		actTaskService.startProcess(ActUtils.THD_PARTY[0], ActUtils.THD_PARTY[1], party.getId(), party.getTitle(), vars);
	}

	@Override
	public Party getByProcInsId(String procInsId) {
		
		return dao.getByProcInsId(procInsId);
	}

	public Page<Party> findAllList(Page<Party> page, Party dwa) throws Exception {
		dwa.setPage(page);
		page.setList(partyDao.getAllList(dwa));
		return page;
	}
}
