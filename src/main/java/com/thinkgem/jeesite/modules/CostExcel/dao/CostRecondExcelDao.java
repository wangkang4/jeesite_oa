package com.thinkgem.jeesite.modules.CostExcel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcel;

import java.util.List;

/**
 * 费用统计Dao
 *
 * @author tanchaoyang
 */
@MyBatisDao
public interface CostRecondExcelDao extends CrudDao<CostRecondExcel> {

    /**
     * 插入CostrecondExcel集合
     */
    public int insertList(List<CostRecondExcel> list);

    /**
     * 获取单条指定id数据
     */
    public CostRecondExcel getone(String id);

    /**
     * 更新一条数据
     */
    public void updateone(CostRecondExcel costRecondExcel);

    /**
     * 插入一条数据
     */
    public void insertone(CostRecondExcel costRecondExcel);

    /**
     * 删除一条数据
     */
    public void deleteone(String id);

    /**
     * 通过用户id获取用户name
     *
     * @param userId 用户id
     * @return 用户name
     */
    public String getUserName(String userId);

    /**
     * 通过部门id获取部门name
     *
     * @param id 部门id
     * @return 部门name
     */
    public String getOffieName(String id);

    /**
     * 更新费用的锁定状态（0：未锁定 1:锁定）
     *
     * @param costRecondExcel id : 费用id
     *                        haveSeee : 锁定状态值
     */
    public void uodateHaveSee(CostRecondExcel costRecondExcel);

}
