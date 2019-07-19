package com.thinkgem.jeesite.modules.risk.controller;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;
import com.thinkgem.jeesite.modules.risk.service.PageService;
import com.thinkgem.jeesite.modules.risk.service.RiskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
@Controller
@RequestMapping(value = "${adminPath}/pms/risk")
public class PmsRiskController extends BaseController {
	
	@Autowired
	private RiskInfoService riskInfoService;
	
	
	@Autowired
	private PageService pageService;
	
//	@RequestMapping(value="address")
//	public String getAddress(){
//		return "modules/risk/address_list";
//	}
	
//	@RequestMapping(value="show")
//	public String show(Model model,PageBean<RiskInfo> pageBean){
//		System.out.println("pageBean:"+pageBean);
//		if(pageBean!=null){
//			if(pageBean.getRiskInfo()!=null){
//				if(pageBean.getRiskInfo().getPmsPro()!=null){
//					if(pageBean.getRiskInfo().getPmsPro().getProjectName()!=null){
//						model.addAttribute("projectName", pageBean.getRiskInfo().getPmsPro().getProjectName());
//					}
//				}	
//			}
//		}
//		if(pageBean!=null){
//			if(pageBean.getRiskInfo()!=null){
//				if(pageBean.getRiskInfo().getRiskState()!=null){
//					model.addAttribute("riskState", pageBean.getRiskInfo().getRiskState());
//				}
//			}
//		}
//		Integer pageNum=1;
//		Integer pageSize=15;
//		pageBean.setPageNum(pageNum);
//		pageBean.setPageSize(pageSize);
//		System.out.println("控制器层");
//		PageBean<RiskInfo>risk=pageService.findAllRiskInfo(pageBean);
//		List<RiskInfo> list=risk.getList();
//		for(RiskInfo r:list){
//			System.out.println("风险的信息："+r);
//		}
//		System.out.println("risk:"+risk);
//		model.addAttribute("risk", risk);
//		Integer totalPage=risk.getTotalPage();
//		System.out.println("totalPage"+totalPage);
//		return "modules/risk/PageLimit";
//	}
	
//	@RequestMapping(value="limit")
//	@ResponseBody
//	public Map<String,PageBean<RiskInfo>> getPage(String pageNum ,Model model,PageBean<RiskInfo> pageBean,String projectName,String riskState){
//		Map<String,PageBean<RiskInfo>> map=new HashMap<String, PageBean<RiskInfo>>();
//		System.out.println("projectName:"+projectName);
//		System.out.println("riskState:"+riskState);
//		PmsProject pp = new PmsProject();
//		pp.setProjectName(projectName);
//		RiskInfo ri = new RiskInfo();
//		ri.setPmsPro(pp);
//		ri.setRiskState(riskState);
//		pageBean.setRiskInfo(ri);
//		System.out.println("设置项目名称属性");
//		System.out.println("Set.projectName:"+pageBean.getRiskInfo().getPmsPro().getProjectName());
//		if(pageBean!=null&&pageBean.getRiskInfo()!=null){
//			pageBean.getRiskInfo().setRiskState(riskState);
//			System.out.println("设置风险状态属性");
//			System.out.println("Set.riskState:"+pageBean.getRiskInfo().getRiskState());
//		}
//		System.out.println("projectName:"+projectName);
//		System.out.println("pageNum:"+pageNum);
//		Integer pageNumber=Integer.valueOf(pageNum);
//		pageBean.setPageNum(pageNumber);
//		Integer pageSize=15;
//		pageBean.setPageSize(pageSize);
//		PageBean<RiskInfo> risk=pageService.findAllRiskInfo(pageBean);
//		map.put("risk", risk);
//		List<RiskInfo> list=risk.getList();
//		for(RiskInfo r:list){
//			System.out.println("风险的信息："+r);
//		}
//		System.out.println("risk:"+risk);
//		
//		return map;
//	}	
	
	/**
	 * 
	 * 生成信息列表界面
	 * @param riskInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String findList(RiskInfo riskInfo,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<RiskInfo> page=riskInfoService.findPage(new Page<RiskInfo> (request, response),riskInfo);
//		System.out.println("request:"+request.getSession().getServletContext().getRealPath("/upload"));
		model.addAttribute("page", page);
		return "modules/risk/riskManager";
	}
	
	
	
	
	/**展示增加信息界面**/
	@RequestMapping(value="addrisk")
	public String showAddRisk(HttpServletRequest request){
		List<String> list=riskInfoService.getProjectName();
		request.setAttribute("list", list);
		return  "modules/risk/RiskInfoAdd";
	}
	/**展示修改信息界面*/
	@RequestMapping(value="updaterisk")
	public String showUpdateRisk(String id,Model model){
			RiskInfo risk=riskInfoService.findRiskInfoById(id);
//			System.out.println("riskAnswer:"+risk.getRiskAnswer());
			model.addAttribute("risk", risk);
		return  "modules/risk/UpdateRiskInfo";
	}
	
	/**添加风险信息*/
	@RequestMapping(value="add")
	public String addRisk(RiskInfo riskInfo,String projectName){
		//测试前端提交数据
		//通过项目名称获取项目id
//		System.out.println("projectName:"+projectName);
		String i=riskInfoService.findProjectId(projectName);
//		System.out.println("addRiskInfo:"+riskInfo.getResponseId());
//		System.out.println("expecteTime:"+riskInfo.getExpecteTime());
//		System.out.println("i:"+i);
		//把id和i封装到RiskInfo中
		riskInfo.setProId(i);
		riskInfo.setId(UUID.randomUUID().toString());
		Date day=new Date();  
		riskInfo.setCreateDate(day);
		//向风险信息表中插入数据
		riskInfoService.insertNewRiskInfo(riskInfo);
		return  "redirect:/a/pms/risk/list";
	}
	
	/**修改风险信息*/
	@RequestMapping(value="update")
	public String updateRisk(RiskInfo riskInfo,String responseName,String responseName1){
//		System.out.println("responseName:"+responseName);
//		System.out.println("responseName:"+responseName1);
//		System.out.println("riskDescrible:"+riskInfo.getRiskDescrible());
		//通过责任人姓名获取责任人id
		String id=riskInfoService.findId(responseName);
//		System.out.println("id:"+id);
		Date day=new Date();  
		riskInfo.setUpdateDate(day);
		riskInfo.setResponseId(id);
//		System.out.println("responseId:"+riskInfo.getResponseId());
		riskInfoService.updateRiskInfo(riskInfo);
		return  "redirect:/a/pms/risk/list";
	}
	
	/**
	 * 通过项目名获取项目id
	 * @param projectName
	 * @return
	 */
	@RequestMapping(value="getProjectId")
	@ResponseBody
	public Map<String,String> getprojectId(String projectName){
		Map<String, String> map = new HashMap<String,String>();
//		System.out.println("projectName:"+projectName);
		String data =null;
		data=riskInfoService.findProjectId(projectName);
//		System.out.println("data:"+data);
		if(data!=null&&data!=""){
			map.put("data", "ok");
		}else{
			map.put("data","error");
		}
		return map;
	}

	/**通过一条风险id删除数据*/
	@RequestMapping(value="delete")
	public String deleteRiskInfo(String id){
		riskInfoService.deleteRiskInfo(id);
		return  "redirect:/a/pms/risk/list";
	}	
}
