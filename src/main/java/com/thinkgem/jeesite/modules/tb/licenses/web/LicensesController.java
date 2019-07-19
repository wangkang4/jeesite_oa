package com.thinkgem.jeesite.modules.tb.licenses.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.licenses.entity.Licenses;
import com.thinkgem.jeesite.modules.tb.licenses.service.LicensesService;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value="${adminPath}/tb/licenses")
public class LicensesController extends BaseController{
	@Autowired
	private LicensesService licensesService;
	
	@ModelAttribute
	public Licenses get(@RequestParam(required = false) String id){
		Licenses licenses = null;
		if(StringUtils.isNotBlank(id)){
			licenses = licensesService.get(id);
		}
		if(licenses ==null){
			licenses = new Licenses();
		}
		return licenses;
	}
	
	@RequestMapping(value="list")
	public String list(Licenses licenses,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		licenses.setUser(UserUtils.getUser());
		Page<Licenses> page = licensesService.findPage(new Page<Licenses>(request,response), licenses);
		model.addAttribute("page",page);
		
		return "modules/tb/licenses/licensesList";
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param licenses
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list2")
	public String list2(Licenses licenses,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Licenses> page =licensesService.findPage3(new Page<Licenses>(request,response), licenses);
		model.addAttribute("page",page);
		return "modules/tb/licenses/licensesList2";
	}
	
	@RequestMapping("employeesList")
	public String employeesList(Licenses licenses,Act act,
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		Page<Licenses> page = licensesService.findEmployeesPage(new Page<Licenses>(request,response), licenses);
		model.addAttribute("page", page);
		model.addAttribute("licenses", licenses);
		return "modules/tb/licenses/licensesEmployeesList";
	}
	
	@RequestMapping(value="toAdd")
	public String toAdd(Model model,Licenses licenses){
		licenses.setUser(UserUtils.getUser());
		licenses.setApplyDate(new Date());
		model.addAttribute("licenses", licenses);
		model.addAttribute("name",UserUtils.getUser().getName());
		model.addAttribute("applyDate",new Date());
		return "modules/tb/licenses/addLicenses";
	}
	@RequestMapping(value="add")
	public String add(Licenses licenses,Model model, RedirectAttributes redirectAttributes) throws ParseException{
		licenses.setProneText(null);
		licenses.setPrtwoText(null);
		licenses.setPrthreeText(null);
		licenses.setUser(UserUtils.getUser());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		licenses.setApplyDate(new Date());
		licenses.setUseDate(sdf.parse(sdf.format(licenses.getUseDate())));
		licenses.setReturnDate(sdf.parse(sdf.format(licenses.getReturnDate())));
		licenses.setTitle("证照申请-"+UserUtils.getUser().getOffice().getName()+"-"+UserUtils.getUser().getName());
		licensesService.save(licenses);
		return "redirect:" + adminPath + "/tb/licenses/list";
	}
	@RequestMapping(value="form")
	public String form(Licenses licenses,Model model){
		String view = "licensesView";
		if(StringUtils.isNotBlank(licenses.getId())){
			// 获取环节ID
			String taskDefKey = licenses.getAct().getTaskDefKey();
			//查看用印
			if(licenses.getAct().isFinishTask()){
				view = "licensesView";
			}else if("userTask1".equals(taskDefKey)){
				view = "licensesAudit";
			}else if("userTask2".equals(taskDefKey)){
				view = "licensesAudit";
			}else if("userTask3".equals(taskDefKey)){
				view = "licensesAudit";
			}else if("modifyTask".equals(taskDefKey)){
				view="addLicenses";
			}
		}
		model.addAttribute("licenses", licenses);
		return "modules/tb/licenses/" + view;
	}
	@RequestMapping(value="saveAudit")
	public String saveAudit(Licenses licenses,Model model){
		licensesService.auditSave(licenses);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
}
