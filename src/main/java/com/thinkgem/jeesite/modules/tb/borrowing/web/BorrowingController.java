package com.thinkgem.jeesite.modules.tb.borrowing.web;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.insertRollBack.HistoryPROC;
import com.thinkgem.jeesite.common.mapper.insertRollBack.RollBackService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.borrowing.entity.Borrowing;
import com.thinkgem.jeesite.modules.tb.borrowing.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="${adminPath}/tb/borrowing")
public class BorrowingController extends BaseController{
	@Autowired
	private BorrowingService borrowingService;
	@Autowired
	private RollBackService rollBackService;
	@ModelAttribute
	public Borrowing get(@RequestParam(required = false) String id){
		Borrowing borrowing = null;
		if(StringUtils.isNotBlank(id)){
			borrowing = borrowingService.get(id);
		}
		if(borrowing ==null){
			borrowing = new Borrowing();
		}
		return borrowing;
	}
	@RequestMapping("list")
	public String list(Borrowing borrowing,HttpServletRequest request, HttpServletResponse response, Model model){
		borrowing.setCreateBy(UserUtils.getUser());
		Page<Borrowing> page = borrowingService.findPage(new Page<Borrowing>(request,response), borrowing);
		model.addAttribute("page", page);
		return "modules/tb/borrowing/borrowingList";
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param borrowing
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="borrowingList2")
	public String borrowingList2(Borrowing borrowing,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		
		Page<Borrowing> page =borrowingService.findPage2(new Page<Borrowing>(request,response), borrowing);
		model.addAttribute("page",page);
		return "modules/tb/borrowing/borrowingList2";
	}
	
	@RequestMapping("employeesList")
	public String employeeList(Borrowing borrowing,HttpServletRequest request, HttpServletResponse response, Model model){
		
		Page<Borrowing> page = borrowingService.findEmployeesPage(new Page<Borrowing>(request,response), borrowing);
		model.addAttribute("page", page);
		model.addAttribute("borrowing", borrowing);
		return "modules/tb/borrowing/borrowingEmployeesList";
	}
	@RequestMapping("toAdd")
	public String toAdd(Borrowing borrowing,Model model) {
		borrowing.setEname(UserUtils.getUser().getCompany().getName());
		borrowing.setName(UserUtils.getUser());
		borrowing.setOffice(UserUtils.getUser().getOffice());
		borrowing.setTime(new Date());
		model.addAttribute("borrowing", borrowing);
		return "modules/tb/borrowing/addBorrowing";
	}
	
	@RequestMapping("save")
	public String save(Borrowing borrowing){
		//borrowing.setEname(UserUtils.getUser().getCompany().getName());
		borrowing.setName(UserUtils.getUser());
		borrowing.setOffice(UserUtils.getUser().getOffice());
		borrowing.setTime(new Date());
		borrowing.setTitle("借款申请-"+borrowing.getMoney()+"元-"+borrowing.getName().getOffice().getName()+"-"+borrowing.getName().getName());
		borrowingService.save(borrowing);
		return "redirect:" + adminPath + "/tb/borrowing/list";
	}
	@RequestMapping("upload")
	public String update(@RequestParam("file")MultipartFile file,String id){
		String fileName = file.getOriginalFilename();
		if(StringUtils.isNoneBlank(fileName)){
			
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			String address = IdGen.uuid()+suffix;
			FileOutputStream fos = null;
			try {
				byte[] fileData = file.getBytes();
				fos = new FileOutputStream(Global.getConfig("borrowingUploadPath")+address);
				fos.write(fileData);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Borrowing borrowing = new Borrowing();
			borrowing.setId(id);
			borrowing.setAddress(address);
			borrowingService.upload(borrowing);
		}
		return "redirect:/a/tb/borrowing/employeesList";
	}
	@RequestMapping("download")
	public String download(Borrowing borrowing,HttpServletRequest request,HttpServletResponse response,String sign) throws IOException{
		String downloadPrefix = Global.getConfig("borrowingUploadPath");
		String url = downloadPrefix+borrowing.getAddress();
		String[] str = borrowing.getAddress().split("\\.");
		borrowing.setAddress(borrowing.getTitle()+"-银行流水."+str[1]);//临时存放文件名称
		
		File file = new File(url);
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(borrowing.getAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
		response.addHeader("Content-Length", "" + file.length());
		try {
			// 以流的形式下载文件
			InputStream is = new BufferedInputStream(new FileInputStream(url));
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();

			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			os.write(buffer);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("form")
	public String form(Borrowing borrowing,Model model){
		String view = "borrowingView";
		if (StringUtils.isNotBlank(borrowing.getId())) {
			String taskDefKey = borrowing.getAct().getTaskDefKey();
			if(borrowing.getAct().isFinishTask()){
				view = "borrowingView";
			}else if("userTask1".equals(taskDefKey)
					||"userTask2".equals(taskDefKey)
					||"userTask3".equals(taskDefKey)
					||"userTask4".equals(taskDefKey)){
				view = "borrowingAudit";
			}else if("modifyTask".equals(taskDefKey)){
				view="addBorrowing";
			}
		}
		model.addAttribute("borrowing", borrowing);
		return "modules/tb/borrowing/"+view;
	}
	@RequestMapping("saveAudit")
	public String saveAudit(Borrowing borrowing){
		borrowingService.auditSave(borrowing);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
	@RequestMapping(value="print")
	public String print(String id,Model model){
		Borrowing borrowing = borrowingService.get(id);
		model.addAttribute("borrowing", borrowing);
		List<HistoryPROC> historyList = rollBackService.selectHistoryPROC(borrowing.getAct().getProcInsId());
		model.addAttribute("historyList", historyList);
		return "modules/tb/borrowing/printBorrowingView";
	}
}
