package com.thinkgem.jeesite.modules.receiptNotice.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.receiptNotice.entity.AjaxEntity;
import com.thinkgem.jeesite.modules.receiptNotice.entity.ReceiptNumber;
import com.thinkgem.jeesite.modules.receiptNotice.service.ReceiptNoticeService;
import com.thinkgem.jeesite.modules.receiptNotice.service.ReceiptNumberService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="${adminPath}/tb/receiptNumber")
public class ReceiptNumberController extends BaseController{
	
	/**
	 * 注入receiptNoticeService属性
	 */
	@Autowired
	private ReceiptNoticeService receiptNoticeService;
	
	/**
	 * 注入receiptNumberService属性
	 */
	@Autowired
	private ReceiptNumberService receiptNumberService;
	
	/**
	 * 返回数据到前台
	 * @return 返回1，则通知有新消息，否则没有新消息提示
	 */
	@ResponseBody
	@RequestMapping(value="find")
	public AjaxEntity find(){
		Integer count=receiptNoticeService.find();
		//数据存储实体类
		AjaxEntity ae=new AjaxEntity();
		if(UserUtils.getUser().getLoginName().equals(ActUtils.ACT_EXA_PURCHASE[1])
				||UserUtils.getUser().getLoginName().equals(ActUtils.ACT_EXA_LEADER[7])
				||UserUtils.getUser().getOffice().getName().equals("财务部")){
			ReceiptNumber rn=new ReceiptNumber();
			rn.setId(UserUtils.getUser().getId());
			rn.setCount(count);
			Integer num=receiptNumberService.find(rn);
			if(num!=count){
				//rn.setCount(count);
				receiptNumberService.insert(rn);
				ae.setCount(1);
				return ae;
			}
			ae.setCount(0);
		}
		/*else if(UserUtils.getUser().getOffice().getName().equals("财务部")){
			ReceiptNumber rn=new ReceiptNumber();
			rn.setId(UserUtils.getUser().getOffice().getId());
			rn.setCount(count);
			Integer num=receiptNumberService.find(rn);
			if(num!=count){
				rn.setCount(count);
				receiptNumberService.insert(rn);
				ae.setCount(1);
				return ae;
			}
			ae.setCount(0);
		}*/
		return ae;
	}
}
