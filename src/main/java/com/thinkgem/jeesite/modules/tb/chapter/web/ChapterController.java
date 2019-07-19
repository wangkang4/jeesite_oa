package com.thinkgem.jeesite.modules.tb.chapter.web;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.chapter.entity.Chapter;
import com.thinkgem.jeesite.modules.tb.chapter.entity.ChapterType;
import com.thinkgem.jeesite.modules.tb.chapter.service.ChapterService;
import com.thinkgem.jeesite.modules.tb.contract.entity.Contract;
import com.thinkgem.jeesite.modules.tb.contract.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="${adminPath}/tb/chapter")
public class ChapterController extends BaseController{
	
	@Autowired
	private ChapterService chapterService;
	@Autowired
	private ContractService contractService;
	
	@ModelAttribute
	public Chapter get(@RequestParam(required = false) String id){
		Chapter chapter = null;
		if(StringUtils.isNotBlank(id)){
			chapter = chapterService.get(id);
		}
		if(chapter ==null){
			chapter = new Chapter();
		}
		return chapter;
	}
	
	@RequestMapping(value="list")
	public String list(Chapter chapter,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		chapter.setUser(UserUtils.getUser());
		Page<Chapter> page = chapterService.findPage(new Page<Chapter>(request,response), chapter);
		model.addAttribute("page",page);
		return "modules/tb/chapter/chapterList";
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param invoiceApply
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list2")
	public String list2(Chapter chapter,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Chapter> page =chapterService.findPage3(new Page<Chapter>(request,response), chapter);
		model.addAttribute("page",page);
		return "modules/tb/chapter/chapterList2";
	}
	
	@RequestMapping("employeesList")
	public String employeesList(Chapter chapter,Act act,
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		Page<Chapter> page = chapterService.findEmployeesPage(new Page<Chapter>(request,response), chapter);
		model.addAttribute("page", page);
		model.addAttribute("chapter", chapter);
		return "modules/tb/chapter/chapterEmployeesList";
	}
	@RequestMapping(value="toAdd")
	public String toAdd(Model model,Chapter chapter){
		chapter.setUser(UserUtils.getUser());
		chapter.setOffice(UserUtils.getUser().getOffice());
		chapter.setApplyDate(new Date());
		model.addAttribute("chapter", chapter);
		List<ChapterType> chapterList = chapterService.selectChapterType();
		List<ChapterType> fileList = chapterService.selectFileType();
		model.addAttribute("chapterList", chapterList);
		model.addAttribute("fileList", fileList);
		return "modules/tb/chapter/addChapter";
	}
	
	@RequestMapping(value="add")
	public String add(Chapter chapter,Model model, RedirectAttributes redirectAttributes) throws ParseException{
		chapter.setProneText(null);
		chapter.setPrtwoText(null);
		chapter.setPrthreeText(null);
		chapter.setPrfourText(null);
		chapter.setPrfiveText(null);
		chapter.setUser(UserUtils.getUser());
		chapter.setOffice(UserUtils.getUser().getOffice());
		chapter.setApplyDate(new Date());
		if(chapter.getReturnDate()!=null){
			chapter.setReturnDate(chapter.getReturnDate());
		}
		chapter.setReason("用印申请-"+chapter.getFileForChapter()+"-"+chapter.getOffice().getName()+"-"+chapter.getUser().getName());
		chapterService.save(chapter);
		return "redirect:" + adminPath + "/tb/chapter/list";
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
				fos = new FileOutputStream(Global.getConfig("chapterUploadPath")+address);
				fos.write(fileData);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return address;
	}
	@RequestMapping("download")
	public String download(Chapter chapter,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String downloadPrefix = Global.getConfig("chapterUploadPath");
		String url = downloadPrefix+chapter.getAddress();
		String[] str = chapter.getAddress().split("\\.");
		chapter.setAddress(chapter.getFileForChapter()+"."+str[1]);
		File file = new File(url);
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(chapter.getAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
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
	public String form(Chapter chapter,Model model){
		String view = "chapterView";
		if(StringUtils.isNotBlank(chapter.getId())){
			// 获取环节ID
			String taskDefKey = chapter.getAct().getTaskDefKey();
			//查看用印
			if(chapter.getAct().isFinishTask()){
				view = "chapterView";
			}else if("userTask1".equals(taskDefKey)
					||"userTask2".equals(taskDefKey)
					||"userTask3".equals(taskDefKey)){
				view = "chapterAudit";
			}else if("modifyTask".equals(taskDefKey)){
				view="addChapter";
			}
		}
		model.addAttribute("chapter", chapter);
		List<ChapterType> chapterList = chapterService.selectChapterType();
		List<ChapterType> fileList = chapterService.selectFileType();
		model.addAttribute("chapterList", chapterList);
		model.addAttribute("fileList", fileList);
		return "modules/tb/chapter/" + view;
	}
	@RequestMapping(value="saveAudit")
	public String saveAudit(Chapter chapter,Model model){
		chapterService.auditSave(chapter);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
	@ResponseBody
	@RequestMapping(value="getContract")
	public Map<String, List<Contract>> getContract(){
		Contract contract = new Contract();
		contract.setContractApply(UserUtils.getUser().getId());
		Map<String, List<Contract>> map = new HashMap<String, List<Contract>>();
		List<Contract> list = contractService.findContract(contract);
		map.put("list", list);
		return map;
	}
	
	/**
	 * 验证印章是否被外借使用
	 * @param chapteruse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="chapteruse")
	public JSONObject getChapterUser(String chapteruse){
		JSONObject json = new JSONObject();
		String[] chapterTypes = chapteruse.split(",");
		int flag=0;
		String flagName = "";
		for(String ct: chapterTypes){
			/*检查是否有外借章的流程没有走完*/
			flag = chapterService.findChapterUse(ct);
			/*如果有*/
			if(flag!=0){
				/*获取章的名字*/
				flagName = chapterService.findChapterUseName(ct);
				json.put("flag", flag);
				json.put("flagName", flagName);
				/*返回到前台*/
				return json;
			}
		}
		/*如果没有（即可以申请）*/
		json.put("flag", 0);
		return json;
	}
	

}
