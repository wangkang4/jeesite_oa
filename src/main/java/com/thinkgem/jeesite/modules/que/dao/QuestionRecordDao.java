package com.thinkgem.jeesite.modules.que.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.que.entity.QuestionRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 项目问题记录Dao
 *
 * @author chengxin
 */
@MyBatisDao
public interface QuestionRecordDao extends CrudDao<QuestionRecord> {

    /**
     * 展示所有项目问题
     *
     * @return 项目问题集合
     */
    public List<QuestionRecord> showAllQuestionForm();

    /**
     * 根据项目名称查询项目的id
     *
     * @param projectName 项目名称
     * @return 返回项目id
     */
    public String findProjectIdByProjectName(String projectName);


    /**
     * 根据项目名称和问题状态查询项目问题列表
     *
     * @param questionRecord 问题记录对象
     * @return 项目问题列表集合
     */
    public List<QuestionRecord> findQuestionByProjectNameAndQuestionState(
            @RequestParam("questionRecord") QuestionRecord questionRecord);

    /**
     * 根据问题id删除问题
     *
     * @param id 问题id
     * @return 受影响的数据行数
     */
    public Integer deleteQuestionByQuestionId(String id);

    /**
     * 根据问题id查询问题
     *
     * @param id 问题id
     * @return 问题记录对象
     */
    public QuestionRecord findQuestionByQuestionId(String id);

    /**
     * 添加问题
     *
     * @param questionRecord 问题对象
     * @return 受影响的数据行数
     */
    public Integer insertQuestion(QuestionRecord questionRecord);


    /**
     * 修改问题
     *
     * @param questionRecord 问题对象
     * @return 受影响的数据行数
     */
    public Integer updateQuestionByQuestion(QuestionRecord questionRecord);

    /**
     * @return String   返回类型
     * @throws
     * @Title: selectAttachmentById 根据id查询附件名字
     */
    public String selectAttachmentById(@Param("attachId") String attachId);

    /**
     * 查找所有的项目
     *
     * @return 项目名的集合
     */
    public List<String> findAllProjectName();

    /**
     * 通过附件名查找附件的地址
     *
     * @param attachId
     * @return 附件地址
     */
    public String findAttachAddressByAttachId(String attachId);

    /**
     * 通过附件地址查找附件的名字
     *
     * @param attachAddress
     * @return 附件名字
     */
    public String findAttachIdByAttachAddress(String attachAddress);
}
