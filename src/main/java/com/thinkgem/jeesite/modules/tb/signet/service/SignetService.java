package com.thinkgem.jeesite.modules.tb.signet.service;


import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.signet.dao.SignetDao;
import com.thinkgem.jeesite.modules.tb.signet.entity.Signet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignetService extends CrudService<SignetDao,Signet> {

	
	@Autowired
	private ActTaskService actTaskService;
	
	@Transactional(readOnly = false)
	public void save(Signet signet){
		if(StringUtils.isBlank(signet.getId())){
			signet.preInsert();
			dao.insert(signet);
			//启动流程
			startGet(signet);
			
		}else{//驳回列表
			signet.preUpdate();
			dao.update(signet);
			if ("yes".equals(signet.getAct().getFlag())) {
				signet.getAct().setComment("[重申]" + signet.getTitle());
				signet.setStatu("审核中");
				dao.updateStatu(signet);
			} else {
				signet.getAct().setComment("[销毁]" + signet.getAct().getComment());
				signet.setStatu("销毁");
				dao.updateStatu(signet);
				dao.delete(signet);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(signet.getAct().getFlag()) ? "1" : "0");
			String taskId = dao.selectTaskIdByProcinsId(signet.getAct().getProcInsId());
			actTaskService.complete(taskId, signet.getAct().getProcInsId(),
					signet.getAct().getComment(), signet.getTitle(), vars);
		}
	}
	@Transactional(readOnly = false)
	public void auditSave(Signet signet){
		// 设置审核意见
		signet.getAct().setComment(
				("yes".equals(signet.getAct().getFlag()) ? "[同意]" : "[驳回]") + signet.getAct().getComment());
		signet.preUpdate();
		
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = signet.getAct().getTaskDefKey();
		// 各个审核环节
		
		if ("proneText".equals(taskDefKey)) {
			signet.setProneText(signet.getAct().getComment());
			dao.updateProneText(signet);
		}
		if ("prtwoText".equals(taskDefKey)) {
			signet.setPrtwoText(signet.getAct().getComment());
			dao.updatePrtwoText(signet);
		}
		if(!"yes".equals(signet.getAct().getFlag())){
			signet.setStatu("驳回");
			dao.updateStatu(signet);
		}
		if("yes".equals(signet.getAct().getFlag())&&"prtwoText".equals(taskDefKey)){
			signet.setStatu("审核通过");
			dao.updateStatu(signet);
		}
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(signet.getAct().getFlag()) ? "1" : "0");
		actTaskService.complete(signet.getAct().getTaskId(), signet.getAct().getProcInsId(),
				signet.getAct().getComment(), vars);
		
			
	}
	public void startGet(Signet signet){
		Map<String, Object> vars = new HashMap<String,Object>();
		String areaName = UserUtils.getUser().getOffice().getArea().getName();
		String userName = UserUtils.getUser().getLoginName();
		Boolean a = Arrays.asList(ActUtils.ACT_EXA_LEADER).contains(userName);
		if(a||"北京".equals(areaName)){
			actTaskService.startProcess(ActUtils.ACT_TB_SIGNET[1], ActUtils.ACT_TB_SIGNET[0], signet.getId(),
					signet.getTitle(), vars);
		}else if("合肥市".equals(areaName)){
			vars.put("prtwoText", ActUtils.ACT_EXA_DIRECTOR[0]);//合肥：迟笑甜
			actTaskService.startProcess(ActUtils.ACT_TB_SIGNET[2], ActUtils.ACT_TB_SIGNET[0], signet.getId(),
					signet.getTitle(), vars);
		}else if("济南市".equals(areaName)){
			vars.put("prtwoText", ActUtils.ACT_EXA_DIRECTOR[1]);//济南：刘明升
			actTaskService.startProcess(ActUtils.ACT_TB_SIGNET[2], ActUtils.ACT_TB_SIGNET[0], signet.getId(),
					signet.getTitle(), vars);
		}else if("太原市".equals(areaName)){
			vars.put("prtwoText", ActUtils.ACT_EXA_DIRECTOR[3]);//刘岩峰
			actTaskService.startProcess(ActUtils.ACT_TB_SIGNET[2], ActUtils.ACT_TB_SIGNET[0], signet.getId(),
					signet.getTitle(), vars);
		}else if("上海".equals(areaName)||"福州市".equals(areaName)){
			vars.put("prtwoText", ActUtils.ACT_EXA_DIRECTOR[4]);//徐哲君
			actTaskService.startProcess(ActUtils.ACT_TB_SIGNET[2], ActUtils.ACT_TB_SIGNET[0], signet.getId(),
					signet.getTitle(), vars);
		}else{
			vars.put("prtwoText", ActUtils.ACT_EXA_DIRECTOR[2]);//马建新
			actTaskService.startProcess(ActUtils.ACT_TB_SIGNET[2], ActUtils.ACT_TB_SIGNET[0], signet.getId(),
					signet.getTitle(), vars);
		}
		
	}
	public Signet getByProcInsId(String procInsId){
		return dao.getByProcInsId(procInsId);
	}
	public Page<Signet> findEmployeesPage(Page<Signet> page, Signet signet) {
		signet.setPage(page);
		page.setList(dao.findEmployeesList(signet));
		return page;
	}
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param signet
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Signet> findPage3(Page<Signet> page, Signet signet) {
		signet.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}
}
