package com.thinkgem.jeesite.modules.que.controller;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.que.entity.QuestionFeedback;
import com.thinkgem.jeesite.modules.que.entity.QuestionRecord;
import com.thinkgem.jeesite.modules.que.entity.ResponseResult;
import com.thinkgem.jeesite.modules.que.service.QuestionFeedbackService;
import com.thinkgem.jeesite.modules.que.service.QuestionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping(value = "${adminPath}/pms/feedback")
public class QuestionFeedbackController extends BaseController {

    /**
     * 问题反馈service
     */
    @Autowired
    private QuestionFeedbackService questionFeedbackService;

    /**
     * 问题记录service
     */
    @Autowired
    private QuestionRecordService questionRecordService;

    /**
     * 问题id
     */
    private String problemId;

    /**
     * 问题反馈对象
     */
    private QuestionFeedback queFeedback;

    /**
     * 根据问题id查找问题反馈
     *
     * @param id       问题id
     * @param modelMap
     * @return 跳转问题反馈jsp页面
     */
    @RequestMapping(value = "showQuestionFeedback")
    @ResponseBody
    public ResponseResult<Void> showQuestionFeedback(String id, HttpSession session, String problemName, String projectName) {
        //声明返回值
        ResponseResult<Void> rr = null;
        QuestionRecord record = questionRecordService.findQuestionByQuestionId ( id );
        QuestionFeedback feedback = questionFeedbackService.findQuestionFeedbackByProblemId ( id );
        if (feedback != null) {
            feedback.setQuestionRecord ( record );
        }
        System.out.println ( "问题对象是：" + record );
        System.out.println ( "问题id是：" + id );
        System.out.println ( "反馈对象是：" + feedback );
        System.out.println ( "问题标题是：=======" + problemName );
        System.out.println ( "项目名是：=======" + projectName );
        session.setAttribute ( "feedback", feedback );
        session.setAttribute ( "feedbackProjectName", projectName );
        session.setAttribute ( "feedbackProblemName", problemName );
        problemId = id;
        queFeedback = feedback;
        rr = new ResponseResult<Void> ( 1, "请求问题反馈" );
        return rr;
    }

    /**
     * 展示反馈页面
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "showFeedback")
    public String showFeedback(ModelMap modelMap) {
        System.out.println ( "问题的id-------" + problemId );
        modelMap.addAttribute ( "problemId", problemId );
        return "modules/oapms/que/updateFeedback";
    }

    /**
     * 修改或添加问题反馈
     *
     * @param questionFeedback 问题反馈对象
     * @return 返回responseBody对象
     */
    @RequestMapping(value = "updateFeedback")
    @ResponseBody
    public ResponseResult<Void> updateQuestionFeedback(QuestionFeedback questionFeedback) {
        //声明返回值
        ResponseResult<Void> rr = null;
        if (queFeedback == null) {
            questionFeedback.setId ( UUID.randomUUID ().toString () );
            questionFeedbackService.insertQuestionFeedback ( questionFeedback );
            rr = new ResponseResult<Void> ( 1, "问题反馈添加成功" );
        } else {
            questionFeedback.setProblemId ( problemId );
            System.out.println ( "需要修改反馈信息的问题id是：" + problemId );
            System.out.println ( "修改后的反馈是：" + questionFeedback );
            questionFeedbackService.updateQuestionFeedback ( questionFeedback );
            rr = new ResponseResult<Void> ( 2, "问题反馈修改成功" );
        }
        return rr;
    }

}
