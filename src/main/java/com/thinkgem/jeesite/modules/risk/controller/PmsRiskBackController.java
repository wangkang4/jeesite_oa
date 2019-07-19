package com.thinkgem.jeesite.modules.risk.controller;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.risk.entity.RiskBack;
import com.thinkgem.jeesite.modules.risk.service.RiskInfoBackService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.UUID;
@Controller
@RequestMapping(value= "${adminPath}/pms/riskback")
public class PmsRiskBackController  extends BaseController{
	
	
	@Autowired
	private RiskInfoBackService  riskInfoBackService;
	
	RiskBack back;
	/**
	 * 展示风险反馈界面
	 */
	@RequestMapping(value="showback")
	public String showFeedBack(String id,Model model){
		System.out.println("传入参数的id:"+id);
		//通过风险id查询对应的反馈信息
		back=riskInfoBackService.findRiskBackById(id);
		
		//System.out.println("riskid:"+back.getRiskId());
		model.addAttribute("back", back);	
		model.addAttribute("id", id);
		return "modules/risk/addFeedBack";
	}
	
	/**
	 * 修改反馈信息
	 */
	@RequestMapping(value="update")
	public String updateRiskBack(RiskBack riskBack,String id){
		//执行修改反馈信息表
		System.out.println("riskId:"+riskBack.getRiskId());
		System.out.println("solveTime:"+riskBack.getSolveTime());
		System.out.println("riskContent:"+riskBack.getRiskContent());
		//判断反馈表的id是否为空
		System.out.println("back.id:"+back);
		if(back.getId()!=null){
			//不为空则执行修改方法
			System.out.println("执行修改？");
			riskBack.setRiskId(id);
			riskBack.setUpdateDate(new Date ());
			int id1=riskInfoBackService.updateRiskBack(riskBack);
			System.out.println("修改："+id);
		}else{
			//为空则执行插入方法
			
			System.out.println("走到else了？id:"+id);
			riskBack.setCreateDate(new Date ());
			riskBack.setUpdateDate(new Date ());
			riskBack.setId(UUID.randomUUID().toString());
			riskBack.setRiskId(id);	
			riskBack.setCreateBy(UserUtils.getUser());	
			riskBack.setUpdateBy(UserUtils.getUser());
			System.out.println("插入:"+id);
			int id2=riskInfoBackService.addRiskBack(riskBack);
		}
		
		
		return "redirect:/a/pms/risk/list";
	}
}
