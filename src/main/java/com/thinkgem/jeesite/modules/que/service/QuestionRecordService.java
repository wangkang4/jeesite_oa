package com.thinkgem.jeesite.modules.que.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.que.dao.QuestionRecordDao;
import com.thinkgem.jeesite.modules.que.entity.QuestionRecord;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class QuestionRecordService extends CrudService<QuestionRecordDao, QuestionRecord> {

    @Autowired
    private QuestionRecordDao questionRecordDao;

    public Page<QuestionRecord> findPage(Page<QuestionRecord> page, QuestionRecord questionRecord) {
        questionRecord.setPage ( page );
        List<QuestionRecord> list = dao.findList ( questionRecord );
        if ("1".equals ( UserUtils.getUser ().getId () )) {
            page.setList ( list );
            return page;
        }

        return page;
    }

    /**
     * 获取所有项目问题列表
     *
     * @return 返回项目问题对象的List集合
     */
    public List<QuestionRecord> getAllQuestionForm() {
        return questionRecordDao.showAllQuestionForm ();
    }

    /**
     * 根据问题id删除问题（添加删除标识）
     *
     * @param id 问题id
     * @return 返回受影响的数据函数
     */
    @Transactional(readOnly = false)
    public Integer deleteQuestionByQuestionId(String id) {
        return questionRecordDao.deleteQuestionByQuestionId ( id );
    }

    /**
     * 根据项目名称和问题状态查询项目问题列表
     *
     * @param questionRecord 问题记录对象
     * @return 符合条件的项目问题列表集合
     */
    public List<QuestionRecord> findQuestionByProjectNameAndQuestionState(
            @RequestParam("questionRecord") QuestionRecord questionRecord) {
        return questionRecordDao.findQuestionByProjectNameAndQuestionState ( questionRecord );
    }

    /**
     * 根据问题id查询问题
     *
     * @param id 问题id
     * @return 问题记录对象
     */
    public QuestionRecord findQuestionByQuestionId(String id) {
        return questionRecordDao.findQuestionByQuestionId ( id );
    }

    /**
     * 添加问题
     *
     * @param questionRecord 问题对象
     * @return 受影响的数据行数
     */
    @Transactional(readOnly = false)
    public Integer insertQuestion(QuestionRecord questionRecord) {
        return questionRecordDao.insertQuestion ( questionRecord );
    }

    /**
     * 根据项目名称查询项目的id
     *
     * @param projectName 项目名称
     * @return 返回项目id
     */
    public String findProjectIdByProjectName(String projectName) {
        return questionRecordDao.findProjectIdByProjectName ( projectName );
    }

    /**
     * 修改问题
     *
     * @param questionRecord 问题对象
     * @return 受影响的数据行数
     */
    @Transactional(readOnly = false)
    public Integer updateQuestionByQuestion(QuestionRecord questionRecord) {
        return questionRecordDao.updateQuestionByQuestion ( questionRecord );
    }

    /**
     * 根据id查询附件名字
     *
     * @param attachmentAddress
     * @return 附件名称
     */
    public String selectAttachmentById(String attachId) {
        return questionRecordDao.selectAttachmentById ( attachId );
    }

    /**
     * 查找所有的项目
     *
     * @return 项目名的集合
     */
    public List<String> findAllProjectName() {
        return questionRecordDao.findAllProjectName ();
    }

    /**
     * 通过附件名查找附件的地址
     *
     * @param attachId
     * @return 附件地址
     */
    public String findAttachAddressByAttachId(String attachId) {
        return questionRecordDao.findAttachAddressByAttachId ( attachId );
    }

    /**
     * 通过附件地址查找附件的名字
     *
     * @param attachAddress
     * @return 附件名字
     */
    public String findAttachIdByAttachAddress(String attachAddress) {
        return questionRecordDao.findAttachIdByAttachAddress ( attachAddress );
    }
}
