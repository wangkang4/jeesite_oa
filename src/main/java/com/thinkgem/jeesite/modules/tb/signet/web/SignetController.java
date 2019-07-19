package com.thinkgem.jeesite.modules.tb.signet.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.signet.entity.Signet;
import com.thinkgem.jeesite.modules.tb.signet.service.SignetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping(value="${adminPath}/tb/signet")
public class SignetController extends BaseController{
	@Autowired
	private SignetService signetService;
	
	@ModelAttribute
	public Signet get(@RequestParam(required = false) String id){
		Signet signet = null;
		if(StringUtils.isNotBlank(id)){
			signet = signetService.get(id);
		}
		if(signet ==null){
			signet = new Signet();
		}
		return signet;
	}
	
	@RequestMapping(value="list")
	public String list(Signet signet,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		signet.setUser(UserUtils.getUser());
		Page<Signet> page = signetService.findPage(new Page<Signet>(request,response), signet);
		model.addAttribute("page",page);
		
		return "modules/tb/signet/signetList";
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param signet
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list2")
	public String list2(Signet signet,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Signet> page =signetService.findPage3(new Page<Signet>(request,response), signet);
		model.addAttribute("page",page);
		return "modules/tb/signet/signetList2";
	}
	
	@RequestMapping("employeesList")
	public String employeesList(Signet signet,Act act,
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		Page<Signet> page = signetService.findEmployeesPage(new Page<Signet>(request,response), signet);
		model.addAttribute("page", page);
		model.addAttribute("signet", signet);
		return "modules/tb/signet/signetEmployeesList";
	}
	
	@RequestMapping(value="toAdd")
	public String toAdd(Model model,Signet signet){
		signet.setUser(UserUtils.getUser());
		signet.setApplyDate(new Date());
		model.addAttribute("signet", signet);
		model.addAttribute("name",UserUtils.getUser().getName());
		model.addAttribute("applyDate",new Date());
		return "modules/tb/signet/addSignet";
	}
	@RequestMapping(value="add")
	public String add(Signet signet,Model model, RedirectAttributes redirectAttributes) throws ParseException{
		signet.setProneText(null);
		signet.setUser(UserUtils.getUser());
		signet.setApplyDate(new Date());	
		signet.setTitle("印章刻制-"+signet.getUser().getOffice().getName()+"-"+signet.getUser().getName());
		signetService.save(signet);
		return "redirect:" + adminPath + "/tb/signet/list";
	}
	@RequestMapping(value="form")
	public String form(Signet signet,Model model){
		String view = "signetView";
		if(StringUtils.isNotBlank(signet.getId())){
			// 获取环节ID
			String taskDefKey = signet.getAct().getTaskDefKey();
			//查看用印
			if(signet.getAct().isFinishTask()){
				view = "signetView";
			}else if("proneText".equals(taskDefKey)){
				view = "signetAudit";
			}else if("prtwoText".equals(taskDefKey)){
				view = "signetAudit";
			}else if("signetUpdate".equals(taskDefKey)){
				view="addSignet";
			}
		}
		model.addAttribute("signet", signet);
		return "modules/tb/signet/" + view;
	}
	@RequestMapping(value="saveAudit")
	public String saveAudit(Signet signet,Model model){
		signetService.auditSave(signet);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
}
