package com.thinkgem.jeesite.modules.tb.contract.web;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.tool.PinyinTool;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="${adminPath}/tb/contract")
public class ContractController extends BaseController{
	
	@Autowired
	private ContractService contractService;
	@ModelAttribute
	public Contract get(@RequestParam(required = false) String id){
		Contract contract = null;
		if(StringUtils.isNotBlank(id)){
			contract = contractService.get(id);
			if(StringUtils.isNotBlank(contract.getManager())){
				contract.setManagerName(UserUtils.get(contract.getManager()).getName());
			}
			if(StringUtils.isNotBlank(contract.getManagerTwo())){
				contract.setManagerTwoName(UserUtils.get(contract.getManagerTwo()).getName());
			}
		}
		if(contract ==null){
			contract = new Contract();
		}
		return contract;
	}
	@RequestMapping("list")
	public String list(Contract contract,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		contract.setContractApply(UserUtils.getUser().getId());
		Page<Contract> page = contractService.findPage(new Page<Contract>(request,response), contract);
		model.addAttribute("page", page);
		return "modules/tb/contract/contractList";
	}

	/**
	 * 行政人员查看所属区域申请列表
	 * @param contract
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="contractList2")
	public String contractList2(Contract contract,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		
		Page<Contract> page =contractService.findPage2(new Page<Contract>(request,response), contract);
		model.addAttribute("page",page);
		return "modules/tb/contract/contractList2";
	}
	
	@RequestMapping("employeesList")
	public String employeesList(Contract contract,Act act,
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		Page<Contract> page = contractService.findEmployeesPage(new Page<Contract>(request,response), contract);
		model.addAttribute("page", page);
		model.addAttribute("contract", contract);
		return "modules/tb/contract/contractEmployeesList";
	}
	@ResponseBody
	@RequestMapping("applist")
	public String applist(@RequestParam("callback") String callback){
		Contract contract = new Contract();
		contract.setContractApply("1");
		List<Contract> list = contractService.findList(contract);
		Map<String, List<Contract>> map = new HashMap<String,List<Contract>>();
		map.put("list", list);
		Gson gson = new Gson();
		return callback+"("+gson.toJson(map)+")";
	}
	@RequestMapping("toAdd")
	public String toAdd(Contract contract,Model model) {
		contract.setCreateBy(UserUtils.getUser());
		contract.setCreateDate(new Date());
		contract.setEname(UserUtils.getUser().getCompany().getName());
		model.addAttribute("contract", contract);
		return "modules/tb/contract/addContract";
	}
	@ResponseBody
	@RequestMapping("getPY")
	public Map<String,String> getPY(String companyName){
		Map<String,String> map = new HashMap<String,String>();
		int i = companyName.indexOf("有限公司");
		if(i!=-1){
			companyName=companyName.replaceAll("有限公司", "");
		}
		String data = PinyinTool.getPinYinHeadChar(companyName).toUpperCase();
		map.put("PY", data);
		return map;
	}
	@RequestMapping("save")
	public String save(Contract contract,String type,String PY){
		contract.setAreaName(contractService.selectAreaById(contract.getArea()));
		//contract.setEname(UserUtils.getUser().getCompany().getName());
		contractService.save(contract,type,PY);
		return "redirect:" + adminPath + "/tb/contract/list";
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
				fos = new FileOutputStream(Global.getConfig("contractUploadPath")+address);
				fos.write(fileData);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return address;
	}
	@RequestMapping("download")
	public String download(Contract contract,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String downloadPrefix = Global.getConfig("contractUploadPath");
		String url = downloadPrefix+contract.getAddress();
		String[] str = contract.getAddress().split("\\.");
		contract.setAddress(contract.getContractName()+"."+str[1]);
		File file = new File(url);
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(contract.getAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
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
	@ResponseBody
	@RequestMapping("associated")
	public Map<String, List<Contract>> associated(){
		Map<String, List<Contract>> map = new HashMap<String, List<Contract>>();
		List<Contract> list = contractService.selectContractToAssociated(UserUtils.getUser().getId());
		map.put("list", list);
		return map;
	}
	@RequestMapping("form")
	public String form(Contract contract,Model model){
		String view = "contractView";
		if (StringUtils.isNotBlank(contract.getId())) {
			String taskDefKey = contract.getAct().getTaskDefKey();
			if(contract.getAct().isFinishTask()){
				view = "contractView";
			}else if("userTask1".equals(taskDefKey)
					||"userTask2".equals(taskDefKey)
					||"userTask3".equals(taskDefKey)
					||"userTask4".equals(taskDefKey)
					||"userTask5".equals(taskDefKey)){
				view = "contractAudit";
			}else if("modifyTask".equals(taskDefKey)){
				view="addContract";
			}
		}
		model.addAttribute("contract", contract);
		return "modules/tb/contract/"+view;
	}
	@RequestMapping("saveAudit")
	public String saveAudit(Contract contract){
		contractService.auditSave(contract);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
	@ResponseBody
	@RequestMapping("abandon")
	public Map<String, String> abandon(Contract contract){
		contractService.abandon(contract);
		Map<String,String> map = new HashMap<String, String>();
		return map;
	}
	@ResponseBody
	@RequestMapping(value="withdraw")
	public Map<String, String> withdraw(Contract contract){
		Map<String, String> map = new HashMap<String, String>();
		if(contract.getProneText()!=null||contract.getPrtwoText()!=null
				||contract.getPrthreeText()!=null
				||contract.getPrfourText()!=null||contract.getPrfiveText()!=null
				||contract.getPrsixText()!=null||contract.getPrsevenText()!=null){
			map.put("data", "error");
		}else{
			contractService.withdraw(contract);
			map.put("data", "ok");
		}
		return map;
	}
	
}
