package com.thinkgem.jeesite.modules.que.service;

import com.thinkgem.jeesite.modules.que.dao.QuestionFeedbackDao;
import com.thinkgem.jeesite.modules.que.entity.QuestionFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionFeedbackService {

    @Autowired
    private QuestionFeedbackDao questionFeedbackDao;

    /**
     * 新增问题反馈
     *
     * @param questionFeedback 问题反馈对象
     * @return 受影响的数据行数
     */
    @Transactional(readOnly = false)
    public Integer insertQuestionFeedback(QuestionFeedback questionFeedback) {
        return questionFeedbackDao.insertQuestionFeedback ( questionFeedback );
    }

    /**
     * 根据问题id查找问题反馈
     *
     * @param problemId 问题id
     * @return 问题反馈对象
     */
    public QuestionFeedback findQuestionFeedbackByProblemId(String problemId) {
        return questionFeedbackDao.findQuestionFeedbackByProblemId ( problemId );
    }

    /**
     * 修改问题反馈
     *
     * @param questionFeedback 问题反馈对象
     * @return 问题反馈对象
     */
    @Transactional(readOnly = false)
    public Integer updateQuestionFeedback(QuestionFeedback questionFeedback) {
        return questionFeedbackDao.updateQuestionFeedback ( questionFeedback );
    }
}
