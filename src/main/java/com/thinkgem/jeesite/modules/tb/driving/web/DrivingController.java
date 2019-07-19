package com.thinkgem.jeesite.modules.tb.driving.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.driving.entity.Driving;
import com.thinkgem.jeesite.modules.tb.driving.service.DrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 
 * @ClassName: DrivingController 
 * @Description: 自驾车申请Controller层
 * @author: WangFucheng
 * @date 2018年7月16日 下午3:07:09 
 *
 */
@Controller
@RequestMapping(value="${adminPath}/tb/driving")
public class DrivingController extends BaseController{
	@Autowired
	private DrivingService drivingService;
	/**
	 * 
	 * @Title: get
	 * @Description: 根据id获取实体类，在所有controller方法前执行
	 * @author: WangFucheng
	 * @param id
	 * @return Driving   返回类型 
	 * @throws
	 */
	@ModelAttribute
	public Driving get(@RequestParam(required = false) String id){
		Driving driving = null;
		if(StringUtils.isNotBlank(id)){
			driving = drivingService.get(id);
		}
		if(driving ==null){
			driving = new Driving();
		}
		return driving;
	}
	/**
	 * 
	 * @Title: list
	 * @Description: 获取当前用户的自驾车出差申请列表
	 * @author: WangFucheng
	 * @param driving 自驾车出差申请表实体类
	 * @param request 
	 * @param response
	 * @param model
	 * @return String  jsp页面 
	 * @throws
	 */
	@RequestMapping("list")
	public String list(Driving driving,HttpServletRequest request, HttpServletResponse response, Model model){
		driving.setCreateBy(UserUtils.getUser());
		Page<Driving> page = drivingService.findPage(new Page<Driving>(request,response), driving);
		model.addAttribute("page", page);
		return "modules/tb/driving/drivingList";
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param special
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="drivingList2")
	public String drivingList2(Driving driving,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		
		Page<Driving> page =drivingService.findPage2(new Page<Driving>(request,response), driving);
		model.addAttribute("page",page);
		return "modules/tb/driving/drivingList2";
	}
	
	/**
	 * 
	 * @Title: employeesList
	 * @Description: 获取所有员工列表
	 * @author: WangFucheng
	 * @param driving
	 * @param request
	 * @param response
	 * @param model
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping("employeesList")
	public String employeesList(Driving driving,
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		Page<Driving> page = drivingService.findEmployeesPage(new Page<Driving>(request,response), driving);
		model.addAttribute("page", page);
		model.addAttribute("driving", driving);
		return "modules/tb/driving/drivingEmployeesList";
	}
	/**
	 * 
	 * @Title: toAdd
	 * @Description: 跳转到自驾车申请页面（提交或者驳回修改时调用）
	 * @author: WangFucheng
	 * @param driving 自驾车申请实体类
	 * @param model
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping("toAdd")
	public String toAdd(Driving driving,Model model) {
		driving.setCreateBy(UserUtils.getUser());
		driving.setCreateDate(new Date());
		model.addAttribute("driving", driving);
		return "modules/tb/driving/addDriving";
	}
	/**
	 * 
	 * @Title: save
	 * @Description: 点击提交申请时执行该方法
	 * @author: WangFucheng
	 * @param driving
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping("save")
	public String save(Driving driving){
		drivingService.save(driving);
		return "redirect:" + adminPath + "/tb/driving/list";
	}
	/**
	 * 
	 * @Title: form
	 * @Description: 详情页面跳转
	 * @author: WangFucheng
	 * @param driving
	 * @param model
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping(value="form")
	public String form(Driving driving,Model model){
		String view = "drivingAudit";
		if(StringUtils.isNotBlank(driving.getId())){
			// 获取环节ID
			String taskDefKey = driving.getAct().getTaskDefKey();
			//查看用印
			if(driving.getAct().isFinishTask()){
				view = "drivingView";
			}else if("modifyTask".equals(taskDefKey)){
				view="addInduction";
			}
		}
		model.addAttribute("driving", driving);
		return "modules/tb/driving/" + view;
	}
	/**
	 * 
	 * @Title: saveAudit
	 * @Description: 审批同意或驳回调用该方法
	 * @author: WangFucheng
	 * @param driving
	 * @param model
	 * @return String   返回类型 
	 * @throws
	 */
	@RequestMapping(value="saveAudit")
	public String saveAudit(Driving driving,Model model){
		drivingService.auditSave(driving);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
}
