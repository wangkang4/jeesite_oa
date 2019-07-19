package com.thinkgem.jeesite.modules.historyTask.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.historyTask.dao.HistoryTaskDao;
import com.thinkgem.jeesite.modules.historyTask.entity.HistoryTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoryTaskService extends CrudService<HistoryTaskDao, HistoryTask> {

    @Autowired
    private HistoryTaskDao historyTaskDao;

    /**
     * 获取数目
     *
     * @param historyTask
     * @return
     */
    @Transactional(readOnly = false)
    public Integer findNewCont(HistoryTask historyTask) {
        /**
         * 如果是人名搜索
         */
        if (historyTask.getUserId () != null && historyTask.getUserId ().length () > 2) {
            return historyTaskDao.findNewContIndex ( historyTask.getLoginName (), historyTask.getUserId () );
        }
        /**
         * 如果是标题搜索
         */
        else if (historyTask.getTitle () != null && historyTask.getTitle ().length () > 1) {
            System.out.println ( historyTask.getTitle () );
            return historyTaskDao.findNewContTitle ( historyTask.getLoginName (), historyTask.getTitle () );
        }
        /**
         * 如果是流程名搜做
         */
        else if (historyTask.getPreceName () != null && historyTask.getPreceName ().length () > 1) {
            return historyTaskDao.findNewContPreceName ( historyTask.getLoginName (), historyTask.getPreceName () );
        }
        return historyTaskDao.findNewCont ( historyTask.getLoginName () );
    }

    /**
     * 获取数据
     *
     * @param startPos
     * @param pageSize
     * @param historyTask
     * @return
     */
    @Transactional(readOnly = false)
    public List<HistoryTask> findNewsPage(int startPos, int pageSize, HistoryTask historyTask) {
        /**
         * 如果是人名搜索
         */
        if (historyTask.getUserId () != null && historyTask.getUserId ().length () > 2) {
            return historyTaskDao.findNewPagesIndex ( startPos, pageSize, historyTask.getLoginName (), historyTask.getUserId () );
        }
        /**
         * 如果是标题搜索
         */
        else if (historyTask.getTitle () != null && historyTask.getTitle ().length () > 1) {
            return historyTaskDao.findNewPagesTitle ( startPos, pageSize, historyTask.getLoginName (), historyTask.getTitle () );
        }
        /**
         * 如果是流程名搜索
         */
        else if (historyTask.getPreceName () != null && historyTask.getPreceName ().length () > 1) {
            return historyTaskDao.findNewPagesPreceName ( startPos, pageSize, historyTask.getLoginName (), historyTask.getPreceName () );
        }
        return historyTaskDao.findNewPages ( startPos, pageSize, historyTask.getLoginName () );
    }

    /**
     * 获取taskId
     *
     * @param procId
     * @param string
     * @return
     */
    @Transactional(readOnly = false)
    public String findTaskId(String procId, String name) {
        return historyTaskDao.findTaskId ( procId, name );
    }

    /**
     * 获取任务定义Key
     *
     * @param procId
     * @param string
     * @return
     */
    @Transactional(readOnly = false)
    public String findTaskDefKey(String procId, String name) {
        return historyTaskDao.findTaskDefKey ( procId, name );
    }

    @Transactional(readOnly = false)
    public String findPrdName(String prdId) {
        return historyTaskDao.findPrdName ( prdId );
    }

}
