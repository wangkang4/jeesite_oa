package com.thinkgem.jeesite.modules.CostExcel.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.CostExcel.entity.Client;
import com.thinkgem.jeesite.modules.CostExcel.entity.Project;
import com.thinkgem.jeesite.modules.CostExcel.service.ClientService;
import com.thinkgem.jeesite.modules.CostExcel.service.ProjectService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 项目controller
 *
 * @author tanchaoyang
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/client/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ClientService clientService;

    /**
     * 获取项目集合list
     *
     * @param project 附带检索条件的project实体类集合
     */
    @RequestMapping(value = {"list", ""})
    public String list(Project project, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        List<String> list = UserUtils.getUser ().getRoleIdList ();
        //判定该用户的权限是否有权利操作项目信息
        String rol = "user";
        for (String str : list) {
            if (str.equals ( "a521208da0ff45189d4d316c20161ab8" )) {
                rol = "admin";
            }
        }
        model.addAttribute ( "rol", rol );
        Page<Project> page = projectService.findPage ( new Page<Project> ( request, response ), project );
        model.addAttribute ( "page", page );
        return "modules/CostExcel/Project/ProjectList";
    }

    /**
     * 修改还是新增界面的跳转判定
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "form")
    public String form(String id, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        List<Client> clientList = clientService.getClientList ();
        if (StringUtils.isNotBlank ( id )) {
            Project project = projectService.getById ( id );
            model.addAttribute ( "project", project );
            model.addAttribute ( "clientList", clientList );
            return "modules/CostExcel/Project/ProjectForm";
        } else {
            String projectId = IdGen.uuid ();
            Project project = new Project ();
            project.setId ( projectId );
            model.addAttribute ( "project", project );
            model.addAttribute ( "clientList", clientList );
            return "modules/CostExcel/Project/ProjectAdd";
        }
    }

    @RequestMapping(value = "add")
    public String add(Project project, HttpServletRequest request, HttpServletResponse response,
                      Model model) {
        project.setCreaterBy ( UserUtils.getUser () );
        project.setCreaterTime ( new Date () );
        projectService.addProject ( project );
        return "redirect:" + adminPath + "/oa/client/project/list?repage";
    }

    @RequestMapping(value = "update")
    public String update(Project project, HttpServletRequest request, HttpServletResponse response,
                         Model model) {
        projectService.updateProject ( project );
        return "redirect:" + adminPath + "/oa/client/project/list?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(String id, RedirectAttributes redirectAttributes) {
        projectService.deleteProject ( id );
        addMessage ( redirectAttributes, "删除信息成功" );
        return "redirect:" + adminPath + "/oa/client/project/list?repage";
    }
}
