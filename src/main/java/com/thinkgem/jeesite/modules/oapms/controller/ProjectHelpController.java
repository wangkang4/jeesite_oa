package com.thinkgem.jeesite.modules.oapms.controller;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oapms.entity.*;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectOperationService;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectService;
import com.thinkgem.jeesite.modules.oapms.services.ProjectHelpService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping(value = "${adminPath}/pms/projectHelp")
public class ProjectHelpController extends BaseController {

    @Autowired
    private ProjectHelpService projectHelpService;
    @Autowired
    private PmsProjectOperationService pmsProjectOpertionService;
    @Autowired
    private PmsProjectService pmsProjectService;

    @RequestMapping(value = "helpList")
    public String helpList(String id, PmsProjectHelp pmsProjectHelp, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank ( id )) {
            PmsProject project = new PmsProject ();
            project.setProjectId ( id );
            pmsProjectHelp.setProject ( project );
        }
        Page<PmsProjectHelp> page = projectHelpService.findProjectHelpList ( new Page<PmsProjectHelp> ( request, response ), pmsProjectHelp );
        model.addAttribute ( "pmsProjectHelp", pmsProjectHelp );
        model.addAttribute ( "page", page );
        return "modules/oapms/projectHelp";
    }

    @RequestMapping(value = "form")
    public String form(PmsProjectHelp pmsProjectHelp, String id, String helpId, Model model) {
        String flag = "";
        if (StringUtils.isNotBlank ( id )) {
            PmsProject pmsProject = projectHelpService.getOnePmsProjectByProjectId ( id );
            model.addAttribute ( "pmsProject", pmsProject );
            flag = "modules/oapms/projectHelpAdd";
        }
        if (StringUtils.isNotBlank ( helpId )) {
            pmsProjectHelp = projectHelpService.getOneProjectHelp ( helpId );
            model.addAttribute ( "pmsProjectHelp", pmsProjectHelp );
            flag = "modules/oapms/projectHelpForm";
        }
        return flag;
    }

    @RequestMapping(value = "helpAdd")
    public String helpAdd(PmsProjectHelp pmsProjectHelp, Model model, RedirectAttributes redirectAttributes) {
        pmsProjectHelp.setHelpId ( IdGen.uuid () );
        pmsProjectHelp.setHelpTime ( new Date () );
        pmsProjectHelp.setCreateBy ( UserUtils.getUser () );
        pmsProjectHelp.setCreateDate ( new Date () );
        pmsProjectHelp.setUpdateBy ( UserUtils.getUser () );
        pmsProjectHelp.setUpdateDate ( new Date () );
        pmsProjectHelp.setDelFlag ( "0" );

        PmsProject pp = pmsProjectService.getOneProject ( pmsProjectHelp.getProject ().getProjectId () );

        int i = projectHelpService.addOneProjectHelp ( pmsProjectHelp );
        if (1 != i) {
            addMessage ( redirectAttributes, "保存保存失败失败" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目 " + pp.getProjectName () + "求助失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            if (StringUtils.isNoneBlank ( pmsProjectHelp.getHelper ().getId () )) {
                ProjectRelativer pr = new ProjectRelativer ();
                pr.setId ( pmsProjectHelp.getProject ().getProjectId () );
                pr.setRelativeType ( "1" );
                pr.setEmployees ( pmsProjectHelp.getHelper () );
                pr.setEmployeesType ( "协助者" );
                projectHelpService.insertHelperToRelative ( pr );
            }
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目 " + pp.getProjectName () + "求助成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
            addMessage ( redirectAttributes, "保存保存成功成功" );
        }
        return "redirect:" + Global.getAdminPath () + "/pms/projectHelp/helpList?repage&id=" + pmsProjectHelp.getProject ().getProjectId ();
    }

    @RequestMapping(value = "helpForm")
    public String helpForm(PmsProjectHelp pmsProjectHelp, Model model, RedirectAttributes redirectAttributes) {
        pmsProjectHelp.setUpdateBy ( UserUtils.getUser () );
        pmsProjectHelp.setUpdateDate ( new Date () );
        PmsProjectHelp phbefore = projectHelpService.getOneProjectHelp ( pmsProjectHelp.getHelpId () );

        PmsProject pp = pmsProjectService.getOneProject ( pmsProjectHelp.getProject ().getProjectId () );

        int i = projectHelpService.updateOneProjectHelp ( pmsProjectHelp );
        if (1 != i) {
            addMessage ( redirectAttributes, "修改修改失败失败" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "修改项目 " + pp.getProjectName () + "求助失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            if (!phbefore.getHelper ().getId ().equals ( pmsProjectHelp.getHelper ().getId () )) {
                if (StringUtils.isNotBlank ( pmsProjectHelp.getHelper ().getId () )) {
                    ProjectRelativer pr = new ProjectRelativer ();
                    pr.setId ( pmsProjectHelp.getProject ().getProjectId () );
                    pr.setRelativeType ( "1" );
                    pr.setEmployees ( pmsProjectHelp.getHelper () );
                    pr.setEmployeesType ( "协助者" );
                    projectHelpService.insertHelperToRelative ( pr );
                }
                PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
                pmsProjectOperation.setOperationId ( IdGen.uuid () );
                pmsProjectOperation.setProject ( pp );
                pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
                pmsProjectOperation.setOperationTime ( new Date () );
                pmsProjectOperation.setContent ( "修改项目 " + pp.getProjectName () + "求助成功" );
                pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
                pmsProjectOperation.setCreateDate ( new Date () );
                pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
                pmsProjectOperation.setUpdateDate ( new Date () );
                pmsProjectOperation.setDelFlag ( "0" );
                pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
                addMessage ( redirectAttributes, "保存保存成功成功" );
            }
        }
        return "redirect:" + Global.getAdminPath () + "/pms/projectHelp/helpList?repage&id=" + pmsProjectHelp.getProject ().getProjectId ();
    }

    @RequestMapping(value = "delete")
    public String delete(PmsProjectHelp pmsProjectHelp, String id, Model model, RedirectAttributes redirectAttributes) {
        projectHelpService.deleteOneProjectHelp ( id );
        pmsProjectHelp = projectHelpService.getOneProjectHelp ( id );
        PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
        pmsProjectOperation.setOperationId ( IdGen.uuid () );
        pmsProjectOperation.setProject ( pmsProjectHelp.getProject () );
        pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
        pmsProjectOperation.setOperationTime ( new Date () );
        pmsProjectOperation.setContent ( "删除项目 " + pmsProjectHelp.getProject ().getProjectName () + "求助成功" );
        pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
        pmsProjectOperation.setCreateDate ( new Date () );
        pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
        pmsProjectOperation.setUpdateDate ( new Date () );
        pmsProjectOperation.setDelFlag ( "0" );
        pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        return "redirect:" + Global.getAdminPath () + "/pms/projectHelp/helpList?repage&id=" + id;
    }

    @RequestMapping("expenseList")
    public String expenseList(String id, PmsProjectExpense pmsProjectExpense, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank ( id )) {
            PmsProject pp = new PmsProject ();
            pp.setProjectId ( id );
            pmsProjectExpense.setProject ( pp );
        }
        Page<PmsProjectExpense> page = projectHelpService.findProjectExpenseList ( new Page<PmsProjectExpense> ( request, response ), pmsProjectExpense );
        model.addAttribute ( "pmsProjectExpense", pmsProjectExpense );
        model.addAttribute ( "page", page );
        return "modules/oapms/projectExpense";
    }

    @RequestMapping(value = "expenseForm")
    public String expenseForm(PmsProjectExpense pmsProjectExpense, String id, String expenseId, Model model) {
        String flag = "";
        if (StringUtils.isNotBlank ( id )) {
            PmsProject pmsProject = projectHelpService.getOnePmsProjectByProjectId ( id );
            model.addAttribute ( "pmsProject", pmsProject );
            flag = "modules/oapms/projectExpenseAdd";
        }
        if (StringUtils.isNotBlank ( expenseId )) {
            pmsProjectExpense = projectHelpService.getOneProjectExpense ( expenseId );
            model.addAttribute ( "pmsProjectExpense", pmsProjectExpense );
            flag = "modules/oapms/projectExpenseForm";
        }
        return flag;
    }

    @RequestMapping(value = "expenseAdd")
    public String expenseAdd(PmsProjectExpense pmsProjectExpense, Model model, RedirectAttributes redirectAttributes) {
        pmsProjectExpense.setExpenseId ( IdGen.uuid () );
        pmsProjectExpense.setCreateDate ( new Date () );
        pmsProjectExpense.setUpdateBy ( UserUtils.getUser () );
        pmsProjectExpense.setUpdateDate ( new Date () );
        pmsProjectExpense.setDelFlag ( "0" );

        PmsProject pp = pmsProjectService.getOneProject ( pmsProjectExpense.getProject ().getProjectId () );

        int i = projectHelpService.addOneProjectExpense ( pmsProjectExpense );
        if (1 != i) {
            addMessage ( redirectAttributes, "保存保存失败失败" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目 " + pp.getProjectName () + "费用失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            addMessage ( redirectAttributes, "保存保存成功成功" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目 " + pp.getProjectName () + "费用成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        }
        return "redirect:" + Global.getAdminPath () + "/pms/projectHelp/expenseList?repage&id=" + pmsProjectExpense.getProject ().getProjectId ();
    }

    @RequestMapping(value = "expenseFormTo")
    public String expenseFormTo(PmsProjectExpense pmsProjectExpense, Model model, RedirectAttributes redirectAttributes) {
        pmsProjectExpense.setUpdateBy ( UserUtils.getUser () );
        pmsProjectExpense.setUpdateDate ( new Date () );

        PmsProject pp = pmsProjectService.getOneProject ( pmsProjectExpense.getProject ().getProjectId () );

        int i = projectHelpService.updateOneProjectExpense ( pmsProjectExpense );
        if (1 != i) {
            addMessage ( redirectAttributes, "保存保存失败失败" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目 " + pp.getProjectName () + "费用失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            addMessage ( redirectAttributes, "保存保存成功成功" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目 " + pp.getProjectName () + "费用成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        }
        return "redirect:" + Global.getAdminPath () + "/pms/projectHelp/expenseList?repage&id=" + pmsProjectExpense.getProject ().getProjectId ();
    }

    @RequestMapping(value = "expenseDelete")
    public String expenseDelete(PmsProjectExpense pmsProjectExpense, String id, Model model, RedirectAttributes redirectAttributes) {
        int i = projectHelpService.deleteOneProjectExpense ( id );
        pmsProjectExpense = projectHelpService.getOneProjectExpense ( id );
        if (1 != i) {
            addMessage ( redirectAttributes, "删除删除失败失败" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pmsProjectExpense.getProject () );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "删除项目 " + pmsProjectExpense.getProject ().getProjectName () + "费用失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            addMessage ( redirectAttributes, "删除删除成功成功" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pmsProjectExpense.getProject () );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "删除项目 " + pmsProjectExpense.getProject ().getProjectName () + "费用成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        }
        return "redirect:" + Global.getAdminPath () + "/pms/projectHelp/expenseList?repage&id=" + id;
    }
}
