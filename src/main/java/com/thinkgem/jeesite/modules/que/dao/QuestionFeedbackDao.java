package com.thinkgem.jeesite.modules.que.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.que.entity.QuestionFeedback;

@MyBatisDao
public interface QuestionFeedbackDao {

    /**
     * 新增问题反馈
     *
     * @param questionFeedback 问题反馈对象
     * @return 受影响的数据行数
     */
    public Integer insertQuestionFeedback(QuestionFeedback questionFeedback);

    /**
     * 根据问题id查找问题反馈
     *
     * @param problemId 问题id
     * @return 问题反馈对象
     */
    public QuestionFeedback findQuestionFeedbackByProblemId(String problemId);

    /**
     * 修改问题反馈
     *
     * @param questionFeedback 问题反馈对象
     * @return 问题反馈对象
     */
    public Integer updateQuestionFeedback(QuestionFeedback questionFeedback);
}
