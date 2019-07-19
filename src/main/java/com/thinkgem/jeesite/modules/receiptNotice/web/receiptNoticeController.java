package com.thinkgem.jeesite.modules.receiptNotice.web;


import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.receiptNotice.entity.ReceiptNotice;
import com.thinkgem.jeesite.modules.receiptNotice.service.ReceiptNoticeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping(value="${adminPath}/tb/receiptNotice")
public class receiptNoticeController extends BaseController{
	
	/**
	 * 注入service层属性
	 */
	@Autowired
	private ReceiptNoticeService receiptNoticeService;
	
	/**
	 * 最新执行，通过id获取数据
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public ReceiptNotice get(String id){
		ReceiptNotice receiptNotice=null;
		if(StringUtils.isNotBlank(id)){
			receiptNotice=receiptNoticeService.get(id);
		}
		if(receiptNotice==null){
			receiptNotice=new ReceiptNotice();
		}
		return receiptNotice;
	}
	
	/**
	 * 展示表单页面
	 * @param model
	 * @param receiptNotice
	 * @return
	 */
	@RequestMapping(value="add")
	public String add(Model model,ReceiptNotice receiptNotice){
		receiptNotice.setUser(UserUtils.getUser());
		receiptNotice.setOffice(UserUtils.getUser().getOffice());
		model.addAttribute("receiptNotice", receiptNotice);
		
		return "modules/receiptNotice/form";
	}
	
	/**
	 * 提交表单并保存至数据库
	 * @param receiptNotice
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="save")
	public String save(ReceiptNotice receiptNotice,RedirectAttributes redirectAttributes){
		receiptNotice.setUser(UserUtils.getUser());
		receiptNotice.setOffice(UserUtils.getUser().getOffice());
		receiptNoticeService.save(receiptNotice);
		
		return "redirect:" + adminPath + "/tb/receiptNotice/list";
	}
	
	/**
	 * 提交人列表页面
	 * @param model
	 * @param receiptNotice
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(Model model,ReceiptNotice receiptNotice,HttpServletRequest request, HttpServletResponse response){
		receiptNotice.setUser(UserUtils.getUser());
		Page<ReceiptNotice> page = receiptNoticeService.findPages(new Page<ReceiptNotice>(request, response), receiptNotice);
		model.addAttribute("page", page);
		
		return "modules/receiptNotice/list";
	}
	
	/**
	 * 详情页面
	 * @param model
	 * @param receiptNotice
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(Model model,ReceiptNotice receiptNotice){
		String view="view";
		model.addAttribute("receiptNotice",receiptNotice);

		return "modules/receiptNotice/"+view;
	}
	
	/**
	 * 员工列表页面
	 * @param model
	 * @param receiptNotice
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="list2")
	public String list2(Model model,ReceiptNotice receiptNotice,HttpServletRequest request, HttpServletResponse response){
		receiptNotice.setUser(UserUtils.getUser());
		Page<ReceiptNotice> page = receiptNoticeService.findPages2(new Page<ReceiptNotice>(request, response), receiptNotice);
		model.addAttribute("page", page);
		
		return "modules/receiptNotice/list2";
	}
	
	/**
	 * 删除对应数据
	 * @param receiptNotice
	 * @return
	 */
	@RequestMapping(value="del")
	public String delete(ReceiptNotice receiptNotice){
		receiptNoticeService.delete(receiptNotice);
		return "redirect:" + adminPath + "/tb/receiptNotice/list";
	}
	
	
	
	
	
}
