package com.thinkgem.jeesite.modules.oapms.controller;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oapms.entity.*;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectOperationService;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/pms/project")
public class PmsProjectController extends BaseController {

    @Autowired
    private PmsProjectService pmsProjectService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private PmsProjectOperationService pmsProjectOpertionService;

    @RequestMapping(value = "list")
    public String list(PmsProject pmsProject, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PmsProject> page = pmsProjectService.findPage ( new Page<PmsProject> ( request, response ), pmsProject );
        for (PmsProject ppj : page.getList ()) {
            if (StringUtils.isNotBlank ( ppj.getSaler ().getId () )) {
                ppj.setSaler ( systemService.getUser ( ppj.getSaler ().getId () ) );
            }
            if (StringUtils.isNotBlank ( ppj.getProducter ().getId () )) {
                ppj.setProducter ( systemService.getUser ( ppj.getProducter ().getId () ) );
            }
            if (StringUtils.isNotBlank ( ppj.getDevloper ().getId () )) {
                ppj.setDevloper ( systemService.getUser ( ppj.getDevloper ().getId () ) );
            }
        }
        model.addAttribute ( "page", page );
        return "modules/oapms/projectList";
    }

    @RequestMapping(value = "form")
    public String form(PmsProject pmsProject, Model model) {
        List<Customer> clist = pmsProjectService.findCustomerList ();
        model.addAttribute ( "clist", clist );
        if (StringUtils.isNotBlank ( pmsProject.getId () )) {
            pmsProject = pmsProjectService.getOneProject ( pmsProject.getId () );
            if (StringUtils.isNotBlank ( pmsProject.getSaler ().getId () )) {
                pmsProject.setSaler ( systemService.getUser ( pmsProject.getSaler ().getId () ) );
            }
            if (StringUtils.isNotBlank ( pmsProject.getProducter ().getId () )) {
                pmsProject.setProducter ( systemService.getUser ( pmsProject.getProducter ().getId () ) );
            }
            if (StringUtils.isNotBlank ( pmsProject.getDevloper ().getId () )) {
                pmsProject.setDevloper ( systemService.getUser ( pmsProject.getDevloper ().getId () ) );
            }

            List<ProjectRelativer> plist = pmsProjectService.findPersonList ( pmsProject.getProjectId () );
            String personsName = "";
            String persons = "";
            for (ProjectRelativer ppp : plist) {
                personsName += ppp.getEmployees ().getName () + ",";
            }
            for (ProjectRelativer ppp : plist) {
                persons += ppp.getEmployees ().getId () + ",";
            }
            if (StringUtils.isNotBlank ( persons )) {
                persons = persons.substring ( 0, persons.length () - 1 );
                personsName = personsName.substring ( 0, personsName.length () - 1 );
            }
            pmsProject.setPersons ( persons );
            pmsProject.setPersonsName ( personsName );

            List<CustomerContact> cclist = pmsProjectService.findCustomerContactList ( pmsProject.getCustomer ().getCustomerId () );
            model.addAttribute ( "cclist", cclist );
            model.addAttribute ( "pmsProject", pmsProject );
            return "modules/oapms/projectForm";
        }
        return "modules/oapms/projectAdd";
    }

