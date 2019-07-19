package com.thinkgem.jeesite.modules.risk.controller;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.risk.entity.PageBean;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;
import com.thinkgem.jeesite.modules.risk.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping(value="${adminPath}/pms/page")
public class PageController extends BaseController{
	@Autowired
	private PageService pageService;	
	
	@RequestMapping(value="show")
	public String show(Model model,PageBean<RiskInfo> pageBean){
		Integer pageNum=1;
		Integer pageSize=15;
		pageBean.setPageNum(pageNum);
		pageBean.setPageSize(pageSize);
		System.out.println("控制器层");
		PageBean<RiskInfo>risk=pageService.findAllRiskInfo(pageBean);
		List<RiskInfo> list=risk.getList();
		for(RiskInfo r:list){
			System.out.println("风险的信息："+r);
		}
		System.out.println("risk:"+risk);
		model.addAttribute("risk", risk);
		Integer totalPage=risk.getTotalPage();
		System.out.println("totalPage"+totalPage);
		return "modules/risk/PageLimit";
	}
	
	@RequestMapping(value="limit")
	public String getPage(String pageNum ,Model model,PageBean<RiskInfo> pageBean){
		
		System.out.println("pageNumber:"+pageNum+1);
		Integer pageNumber=Integer.valueOf(pageNum);
		pageBean.setPageNum(pageNumber);
		Integer pageSize=15;
		pageBean.setPageSize(pageSize);
		PageBean<RiskInfo> risk=pageService.findAllRiskInfo(pageBean);
		List<RiskInfo> list=risk.getList();
		for(RiskInfo r:list){
			System.out.println("风险的信息："+r);
		}
		System.out.println("risk:"+risk);
		model.addAttribute("risk", risk);
		return "modules/risk/PageLimit";
	}	
}
