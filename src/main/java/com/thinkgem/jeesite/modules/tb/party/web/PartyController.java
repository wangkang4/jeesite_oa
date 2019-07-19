package com.thinkgem.jeesite.modules.tb.party.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.party.entity.Party;
import com.thinkgem.jeesite.modules.tb.party.service.impl.PartyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @ClassName: PartyController 
 * @Description: 团建申请控制层
 * @author: WangFucheng
 * @date 2018年8月6日 下午2:45:45 
 *
 */
@Controller
@RequestMapping(value="${adminPath}/tb/party")
public class PartyController extends BaseController{
	@Autowired
	private PartyServiceImpl partyService;
	/**
	 * 
	 * @Title: get
	 * @Description: 执行其他方法前先执行该方法，通过id查询实体类
	 * @author: WangFucheng
	 * @param id
	 * @return Party   返回类型 
	 * @throws
	 */
	@ModelAttribute
	public Party get(@RequestParam(required = false) String id){
		Party party = null;
		if(StringUtils.isNotBlank(id)){
			party = partyService.get(id);
		}
		if(party ==null){
			party = new Party();
		}
		return party;
	}
	/**
	 * 
	 * @Title: list
	 * @Description: 前往列表页面
	 * @author: WangFucheng
	 * @param party
	 * @param request
	 * @param response
	 * @param model
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping("list")
	public String list(Party party,HttpServletRequest request,HttpServletResponse response,Model model){
		party.setCreateBy(UserUtils.getUser());
		Page<Party> page = partyService.findPage(new Page<Party>(request, response), party);
		model.addAttribute("page", page);
		return "modules/tb/party/partyList";
	}
	/**
	 * 
	 * @Title: form
	 * @Description: 审批或者查看详情时通过此方法
	 * @author: WangFucheng
	 * @param party
	 * @param model
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping("form")
	public String form(Party party,Model model){
		String view = "partyView";
		if(StringUtils.isNotBlank(party.getId())){
			// 获取环节ID
			String taskDefKey = party.getAct().getTaskDefKey();
			if(party.getAct().isFinishTask()){
				view = "partyView";
			}else if("userTask1".equals(taskDefKey)){
				view = "partyAudit";
			}else if("userTask2".equals(taskDefKey)){
				view = "partyAudit";
			}else if("userTask3".equals(taskDefKey)){
				view = "partyAudit";
			}else if("modifyTask".equals(taskDefKey)){
				view="toAdd";
			}
		}
		model.addAttribute("party", party);
		return "modules/tb/party/"+view;
	}
	/**
	 * 
	 * @Title: toAdd
	 * @Description: 添加团建活动页面
	 * @author: WangFucheng
	 * @param induction
	 * @param model
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping("toAdd")
	public String toAdd(Party party,Model model){
		model.addAttribute("party", party);
		return "modules/tb/party/addParty";
	}
	/**
	 * 
	 * @Title: save
	 * @Description: 点击提交申请时调用该方法
	 * @author: WangFucheng
	 * @param party
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping("save")
	public String save(Party party){
		party.setTitle("团建申请-"+UserUtils.getUser().getName()+"-"+party.getBudget()+"元");
		partyService.save(party);
		return "redirect:" + adminPath + "/tb/party/list";
	}
	/**
	 * 
	 * @Title: saveAudit
	 * @Description: 审批人审批时调用该方法
	 * @author: WangFucheng
	 * @param party
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping("saveAudit")
	public String saveAudit(Party party){
		partyService.auditSave(party);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}


	/**
	 * 应财务总监俞伶群提的需求给加的：
	 * 想要看看所有员工提的团建
	 * 就查查数据给她看
	 */
	@RequestMapping("getAllList")
	public String getAllList(Party party,HttpServletRequest request,HttpServletResponse response,Model model){

		Page<Party> page = new Page<>(request,response);
		try {
			page = partyService.findAllList(new Page<Party>(request, response), party);
		}catch (Exception e){
			e.printStackTrace();
		}
		model.addAttribute("page", page);
		return "modules/tb/party/getAllList";
	}
}
