package com.thinkgem.jeesite.modules.historyTask.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.historyTask.entity.HistoryTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface HistoryTaskDao extends CrudDao<HistoryTask> {


    /**
     * 获取当前登录者已办任务的总数
     *
     * @param loginName
     * @return
     */
    Integer findNewCont(@Param(value = "loginName") String loginName);

    /**
     * 获取当前登录者已办任务的数据
     *
     * @param startPos  查询起点
     * @param pageSize  查询末点
     * @param loginName
     * @return
     */
    List<HistoryTask> findNewPages(int startPos, int pageSize, String loginName);

    /**
     * 获取到当前登录者已办任务中 指定某人的数据
     *
     * @param startPos
     * @param pageSize
     * @param loginName 前登录者姓名
     * @param string    某人的ID
     * @return
     */
    List<HistoryTask> findNewPagesIndex(int startPos, int pageSize, String loginName, String string);

    /**
     * 获取到当前登录者已办任务中 指定某人的数据的数目
     *
     * @param loginName 前登录者姓名
     * @param string    某人的ID
     * @return
     */
    Integer findNewContIndex(String loginName, String string);

    /**
     * 获取taskId
     *
     * @param procId
     * @param name
     * @return
     */
    String findTaskId(String procId, String name);

    /**
     * 获取任务定义Key
     *
     * @param procId
     * @param name
     * @return
     */
    String findTaskDefKey(String procId, String name);

    /**
     * 标题搜索条数
     *
     * @param loginName
     * @param title
     * @return
     */
    Integer findNewContTitle(String loginName, String title);

    /**
     * 标题搜索数据
     *
     * @param startPos
     * @param pageSize
     * @param loginName
     * @param title
     * @return
     */
    List<HistoryTask> findNewPagesTitle(int startPos, int pageSize, String loginName, String title);

    /**
     * 获取流程名
     *
     * @param prdId
     * @return
     */
    String findPrdName(@Param(value = "prdId") String prdId);

    /**
     * 根据前台流程名搜索数目
     *
     * @param loginName
     * @param preceName
     * @return
     */
    Integer findNewContPreceName(String loginName, String preceName);

    /**
     * 根据前台流程名搜索数据
     *
     * @param startPos
     * @param pageSize
     * @param loginName
     * @param preceName
     * @return
     */
    List<HistoryTask> findNewPagesPreceName(int startPos, int pageSize, String loginName, String preceName);
}
