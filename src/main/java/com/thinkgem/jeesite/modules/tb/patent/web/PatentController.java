package com.thinkgem.jeesite.modules.tb.patent.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.patent.entity.Patent;
import com.thinkgem.jeesite.modules.tb.patent.entity.Person;
import com.thinkgem.jeesite.modules.tb.patent.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="${adminPath}/tb/patent")
public class PatentController extends BaseController {
	
	@Autowired
	private PatentService patentService;
	
	@ModelAttribute
	public Patent get(String id){
		Patent patent=null;
		if(StringUtils.isNotBlank(id)){
			patent=patentService.get(id);
		}
		if(patent==null){
			patent=new Patent();
		}
		return patent;
	}
	
	/**
	 * 填写表单页面
	 * @return
	 */
	@RequestMapping(value="add")
	public String add(Model model,Patent patent){
		patent.setUser(UserUtils.getUser());
		patent.setOffice(UserUtils.getUser().getOffice());
		patent.setApplyDate(new Date());
		model.addAttribute("patent", patent);
		/*附表信息*/
		List<Person> list=patentService.findPerson(patent.getPersonId());
		model.addAttribute("list", list);
		return "modules/tb/patent/add";
	}
	
	/**
	 * 提交表单
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="toAdd")
	public String toAdd(Patent patent,HttpServletRequest request) throws ParseException{
		patent.setUser(UserUtils.getUser());
		patent.setOffice(UserUtils.getUser().getOffice());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		patent.setApplyDate(sdf.parse(sdf.format(new Date())));
		patent.setProneText(null);
		patent.setPrtwoText(null);
		patent.setPrthreeText(null);
		patent.setPrfourText(null);
		if(StringUtils.isNotBlank(patent.getPersonId())){
			patentService.deletePerson(patent.getPersonId());
		}
		System.out.println(patent.getApplyTel());
		Boolean a = patent.getApplyTel()!=null;
		Boolean b = patent.getApplyTel().equals("");
		System.out.println(a);
		System.out.println(b);
		if(patent.getApplyTel()!=null&&!patent.getApplyTel().equals("")){
			/*设置负表关联ID*/
			patent.setPersonId(IdGen.uuid());
			/*获取分配人员信息*/
			String name[] = request.getParameterValues("person");
			String position[] = request.getParameterValues("position");
				for(int i=0;i<name.length;i++){
					Person person=new Person();
					person.setId(patent.getPersonId());
					person.setPerson(name[i]);
					person.setPosition(position[i]);
					patentService.insertPerson(person);
				}
		}
		
		patentService.save(patent);
		return "redirect:" + adminPath + "/tb/patent/list";
	}
	
	/**
	 * 专利申请列表页面
	 * @param model
	 * @param patent
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(Model model,Patent patent,HttpServletRequest request, HttpServletResponse response){
		patent.setUser(UserUtils.getUser());
		Page<Patent> page=patentService.findPage(new Page<Patent>(request,response), patent);
		model.addAttribute("page", page);
		return "modules/tb/patent/list";
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param patent
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list2")
	public String list2(Patent patent,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Patent> page =patentService.findPage3(new Page<Patent>(request,response), patent);
		model.addAttribute("page",page);
		return "modules/tb/patent/list2";
	}

	/**
	 * 财务人员查看员工申请列表
	 * @param patent
	 * @param act
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list3")
	public String list3(Patent patent,Act act,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Patent> page =patentService.findPage4(new Page<Patent>(request,response), patent);
		model.addAttribute("page",page);
		return "modules/tb/patent/list3";
	}
	
	/**
	 * 详情页面
	 * @param model
	 * @param patent
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(Model model,Patent patent){
		String view="view";
		if(StringUtils.isNotBlank(patent.getId())){
			//获取环节ID
			String taskDefKey=patent.getAct().getTaskDefKey();
			if(patent.getAct().isFinishTask()){
				view="view";
			}else if("userTask1".equals(taskDefKey)){
				view="audit";
			}else if("userTask2".equals(taskDefKey)){
				view="audit";
			}else if("userTask3".equals(taskDefKey)){
				view="audit";
			}else if("userTask4".equals(taskDefKey)){
				view="audit";
			}else if("modifyTask".equals(taskDefKey)){
				view="add";
			}
		}
		model.addAttribute("patent", patent);
		List<Person> list=patentService.findPerson(patent.getPersonId());
		model.addAttribute("list", list);
		return "modules/tb/patent/"+view;
	}
	
	@RequestMapping(value="saveAudit")
	public String saveAudit(Model model,Patent patent){
		patentService.auditSave(patent);
		//return "redirect:" + adminPath + "/act/task/todo?click";
		return "redirect:" + adminPath + "/act/task/todo";
	}
	
	/**
	 * 撤销流程
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="back")
	public String back(Patent patent){
		patentService.deletePatent(patent.getProcInsId());
		patentService.deleteTask(patent.getProcInsId());
		patentService.deletePerson(patent.getPersonId());
		return "redirect:"+adminPath+"/tb/patent/list";
	}
}
