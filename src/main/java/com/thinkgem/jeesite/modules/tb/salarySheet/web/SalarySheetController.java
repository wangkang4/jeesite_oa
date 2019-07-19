package com.thinkgem.jeesite.modules.tb.salarySheet.web;


import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.tb.salarySheet.entity.SalarySheet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author wangzhichao
 * @date 2019-4-22
 */
@Controller
@RequestMapping(value="${adminPath}/tb/salary")
public class SalarySheetController extends BaseController {


	@RequestMapping(value="list")
	public String list(){
		return "modules/tb/salarySheet/salaryList";
	}
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(Model model, MultipartFile file, RedirectAttributes redirectAttributes, SalarySheet salary) {
		try {
			ImportExcel ei = new ImportExcel(file, 3, 0);
			List<SalarySheet> list = ei.getDataList(SalarySheet.class);
			model.addAttribute("list", list);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}finally {
			model.addAttribute("sa", salary);
		}
		return "modules/tb/salarySheet/salaryList";
	}
	/**
	 *
	 * @Title: email
	 * @Description: 发送到邮箱
	 * @author: WangFucheng
	 * @param salary
	 * @return
	 * @throws Exception Map<String,String>   返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="email")
	public Map<String,String> email(SalarySheet salary){
		Map<String, String> map = new HashMap<String,String>();
		try {
			SalarySheetController.send(salary);
			map.put("success", "ok");
		} catch (Exception e) {
			map.put("success", "error");
			e.printStackTrace();
		}
		return map;
	}

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
	public static String myEmailSMTPHost = "smtp.exmail.qq.com";


	public static void send(SalarySheet salary) throws Exception {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties();                    // 参数配置
		props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
		// 3. 创建一封邮件
		MimeMessage message = createMimeMessage(session, salary.getEmailName(),salary);
		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();
		// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
		transport.connect(salary.getEmailName(), salary.getPassword());
		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		transport.sendMessage(message, message.getAllRecipients());
		// 7. 关闭连接
		transport.close();

	}
	/**
	 *
	 * @Title: createMimeMessage
	 * @Description: 创建邮件
	 * @author: WangFucheng
	 * @param session
	 * @param sendMail
	 * @param salary
	 * @return
	 * @throws Exception MimeMessage   返回类型
	 * @throws
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail,SalarySheet salary) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
		message.setFrom(new InternetAddress(sendMail, "工资条助手", "UTF-8"));
		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(salary.getEmailAddress(), salary.getName(), "UTF-8"));
		// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
		message.setSubject(salary.getTitle(), "UTF-8");
		// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
		String html = "<table border='1'>"
				+"<tr>"
				+"<th rowspan='4' align='center'>序号</th>"
				+"<th rowspan='4' align='center'>姓名</th>"
				+"<th rowspan='4' align='center'>身份证号码</th>"
				+"<th rowspan='4' align='center'>部门</th>"
				+"<th rowspan='4' align='center'>职务</th>"
				+"<th colspan='6' rowspan='2' align='center'>工资、薪金所得</th>"
				+"<th colspan='14' align='center'>扣除项目</th>"
				+"<th rowspan='4' align='center'>本月扣除合计</th>"
				+"<th rowspan='4' align='center'>累计收入</th>"
				+"<th rowspan='4' align='center'>截至扣除金额</th>"
				+"<th rowspan='4' align='center'>应纳税所得额</th>"
				+"<th rowspan='4' align='center'>适用税率</th>"
				+"<th rowspan='4' align='center'>累计应纳个税</th>"
				+"<th rowspan='4' align='center'>已缴纳个税</th>"
				+"<th colspan='3' align='center'>公司扣款</th>"
				+"<th rowspan='4' align='center'>实发金额</th>"
				+"<th rowspan='4' align='center'>邮箱地址</th>"
				+"<th rowspan='4' align='center'>领款人签字(备注)</th>"
				+"</tr>"
				+"<tr>"
				+"<th colspan='5' align='center'>专项扣除</th>"
				+"<th colspan='7' align='center'>专项附加扣除</th>"
				+"<th align='center'>依法确定的其</th>"
				+"<th rowspan='3' align='center'>基本减除额</th>"
				+"<th rowspan='3' align='center'>代扣个税</th>"
				+"<th rowspan='3' align='center'>社保个人负担</th>"
				+"<th rowspan='3' align='center'>小记</th>"
				+"</tr>"
				+"<tr>"
				+"<th rowspan='2' align='center'>基本工资</th>"
				+"<th rowspan='2' align='center'>电脑补助</th>"
				+"<th rowspan='2' align='center'>奖金/罚款</th>"
				+"<th rowspan='2' align='center'>交通补助</th>"
				+"<th rowspan='2' align='center'>小计(含免税所得)</th>"
				+"<th rowspan='2' align='center'>免税所得</th>"
				+"<th align='center'>养老保险</th>"
				+"<th align='center'>医疗保险</th>"
				+"<th align='center'>失业保险</th>"
				+"<th align='center'>住房公积金</th>"
				+"<th rowspan='2' align='center'>小计</th>"
				+"<th rowspan='2' align='center'>子女教育</th>"
				+"<th rowspan='2' align='center'>继续教育</th>"
				+"<th rowspan='2' align='center'>大病医疗</th>"
				+"<th rowspan='2' align='center'>住房贷款利息</th>"
				+"<th rowspan='2' align='center'>住房租金</th>"
				+"<th rowspan='2' align='center'>赡养老人</th>"
				+"<th rowspan='2' align='center'>小计</th>"
				+"<th rowspan='2' align='center'>小计</th>"
				+"</tr>"
				+"<tr>"
				+"<th align='center' style='color: red;'>8%</th>"
				+"<th align='center' style='color: red;'>2%</th>"
				+"<th align='center' style='color: red;'>0.02%</th>"
				+"<th align='center' style='color: red;'>12%</th>"
				+"</tr>"
				+"<tr>"
				+"<td>"+salary.getNumber()+"</td>"
				+"<td>"+salary.getName()+"</td>"
				+"<td>"+salary.getIdCardNumber()+"</td>"
				+"<td>"+salary.getDepartment()+"</td>"
				+"<td>"+salary.getJob()+"</td>"
				+"<td>"+salary.getBasePay()+"</td>"
				+"<td>"+salary.getComputerSubsidy()+"</td>"
				+"<td>"+salary.getBonusOrFine()+"</td>"
				+"<td>"+salary.getTrafficPay()+"</td>"
				+"<td>"+salary.getCountFirst()+"</td>"
				+"<td>"+salary.getExemptIncome()+"</td>"
				+"<td>"+salary.getEndowmentInsurance()+"</td>"
				+"<td>"+salary.getMedicalInsurance()+"</td>"
				+"<td>"+salary.getUnemploymentInsurance()+"</td>"
				+"<td>"+salary.getHousingProvidentFund()+"</td>"
				+"<td>"+salary.getCountSecond()+"</td>"
				+"<td>"+salary.getChildrenEducation()+"</td>"
				+"<td>"+salary.getContinuingEducation()+"</td>"
				+"<td>"+salary.getMedicalTreatmentForSeriousIllness()+"</td>"
				+"<td>"+salary.getInterestOnHousingLoans()+"</td>"
				+"<td>"+salary.getHousingRent()+"</td>"
				+"<td>"+salary.getSupportForTheElderly()+"</td>"
				+"<td>"+salary.getCountThird()+"</td>"
				+"<td>"+salary.getCountFourth()+"</td>"
				+"<td>"+salary.getBasicDeductions()+"</td>"
				+"<td>"+salary.getTotalDeductionForThisMonth()+"</td>"
				+"<td>"+salary.getAccumulatedIncome()+"</td>"
				+"<td>"+salary.getUpToDeductionAmount()+"</td>"
				+"<td>"+salary.getTaxableIncome()+"</td>"
				+"<td>"+salary.getApplicableTaxRate()+"</td>"
				+"<td>"+salary.getAccumulatedTaxPayable()+"</td>"
				+"<td>"+salary.getaTaxHasBeenPaid()+"</td>"
				+"<td>"+salary.getWithholdingTax()+"</td>"
				+"<td>"+salary.getSocialSecurityPersonalBurden()+"</td>"
				+"<td>"+salary.getCountFivth()+"</td>"
				+"<td>"+salary.getActualAmount()+"</td>"
				+"<td>"+salary.getEmailAddress()+"</td>"
				+"<td>"+salary.getReceiverSignature()+"</td>"
				+"</tr>"
				+ "</table>";
		message.setContent(html, "text/html;charset=UTF-8");
		// 6. 设置发件时间
		message.setSentDate(new Date());
		// 7. 保存设置
		message.saveChanges();

		return message;
	}


	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			download(response);
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/tb/salary/list?repage";
	}
	/**
	 * 添加Flash消息
	 */
	@Override
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}

	/**
	 * 文件导出
	 */
	public void download(HttpServletResponse response) throws  IOException{
		//获取服务器文件
		File file = new File("/home/工资条模板.xlsx");
		//File file = new File("F:\\工作文档\\thd oa平台资料\\工资条模板.xlsx");

		//头文件中文编码
		String fileName = URLEncoder.encode(file.getName(),"UTF-8");
		InputStream ins = new FileInputStream(file);
		/* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
		//response.setContentType("multipart/form-data");
		//设置编码格式
		response.setContentType("application/octet-stream; charset=utf-8");
		/* 设置文件头：最后一个参数是设置下载文件名 */
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		try{
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while((len = ins.read(b)) > 0){
				os.write(b,0,len);
			}
			os.flush();
			os.close();
			ins.close();
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
}
