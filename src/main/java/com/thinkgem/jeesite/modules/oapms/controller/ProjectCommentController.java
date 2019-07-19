package com.thinkgem.jeesite.modules.oapms.controller;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProject;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectComment;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectDocument;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectOperation;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectOperationService;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectService;
import com.thinkgem.jeesite.modules.oapms.services.ProjectCommentService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/pms/comment")
public class ProjectCommentController extends BaseController {

    @Autowired
    public ProjectCommentService projectCommentService;
    @Autowired
    private PmsProjectService pmsProjectService;
    @Autowired
    private PmsProjectOperationService pmsProjectOpertionService;

    @RequestMapping(value = "projectComment")
    public String projectComment(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        PmsProjectComment pmsProjectComment = new PmsProjectComment ();
        PmsProject project = new PmsProject ();
        project.setProjectId ( id );
        pmsProjectComment.setProject ( project );
        Page<PmsProjectComment> page = projectCommentService.findProjectCommentList ( 0, 20, pmsProjectComment );
        model.addAttribute ( "page", page );
        model.addAttribute ( "projectId", id );
        return "modules/oapms/projectComment";
    }

    @ResponseBody
    @RequestMapping(value = "projectCommentMore")
    public List<PmsProjectComment> projectCommentMore(String id, int pageNo) {
        SimpleDateFormat time = new SimpleDateFormat ( "yyyy-MM-dd HH:mm" );
        PmsProjectComment pmsProjectComment = new PmsProjectComment ();
        PmsProject project = new PmsProject ();
        project.setProjectId ( id );
        pmsProjectComment.setProject ( project );
        List<PmsProjectComment> list = projectCommentService.findProjectCommentList ( pageNo, 20, pmsProjectComment ).getList ();
        for (PmsProjectComment ppc : list) {
            ppc.setRemarks ( time.format ( ppc.getCommentTime () ) );
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "commentAdd")
    public Map<String, String> commentAdd(String center, String projectId) {
        PmsProjectComment ppComment = new PmsProjectComment ();
        ppComment.setCommentId ( IdGen.uuid () );
        ppComment.setCommentBy ( UserUtils.getUser () );
        ppComment.setCommentTime ( new Date () );
        ppComment.setContent ( center );

        PmsProject project = pmsProjectService.getOneProject ( projectId );
        ppComment.setProject ( project );

        ppComment.setCreateBy ( UserUtils.getUser () );
        ppComment.setCreateDate ( new Date () );
        ppComment.setUpdateBy ( UserUtils.getUser () );
        ppComment.setUpdateDate ( new Date () );
        ppComment.setDelFlag ( "0" );
        int i = projectCommentService.inserteOneComment ( ppComment );
        Map<String, String> map = new HashMap<String, String> ();
        if (i == 1) {
            map.put ( "data", "success" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( project );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目评论 " + project.getProjectName () + "成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            map.put ( "data", "fail" );
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( project );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "新增项目评论 " + project.getProjectName () + "失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        }
        return map;
    }

    @RequestMapping(value = "projectDocument")
    public String projectDocument(PmsProjectDocument pmsProjectDocument, String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank ( id )) {
            PmsProject pmsProject = new PmsProject ();
            pmsProject.setProjectId ( id );
            pmsProjectDocument.setProject ( pmsProject );
        }
        Page<PmsProjectDocument> page = projectCommentService.findProjectDocumentPage ( new Page<PmsProjectDocument> ( request, response ), pmsProjectDocument );
        model.addAttribute ( "pmsProjectDocument", pmsProjectDocument );
        model.addAttribute ( "page", page );
        return "modules/oapms/projectDocument";
    }

    @RequestMapping(value = "uploadForm")
    public String uploadForm(String id, Model model) {
        model.addAttribute ( "id", id );
        return "modules/oapms/projectDocumentUploadForm";
    }

    /**
     * 附件上传子页面
     *
     * @return
     */
    @RequestMapping("/fileuploads")
    public String fileuploads(String id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        //附件上传页面的跳转
        model.addAttribute ( "id", id );
        return "modules/oapms/index";
    }


    @RequestMapping(value = "uploadfiles")
    public String upload(String id, String files, Model model, RedirectAttributes redirectAttributes) {
        String[] s = files.split ( "\\|" );
        PmsProjectDocument ppd = new PmsProjectDocument ();
        PmsProject pp = pmsProjectService.getOneProject ( id );
        int i = 0;
        for (String a : s) {
            if (StringUtils.isNoneBlank ( a )) {
                ppd.setProject ( pp );
                ppd.setDocumentId ( a );
                projectCommentService.updateDocumentInfo ( ppd );
                i++;
            }
        }
        if (0 != i) {
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "项目 " + pp.getProjectName () + "上传 " + i + "个文件成功" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        } else {
            PmsProjectOperation pmsProjectOperation = new PmsProjectOperation ();
            pmsProjectOperation.setOperationId ( IdGen.uuid () );
            pmsProjectOperation.setProject ( pp );
            pmsProjectOperation.setOperationBy ( UserUtils.getUser () );
            pmsProjectOperation.setOperationTime ( new Date () );
            pmsProjectOperation.setContent ( "项目 " + pp.getProjectName () + "上传 " + "文件失败" );
            pmsProjectOperation.setCreateBy ( UserUtils.getUser () );
            pmsProjectOperation.setCreateDate ( new Date () );
            pmsProjectOperation.setUpdateBy ( UserUtils.getUser () );
            pmsProjectOperation.setUpdateDate ( new Date () );
            pmsProjectOperation.setDelFlag ( "0" );
            pmsProjectOpertionService.insertOneOpertion ( pmsProjectOperation );
        }
        addMessage ( redirectAttributes, "保存保存成功成功" );
        return "redirect:" + Global.getAdminPath () + "/pms/comment/projectDocument?repage&id=" + id;
    }

}
