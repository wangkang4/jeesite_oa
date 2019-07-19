package com.thinkgem.jeesite.modules.daily.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;

import java.util.List;

/**
 * 日报周报关联Dao
 *
 * @author ShiLiangYu
 * @version 2017-8-2
 */
@MyBatisDao
public interface TCheckBacklogDao extends CrudDao<TCheckBacklog> {

    /**
     * 插入周报连接表数据
     */
    public int insertAll(List<TCheckBacklog> tCheckBacklogList);

    /**
     * 根据id删除中间表记录
     *
     * @param id
     */
    public void deleteone(String id);

    /**
     * 根据id查找中间表记录
     *
     * @param id
     * @return
     */
    public TCheckBacklog findone(String id);


    public int findYstayCount(TCheckBacklog tCheckBacklog);

    //获取提交人数；
    public int getNum();

}
