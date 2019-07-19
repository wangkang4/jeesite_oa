package com.thinkgem.jeesite.modules.que.controller;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProject;
import com.thinkgem.jeesite.modules.que.entity.QuestionRecord;
import com.thinkgem.jeesite.modules.que.entity.ResponseResult;
import com.thinkgem.jeesite.modules.que.service.QuestionRecordService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "${adminPath}/pms/question")
public class QuestionRecordController extends BaseController {

    @Autowired
    private QuestionRecordService questionRecordService;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 问题id
     */
    private String problemId;

    /**
     * 附件地址
     */
    private String address;

    /**
     * 展示项目所有问题
     *
     * @param modelMap
     * @return 跳转到jsp页面
     */
    @RequestMapping(value = "showQuestionForm")
    public String showQuestionForm(ModelMap modelMap,
                                   QuestionRecord questionRecord,
                                   String projectName, String problemState,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        if ("--请选择--".equals ( problemState )) {
            problemState = null;
        }
        questionRecord.setProblemState ( problemState );
        PmsProject pms = new PmsProject ();
        pms.setProjectName ( projectName );
        questionRecord.setPms ( pms );
        modelMap.addAttribute ( "projectName", projectName );
        modelMap.addAttribute ( "problemState", problemState );
        System.out.println ( "问题状态是=====" + problemState );
        Page<QuestionRecord> page = questionRecordService.findPage ( new Page<QuestionRecord> ( request, response ), questionRecord );
        modelMap.addAttribute ( "page", page );
        return "modules/oapms/que/questionForm";
    }


    /**
     * 根据问题id删除问题（给问题添加删除标识）
     *
     * @param id 问题id
     * @return 返回responseBody对象
     */
    @RequestMapping(value = "deleteQuestion")
    public String deleteQuestion(String id) {
        questionRecordService.deleteQuestionByQuestionId ( id );
        return "redirect:showQuestionForm";
    }

    /**
     * 展示添加问题页面
     *
     * @return jsp页面
     */
    @RequestMapping(value = "showAddQuestion")
    public String showAddQuestion() {
        return "modules/oapms/que/addQuestion";
    }

    /**
     * 展示处理问题页面
     *
     * @return jsp页面
     */
    @RequestMapping(value = "showUpdateQuestion")
    public String showUpdateQuestion(ModelMap modelMap, String id) {
        //System.out.println("问题的id："+id);
        QuestionRecord question = questionRecordService.findQuestionByQuestionId ( id );
        modelMap.addAttribute ( "question", question );
        problemId = id;
        return "modules/oapms/que/updateQuestion";
    }

    /**
     * 处理添加问题请求
     *
     * @param questionRecord
     * @param modelMap
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "addQuestion")
    public String handleAddQuestion(
            QuestionRecord questionRecord,
            ModelMap modelMap) throws UnsupportedEncodingException {
        if ("--请选择--".equals ( questionRecord.getProblemSource () )) {
            questionRecord.setProblemSource ( null );
        }
        if ("--请选择--".equals ( questionRecord.getProblemState () )) {
            questionRecord.setProblemState ( null );
        }
        if ("--请选择--".equals ( questionRecord.getProblemUrgent () )) {
            questionRecord.setProblemUrgent ( null );
        }
        questionRecord.setAttachAddress ( address );
        questionRecord.setId ( UUID.randomUUID ().toString () );
        questionRecord.setProjectId ( projectId );
        questionRecord.setCreateBy ( UserUtils.getUser () );
        questionRecordService.insertQuestion ( questionRecord );
        System.out.println ( "执行insert===" + questionRecord );
        System.out.println ( "附件的id是===========" + questionRecord.getAttachId () );
        return "redirect:showQuestionForm";
    }

    /**
     * 处理修改问题请求
     *
     * @param questionRecord
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "updateQuestion")
    public String handleUpdateQuestion(
            QuestionRecord questionRecord,
            ModelMap modelMap) {
        questionRecord.setAttachAddress ( address );
        questionRecord.setId ( problemId );
        questionRecord.setUpdateBy ( UserUtils.getUser () );
        questionRecordService.updateQuestionByQuestion ( questionRecord );
        System.out.println ( "执行update===" + questionRecord );
        return "redirect:showQuestionForm";
    }


    /**
     * 根据项目名得到项目id
     *
     * @param projectName 项目名
     * @return 返回ResponseBody提示用户信息
     */
    @RequestMapping(value = "getProjectId")
    public String getProjectIdbyProjectName(String projectName) {
        String info = questionRecordService.findProjectIdByProjectName ( projectName );
        System.out.println ( "info：" + info );
        projectId = info;
        return null;
    }

    /**
     * 获得所有项目
     *
     * @return 项目名的集合
     */
    @RequestMapping(value = "getAllProject")
    @ResponseBody
    public List<String> findAllProjectName() {
        List<String> projectNames = questionRecordService.findAllProjectName ();
        return projectNames;
    }

    /**
     * 下载附件
     *
     * @param attachId 附件名
     * @param request
     * @param response
     * @throws IOException
     * @return null
     */
    @RequestMapping(value = "download")
    @ResponseBody
    public ResponseResult<Void> fileDownload(
            //String attachId,
            String attachAddress, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String attachId = questionRecordService.findAttachIdByAttachAddress ( attachAddress );
        String[] str = attachId.split ( "\\." );
        String suffix = str[1];
        String downloadPrefix = Global.getConfig ( "windowsUploadPath" );
        String url = downloadPrefix + attachAddress + "." + suffix;
        System.out.println ( "url========" + url );
        try {
            File file = new File ( url );
            // 清空response
            response.reset ();
            // 设置response的Header
            response.addHeader ( "Content-Disposition",
                    "attachment;filename=" + new String ( attachId.getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
            response.addHeader ( "Content-Length", "" + file.length () );
            // 以流的形式下载文件
            InputStream is = new BufferedInputStream ( new FileInputStream ( url ) );
            byte[] buffer = new byte[is.available ()];
            is.read ( buffer );
            is.close ();

            OutputStream os = new BufferedOutputStream ( response.getOutputStream () );
            os.write ( buffer );
            os.flush ();
            os.close ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }


    /**
     * 上传附件（上传单个文件）
     *
     * @param attach  附件
     * @param request
     * @throws IOException
     * @throws InvalidFormatException
     * @return ResponseBody对象
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> upload(
            @RequestParam MultipartFile attach,
            HttpServletRequest request)
            throws IOException, InvalidFormatException {
        System.out.println ( "进去上传的方法啦-----------" );
        System.out.println ( "文件对象：" + attach );
        String attachAddress = IdGen.uuid ();                //更改
        String filename = attach.getOriginalFilename ();

        System.out.println ( "文件名称：" + attach.getOriginalFilename () );
        String[] str = filename.split ( "\\." );
        String suffix = str[1];
        System.out.println ( "上传时候的文件后缀：" + suffix );
        //输出实际路径
        String uploadHome = Global.getConfig ( "windowsUploadPath" );
        String path = uploadHome + attachAddress + "." + suffix;
        System.out.println ( "文件路径：" + path );
        File file = new File ( path );
        attach.transferTo ( file );
        address = attachAddress;
        //返回结果
        return new ResponseResult<Void> ( 1, "上传成功" );
    }

}
