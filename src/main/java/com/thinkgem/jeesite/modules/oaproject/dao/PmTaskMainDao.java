/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oaproject.entity.PmTaskMain;

import java.util.List;

/**
 * 单表生成DAO接口
 *
 * @author zhangbingbing
 * @version 2018-03-14
 */
@MyBatisDao
public interface PmTaskMainDao extends CrudDao<PmTaskMain> {
    /**
     * 保存抄送人和责任人
     *
     * @param correlationList
     */
    void saveCorrelationList(List<PmTaskMain> correlationList);

    /**
     * 根据任务id查询对应信息
     *
     * @param taskId
     * @return
     */
    PmTaskMain findPmTaskMain(String taskId);

    /**
     * 根据任务ID查询任务的负责人和抄送人
     *
     * @param id
     * @return
     */
    List<PmTaskMain> getCorrelationList(String id);

    /**
     * 根据任务id删除抄送人和负责人
     *
     * @param id
     */
    void delCorrelation(String id);

    /**
     * 自定义分页查询
     *
     * @param pmTaskMain
     * @return
     */
    List<PmTaskMain> findPageList(PmTaskMain pmTaskMain);

}