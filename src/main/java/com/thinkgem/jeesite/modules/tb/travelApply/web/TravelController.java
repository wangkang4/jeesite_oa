package com.thinkgem.jeesite.modules.tb.travelApply.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.tool.PinyinTool;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.travelApply.entity.TravelApply;
import com.thinkgem.jeesite.modules.tb.travelApply.entity.TravelPlan;
import com.thinkgem.jeesite.modules.tb.travelApply.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 出差申请
 * @author Mr.dong
 *
 */
@Controller
@RequestMapping(value="${adminPath}/tb/travelApply")
public class TravelController extends BaseController{
	/**
	 * 依赖注入service层
	 */
	@Autowired
	private TravelService travelService;
	
	/**
	 * 最先执行
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public TravelApply get(String id){
		TravelApply travelApply = null;
		if(StringUtils.isNotBlank(id)){
			travelApply=travelService.get(id);
		}
		if(travelApply==null){
			travelApply=new TravelApply();
		}
		return travelApply;
	}
	
	/**
	 * 显示表单填写页面
	 * @param travelApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add")
	public String add(TravelApply travelApply,Model model){
		travelApply.setUser(UserUtils.getUser());
		travelApply.setOffice(UserUtils.getUser().getOffice());
		travelApply.setCreateDate(new Date());
		model.addAttribute("travleApply", travelApply);
		/*附表信息*/
		List<TravelPlan> list=travelService.findPlan(travelApply.getPlanId());
		model.addAttribute("list", list);
		
		return "modules/tb/travelApply/add";
	}
	
	/**
	 * 提交表单信息将数据插入两张表中
	 * @param travelApply
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toAdd")
	public String toAdd(TravelApply travelApply,HttpServletRequest request){
		travelApply.setUser(UserUtils.getUser());
		travelApply.setOffice(UserUtils.getUser().getOffice());
		travelApply.setProneText(null);
		travelApply.setPrtwoText(null);
		/*判断是否为负表数据是否有值*/
		if(StringUtils.isNotBlank(travelApply.getPlanId())){
			travelService.deletePlan(travelApply.getPlanId());
		}
		/*设置负表关联ID*/
		travelApply.setPlanId(IdGen.uuid());
		/*获取行程计划信息*/
		String[] planDate=request.getParameterValues("planDate");//行程日期
		String[] customer_name=request.getParameterValues("customerName");//行程客户名
		String[] content=request.getParameterValues("content");//行程工作内容
		for(int i=0;i<planDate.length;i++){
			TravelPlan travelPlan=new TravelPlan();
			travelPlan.setId(travelApply.getPlanId());
			travelPlan.setPlanDate(planDate[i]);
			travelPlan.setCustomerName(customer_name[i]);
			travelPlan.setContent(content[i]);
			travelService.insertPlan(travelPlan);//将负表数据插入
		}
		/*获取项目名称的拼音*/
		String data = PinyinTool.getPinYinHeadChar(travelApply.getProject()).toUpperCase();
		/*获取当前时间*/
		String MM=String.valueOf(System.currentTimeMillis());
		travelApply.setNum(data+"-"+MM);//设置出差编号的值
		travelService.save(travelApply);
		return "redirect:" + adminPath + "/tb/travelApply/list";
	}
	
	/**
	 * 申请人列表页面
	 * @param model
	 * @param travelApply
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(Model model,TravelApply travelApply,HttpServletRequest request, HttpServletResponse response){
		travelApply.setUser(UserUtils.getUser());
		Page<TravelApply> page=travelService.findPage(new Page<TravelApply>(request, response),travelApply);
		model.addAttribute("page", page);
		return "modules/tb/travelApply/list";
	}
	
	/**
	 * 详情页面
	 * @param model
	 * @param travelApply
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(Model model,TravelApply travelApply){
		String view="view";
		if(StringUtils.isNotBlank(travelApply.getId())){
			//获取环节ID
			String taskDefKey=travelApply.getAct().getTaskDefKey();
			if(travelApply.getAct().isFinishTask()){
				view="view";
			}else if("userTask1".equals(taskDefKey)){
				view="audit";
			}else if("modifyTask".equals(taskDefKey)){
				view="add";
			}
		}
		model.addAttribute("travelApply", travelApply);
		/*获取负表数据，封装成集合对象*/
		List<TravelPlan> list=travelService.findPlan(travelApply.getPlanId());
		model.addAttribute("list", list);
		return "modules/tb/travelApply/"+view;
	}
	
	/**
	 * 审核流程状态
	 * @param model
	 * @param travelApply
	 * @return
	 */
	@RequestMapping(value="saveAudit")
	public String saveAudit(Model model,TravelApply travelApply){
		travelService.auditSave(travelApply);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
	
	/**
	 * 撤销流程
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="back")
	public String back(TravelApply travelApply){
		travelService.deletetravelApply(travelApply.getProcInsId());//删除主表信息
		travelService.deleteTask(travelApply.getProcInsId());//删除执行流程的表的数据
		travelService.deletePlan(travelApply.getPlanId());//删除负表数据
		return "redirect:"+adminPath+"/tb/travelApply/list";
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param travelApply
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list2")
	public String list2(TravelApply travelApply,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<TravelApply> page =travelService.findPage3(new Page<TravelApply>(request,response), travelApply);
		model.addAttribute("page",page);
		return "modules/tb/travelApply/list2";
	}

	/**
	 * 财务人员查看员工申请列表
	 * @param travelApply
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="listCaiWu")
	public String listCaiWu(TravelApply travelApply,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<TravelApply> page =travelService.findListCaiWu(new Page<TravelApply>(request,response), travelApply);
		model.addAttribute("page",page);
		return "modules/tb/travelApply/listCaiWu";
	}
}