    @RequestMapping(value = "projectadd")
    public String projectadd(PmsProject pmsProject, Model model, RedirectAttributes redirectAttributes) {
        pmsProject.setProjectId ( IdGen.uuid () );
        pmsProject.setCreateBy ( UserUtils.getUser () );
        pmsProject.setCreateDate ( new Date () );
        pmsProject.setUpdateBy ( UserUtils.getUser () );
        pmsProject.setUpdateDate ( new Date () );
        pmsProject.setDelFlag ( "0" );
        int i = pmsProjectService.inserteOneProject ( pmsProject );
        if (1 == i) {
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pmsProject );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目 " + pmsProject.getProjectName () + "成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pmsProject );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目 " + pmsProject.getProjectName () + "失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        }

        String[] p = pmsProject.getPersons ().split ( "," );

        for (int a = 0; a < p.length; a++) {
            ProjectRelativer pr = new ProjectRelativer ();
            pr.setId ( pmsProject.getProjectId () );
            pr.setRelativeType ( "1" );
            User s = new User ();
            s.setId ( p[a] );
            pr.setEmployees ( s );
            pr.setEmployeesType ( "相关人" );
            pmsProjectService.inserteRelativers ( pr );
        }
        ProjectRelativer saler = new ProjectRelativer ();
        saler.setId ( pmsProject.getProjectId () );
        saler.setRelativeType ( "1" );
        saler.setEmployees ( pmsProject.getSaler () );
        saler.setEmployeesType ( "销售经理" );
        pmsProjectService.inserteRelativers ( saler );

        ProjectRelativer producter = new ProjectRelativer ();
        producter.setId ( pmsProject.getProjectId () );
        producter.setRelativeType ( "1" );
        producter.setEmployees ( pmsProject.getProducter () );
        producter.setEmployeesType ( "产品经理" );
        pmsProjectService.inserteRelativers ( producter );

        ProjectRelativer devloper = new ProjectRelativer ();
        devloper.setId ( pmsProject.getProjectId () );
        devloper.setRelativeType ( "1" );
        devloper.setEmployees ( pmsProject.getDevloper () );
        devloper.setEmployeesType ( "研发经理" );
        pmsProjectService.inserteRelativers ( devloper );

        addMessage ( redirectAttributes, "保存保存成功成功" );
        return "redirect:" + Global.getAdminPath () + "/pms/project/list?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(String id, RedirectAttributes redirectAttributes) {
        PmsProject pmsProject = pmsProjectService.getOneProject ( id );
        int i = pmsProjectService.deleteOneProject ( id );
        if (1 == i) {
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pmsProject );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "删除项目 " + pmsProject.getProjectName () + "成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pmsProject );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "删除项目 " + pmsProject.getProjectName () + "失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        }
        return "redirect:" + Global.getAdminPath () + "/pms/project/list?repage";
    }

    @RequestMapping(value = "projectform")
    public String projectform(PmsProject pmsProject, Model model, RedirectAttributes redirectAttributes) {
        pmsProject.setUpdateBy ( UserUtils.getUser () );
        pmsProject.setUpdateDate ( new Date () );
        int i = pmsProjectService.updateOneProject ( pmsProject );
        if (1 == i) {
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pmsProject );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "修改项目 " + pmsProject.getProjectName () + "成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pmsProject );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "修改项目 " + pmsProject.getProjectName () + "失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        }
        pmsProjectService.deleteAllRelativers ( pmsProject.getProjectId () );
        String[] p = pmsProject.getPersons ().split ( "," );
        for (int a = 0; a < p.length; a++) {
            ProjectRelativer pr = new ProjectRelativer ();
            pr.setId ( pmsProject.getProjectId () );
            pr.setRelativeType ( "1" );
            User s = new User ();
            s.setId ( p[a] );
            pr.setEmployees ( s );
            pr.setEmployeesType ( "相关人" );
            pmsProjectService.inserteRelativers ( pr );
        }
        ProjectRelativer saler = new ProjectRelativer ();
        saler.setId ( pmsProject.getProjectId () );
        saler.setRelativeType ( "1" );
        saler.setEmployees ( pmsProject.getSaler () );
        saler.setEmployeesType ( "销售经理" );
        pmsProjectService.inserteRelativers ( saler );

        ProjectRelativer producter = new ProjectRelativer ();
        producter.setId ( pmsProject.getProjectId () );
        producter.setRelativeType ( "1" );
        producter.setEmployees ( pmsProject.getProducter () );
        producter.setEmployeesType ( "产品经理" );
        pmsProjectService.inserteRelativers ( producter );

        ProjectRelativer devloper = new ProjectRelativer ();
        devloper.setId ( pmsProject.getProjectId () );
        devloper.setRelativeType ( "1" );
        devloper.setEmployees ( pmsProject.getDevloper () );
        devloper.setEmployeesType ( "研发经理" );
        pmsProjectService.inserteRelativers ( devloper );

        addMessage ( redirectAttributes, "保存保存成功成功" );
        return "redirect:" + Global.getAdminPath () + "/pms/project/list?repage";
    }

    @ResponseBody
    @RequestMapping(value = "getCustomerContactList")
    public List<CustomerContact> findCustomerContactList(String customerId) {
        List<CustomerContact> list = pmsProjectService.findCustomerContactList ( customerId );
        return list;
    }

    @RequestMapping(value = "projectDetail")
    public String projectDetail(String id, PmsProject pmsProject, Model model, RedirectAttributes redirectAttributes) {
        pmsProject = pmsProjectService.getOneProject ( pmsProject.getId () );
        if (StringUtils.isNotBlank ( pmsProject.getSaler ().getId () )) {
            pmsProject.setSaler ( systemService.getUser ( pmsProject.getSaler ().getId () ) );
        }
        if (StringUtils.isNotBlank ( pmsProject.getProducter ().getId () )) {
            pmsProject.setProducter ( systemService.getUser ( pmsProject.getProducter ().getId () ) );
        }
        if (StringUtils.isNotBlank ( pmsProject.getDevloper ().getId () )) {
            pmsProject.setDevloper ( systemService.getUser ( pmsProject.getDevloper ().getId () ) );
        }

        List<ProjectRelativer> plist = pmsProjectService.findPersonList ( pmsProject.getProjectId () );
        String personsName = "";
        String persons = "";
        for (ProjectRelativer ppp : plist) {
            personsName += ppp.getEmployees ().getName () + ",";
        }
        for (ProjectRelativer ppp : plist) {
            persons += ppp.getEmployees ().getId () + ",";
        }
        if (StringUtils.isNotBlank ( persons )) {
            persons = persons.substring ( 0, persons.length () - 1 );
            personsName = personsName.substring ( 0, personsName.length () - 1 );
        }
        pmsProject.setPersons ( persons );
        pmsProject.setPersonsName ( personsName );
        model.addAttribute ( "pmsProject", pmsProject );
        return "modules/oapms/projectView";
    }
}
