package com.thinkgem.jeesite.modules.tb.induction.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.induction.entity.Induction;
import com.thinkgem.jeesite.modules.tb.induction.service.InductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Controller
@RequestMapping(value="${adminPath}/tb/induction")
public class InductionController extends BaseController{
	@Autowired
	private InductionService inductionService;
	@ModelAttribute
	public Induction get(@RequestParam(required = false) String id){
		Induction induction = null;
		if(StringUtils.isNotBlank(id)){
			induction = inductionService.get(id);
		}
		if(induction ==null){
			induction = new Induction();
		}
		return induction;
	}
	@RequestMapping("list")
	public String list(Induction induction,HttpServletRequest request, HttpServletResponse response, Model model){
		induction.setCreateBy(UserUtils.getUser());
		Page<Induction> page = inductionService.findPage(new Page<Induction>(request,response), induction);
		model.addAttribute("page",page);
		return "modules/tb/induction/inductionList";
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param induction
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

	@RequestMapping(value="list2")
	public String list2(Induction induction,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Induction> page =inductionService.findPage3(new Page<Induction>(request,response), induction);
		model.addAttribute("page",page);
		return "modules/tb/induction/inductionList2";
	}

	/**
	 * 行政人员查看所属区域申请列表
	 * @param induction
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list3")
	public String list3(Induction induction,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Induction> page =inductionService.findPage4(new Page<Induction>(request,response), induction);
		model.addAttribute("page",page);
		return "modules/tb/induction/inductionList3";
	}
	
	@RequestMapping("toAdd")
	public String toAdd(Induction induction,Model model){
		if(induction.getCreateDate()==null){
			induction.setCreateDate(new Date());
		}
		model.addAttribute("induction", induction);
		return "modules/tb/induction/addInduction";
	}
	@RequestMapping("save")
	public String save(Induction induction){
		inductionService.save(induction);
		return "redirect:" + adminPath + "/tb/induction/list";
	}
	@RequestMapping(value="saveAudit")
	public String saveAudit(Induction induction,Model model){
		inductionService.auditSave(induction);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
	@ResponseBody
	@RequestMapping("upload")
	public String upload(@RequestParam("file")MultipartFile file){
		String fileName = file.getOriginalFilename();
		String address = "";
		if(StringUtils.isNoneBlank(fileName)){
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			address = IdGen.uuid()+suffix;
			FileOutputStream fos = null;
			try {
				byte[] fileData = file.getBytes();
				fos = new FileOutputStream(Global.getConfig("inductionUploadPath")+address);
				fos.write(fileData);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return address;
	}
	@RequestMapping("download")
	public String download(Induction induction,String sign,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String downloadPrefix = Global.getConfig("inductionUploadPath");
		String url = "";
		String name="";
		if("1".equals(sign)){
			url = downloadPrefix+induction.getFileOneAddress();
			String[] str = induction.getFileOneAddress().split("\\.");
			name = induction.getEmployedName()+"的简历和面试意见."+str[1];
		}else if("2".equals(sign)){
			url = downloadPrefix+induction.getFileTwoAddress();
			String[] str = induction.getFileTwoAddress().split("\\.");
			name = induction.getEmployedName()+"的证件."+str[1];
		}
		File file = new File(url);
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(name.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
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
	@RequestMapping(value="form")
	public String form(Induction induction,Model model){
		String view = "inductionAudit";
		if(StringUtils.isNotBlank(induction.getId())){
			// 获取环节ID
			String taskDefKey = induction.getAct().getTaskDefKey();
			//查看用印
			if(induction.getAct().isFinishTask()){
				view = "inductionView";
			}else if("modifyTask".equals(taskDefKey)){
				view="addInduction";
			}
		}
		model.addAttribute("induction", induction);
		return "modules/tb/induction/" + view;
	}
}
