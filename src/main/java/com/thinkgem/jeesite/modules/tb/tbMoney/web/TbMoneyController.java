package com.thinkgem.jeesite.modules.tb.tbMoney.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.insertRollBack.HistoryPROC;
import com.thinkgem.jeesite.common.mapper.insertRollBack.RollBackService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Business;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.ReceptionStaff;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Special;
import com.thinkgem.jeesite.modules.tb.tbMoney.service.BusinessService;
import com.thinkgem.jeesite.modules.tb.tbMoney.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(value="${adminPath}/tb/money")
public class TbMoneyController extends BaseController{
	
	@Autowired
	private SpecialService specialService;
	@Autowired
	private BusinessService businessService;
	@Autowired
	private RollBackService rollBackService;
	
	@ModelAttribute
	public Special get(@RequestParam(required = false) String id){
		Special special = null;
		if(StringUtils.isNotBlank(id)){
			special = specialService.get(id);
		}
		if(special ==null){
			special = new Special();
		}
		return special;
	}
	@ModelAttribute
	public Business getBusiness(@RequestParam(required = false) String id){
		Business business = null;
		if(StringUtils.isNotBlank(id)){
			business = businessService.get(id);
		}
		if(business ==null){
			business = new Business();
		}
		return business;
	}
	
	
	@RequestMapping(value="specialList")
	public String specialList(Special special,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		special.setUser(UserUtils.getUser());
		Page<Special> page = specialService.findPage(new Page<Special>(request,response), special);
		model.addAttribute("page",page);
		return "modules/tb/tbMoney/specialList";
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
	@RequestMapping(value="specialList2")
	public String specialList2(Special special,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		
		Page<Special> page = specialService.findPage2(new Page<Special>(request,response), special);
		model.addAttribute("page",page);
		return "modules/tb/tbMoney/specialList2";
	}
	
	@RequestMapping(value="specialEmployeesList")
	public String specialEmployeesList(Special special,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Special> page = specialService.findEmployeesPage(new Page<Special>(request,response), special);
		model.addAttribute("page",page);
		return "modules/tb/tbMoney/specialEmployeesList";
	}
	//俞跃舒可以查看所有领导的预审批
	@RequestMapping(value="specialLeaderList")
	public String specialLeaderList(Special special,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		special.setLeader(1);
		Page<Special> page = specialService.findPage(new Page<Special>(request,response), special);
		
		model.addAttribute("page",page);
		return "modules/tb/tbMoney/specialList";
	}
	@RequestMapping(value="printSpecial")
	public String printSpecial(Special special,Model model){
		model.addAttribute("special", special);
		List<HistoryPROC> historyList = rollBackService.selectHistoryPROC(special.getAct().getProcInsId());
		model.addAttribute("historyList", historyList);
		return "modules/tb/tbMoney/printSpecialView";
	}
	@RequestMapping(value="printBusiness")
	public String printBusiness(Business business,Model model){
		model.addAttribute("business", business);
		List<ReceptionStaff> list = 
				businessService.selectReceptionStaff(business.getReceptionStaff());
		model.addAttribute("list", list);
		List<HistoryPROC> historyList = rollBackService.selectHistoryPROC(business.getAct().getProcInsId());
		model.addAttribute("historyList", historyList);
		return "modules/tb/tbMoney/printBusinessView";
	}
	@RequestMapping(value="businessList")
	public String businessList(Business business,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		business.setUser(UserUtils.getUser());
		Page<Business> page = businessService.findPage(new Page<Business>(request,response), business);
		model.addAttribute("page",page);
		return "modules/tb/tbMoney/businessList";
	}

	/**
	 * 行政人员查看所属区域申请列表
	 * @param business
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="businessList2")
	public String businessList2(Business business,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		
		Page<Business> page = businessService.findPage2(new Page<Business>(request,response), business);
		model.addAttribute("page",page);
		return "modules/tb/tbMoney/businessList2";
	}
	
	@RequestMapping(value="businessEmployeesList")
	public String businessEmployeesList(Business business,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Business> page = businessService.findEmployeesPage(new Page<Business>(request,response), business);
		model.addAttribute("page",page);
		return "modules/tb/tbMoney/businessEmployeesList";
	}
	//俞跃舒可以查看所有领导的预审批
	@RequestMapping(value="businessLeaderList")
	public String businessLeaderList(Business business,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		business.setLeader(1);
		Page<Business> page = businessService.findPage(new Page<Business>(request,response), business);
		
		model.addAttribute("page",page);
		return "modules/tb/tbMoney/businessList";
	}
	@RequestMapping(value="toAddSpecial")
	public String toAddSpecial(Special special,Model model){
		if(StringUtils.isNotBlank(special.getId())){
			
		}else{
			special.setUser(UserUtils.getUser());
			special.setOffice(UserUtils.getUser().getOffice());
			
		}
		model.addAttribute("special", special);
		return "modules/tb/tbMoney/toAddSpecial";
	}
	@RequestMapping("toAddBusiness")
	public String toAddBusiness(Business business,Model model){
		if(StringUtils.isNotBlank(business.getId())){
			
		}else{
			business.setUser(UserUtils.getUser());
			business.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("business", business);
		List<ReceptionStaff> list = 
				businessService.selectReceptionStaff(business.getReceptionStaff());
		model.addAttribute("list", list);
		String id="";
		String name = "";
		for(ReceptionStaff rs : list){
			if("1".equals(rs.getType())){
				id = id+rs.getPosition()+",";
				name = name + rs.getName()+",";
			}
		}
		if(id.length()>=1){
			id=id.substring(0, id.length()-1);
			name=name.substring(0, name.length()-1);
		}
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "modules/tb/tbMoney/toAddBusiness";
	}
	@RequestMapping(value="addSpecial")
	public String addSpecial(Special special,Model model, RedirectAttributes redirectAttributes) throws ParseException{
		special.setProneText(null);
		special.setPrtwoText(null);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(special.getTbDate());
		special.setTbDate(sdf.parse(str));
		special.setReason("专项费用申请-"+UserUtils.getUser().getOffice().getName()+"-"+UserUtils.getUser().getName());
	    specialService.save(special);
		addMessage(redirectAttributes, "提交预批成功");
		return "redirect:" + adminPath + "/tb/money/specialList";
	}
	@RequestMapping("addBusiness")
	public String addBusiness(Business business,Model model,RedirectAttributes redirectAttributes,HttpServletRequest request) throws ParseException{
		business.setProneText(null);
		business.setPrtwoText(null);
		business.setReason("业务招待申请-"+UserUtils.getUser().getOffice().getName()+"-"+UserUtils.getUser().getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(business.getTbDate());
		business.setTbDate(sdf.parse(str));
		String name[] = request.getParameterValues("person");
		String position[] = request.getParameterValues("position");
		if(StringUtils.isNotBlank(business.getReceptionStaff())){
			businessService.deleteReceptionStaffById(business.getReceptionStaff());
		}
		business.setReceptionStaff(IdGen.uuid());
		for(int i=0;i<name.length;i++){//被招待人员
			ReceptionStaff rs = new ReceptionStaff();
			rs.setId(business.getReceptionStaff());
			rs.setName(name[i]);
			rs.setPosition(position[i]);
			rs.setType("0");
			businessService.insertReceptionStaff(rs);
		}
		String person[] = business.getPersons().split(",");
		for(int i=0;i<person.length;i++){
			ReceptionStaff rs = new ReceptionStaff();
			rs.setId(business.getReceptionStaff());
			rs.setName(UserUtils.get(person[i]).getName());
			rs.setPosition(person[i]);
			rs.setType("1");
			businessService.insertReceptionStaff(rs);
		}
		
		businessService.save(business);
		addMessage(redirectAttributes,"提交预审批成功");
		return "redirect:" + adminPath + "/tb/money/businessList";
	}
	
	@RequestMapping(value="form")
	public String form(Special special,Business business,Model model){
		String view = "";
		if(special.getCreateBy()!=null){//专项费用
			view = "specialView";
			if(StringUtils.isNotBlank(special.getId())){
				// 获取环节ID
				String taskDefKey = special.getAct().getTaskDefKey();
				if(special.getAct().isFinishTask()){
					view = "specialView";
				}else if("userTask1".equals(taskDefKey)
						||"userTask2".equals(taskDefKey)
						||"userTask3".equals(taskDefKey)
						||"userTask4".equals(taskDefKey)){
					view = "specialAudit";
				}else if("modifyTask".equals(taskDefKey)){
					view="toAddSpecial";
				}
			}
			model.addAttribute("special", special);
		}else{//业务招待
			view = "businessView";
			if(StringUtils.isNotBlank(business.getId())){
				// 获取环节ID
				String taskDefKey = business.getAct().getTaskDefKey();
				if(business.getAct().isFinishTask()){
					view = "businessView";
				}else if("userTask1".equals(taskDefKey)
						||"userTask2".equals(taskDefKey)
						||"userTask3".equals(taskDefKey)
						||"userTask4".equals(taskDefKey)){
					view = "businessAudit";
				}else if("tbMoneyUpdate".equals(taskDefKey)){
					view="toAddBusiness";
				}
			}
			model.addAttribute("business", business);
			List<ReceptionStaff> list = 
					businessService.selectReceptionStaff(business.getReceptionStaff());
			model.addAttribute("list", list);
		}
		return "modules/tb/tbMoney/" + view;
	}
	
	@RequestMapping(value="saveAuditSpecial")
	public String saveAuditSpecial(Special special,Model model){
		specialService.auditSave(special);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
	@RequestMapping(value="saveAuditBusiness")
	public String saveAuditBusiness(Business business,Model model){
		businessService.auditSave(business);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
	@RequestMapping("uploadBusiness")
	public String updateBusiness(@RequestParam("file")MultipartFile file,String id){
		String fileName = file.getOriginalFilename();
		if(StringUtils.isNoneBlank(fileName)){
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			String address = IdGen.uuid()+suffix;
			FileOutputStream fos = null;
			try {
				byte[] fileData = file.getBytes();
				fos = new FileOutputStream(Global.getConfig("businessUploadPath")+address);
				fos.write(fileData);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Business business = new Business();
			business.setId(id);
			business.setAddress(address);
			businessService.uploadBusiness(business);
		}
		return "redirect:/a/tb/money/businessEmployeesList";
	}
	@RequestMapping("downloadBusiness")
	public String downloadBusiness(Business business,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String downloadPrefix = Global.getConfig("businessUploadPath");
		String url = downloadPrefix+business.getAddress();
		String[] str = business.getAddress().split("\\.");
		business.setAddress(business.getReason()+"-银行流水."+str[1]);//临时存放文件名称
		
		File file = new File(url);
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(business.getAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
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
	@RequestMapping("uploadSpecial")
	public String updateSpecial(@RequestParam("file")MultipartFile file,String id){
		String fileName = file.getOriginalFilename();
		if(StringUtils.isNoneBlank(fileName)){
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			String address = IdGen.uuid()+suffix;
			FileOutputStream fos = null;
			try {
				byte[] fileData = file.getBytes();
				fos = new FileOutputStream(Global.getConfig("specialUploadPath")+address);
				fos.write(fileData);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Special special = new Special();
			special.setId(id);
			special.setAddress(address);
			specialService.uploadSpecial(special);
		}
		return "redirect:/a/tb/money/specialEmployeesList";
	}
	@RequestMapping("downloadSpecial")
	public String downloadSpecial(Special special,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String downloadPrefix = Global.getConfig("specialUploadPath");
		String url = downloadPrefix+special.getAddress();
		String[] str = special.getAddress().split("\\.");
		special.setAddress(special.getReason()+"-银行流水."+str[1]);//临时存放文件名称
		
		File file = new File(url);
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(special.getAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
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
}
