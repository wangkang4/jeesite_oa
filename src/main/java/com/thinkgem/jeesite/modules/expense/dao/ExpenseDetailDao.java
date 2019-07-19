package com.thinkgem.jeesite.modules.expense.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.expense.entity.ExpenseDetail;

import java.util.List;

/**
 * 报销详情表dao层
 *
 * @author vat
 */
@MyBatisDao
public interface ExpenseDetailDao extends CrudDao<ExpenseDetail> {

    /**
     * 插入表格数据
     */
    void insertAll(List<ExpenseDetail> list);

    /**
     * 通过id查找信息
     */
    List<ExpenseDetail> findById(String saleDetailId);

    /**
     * 根据id更新一条数据
     */
    void updateById(ExpenseDetail expenseDetail);

    /**
     * 根据关联id删除关联数据
     */
    void deleteAll(String saleDetailIds);

    /**
     * @param expenseDetail void   返回类型
     * @throws
     * @Title: updateAllowMoney
     * @Description: 财务用此来修改实际报销金额
     * @author: WangFucheng
     */
    void updateAllowMoney(ExpenseDetail expenseDetail);

    /**
     * 查询出差编号是否重复
     *
     * @param num
     * @return
     */
    int selectNum(String num);

}
