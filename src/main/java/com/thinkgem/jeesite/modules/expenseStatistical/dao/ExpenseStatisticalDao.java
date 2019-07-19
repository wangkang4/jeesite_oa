package com.thinkgem.jeesite.modules.expenseStatistical.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.expenseStatistical.entity.ExpenseStatistical;
import com.thinkgem.jeesite.modules.expenseStatistical.entity.TypeAndDescription;

import java.util.List;

@MyBatisDao
public interface ExpenseStatisticalDao {
    /**
     * @return List<ExpenseStatistical>   返回类型
     * @throws
     * @Title: selectExpenseStatistical
     * @Description: TODO(按照费用类型聚合查询)
     * @author: WangFucheng
     */
    public List<ExpenseStatistical> selectExpenseStatistical(ExpenseStatistical expenseStatistical);

    /**
     * @return List<String>   返回类型
     * @throws
     * @Title: selectAllCostType
     * @Description: TODO(查询一共有哪些报销类型)
     * @author: WangFucheng
     */
    public List<String> selectAllCostType();

    /**
     * @return List<ExpenseStatistical>   返回类型
     * @throws
     * @Title: selectExpenseDescriptionStatistical
     * @Description: TODO(按照费用类型描述聚合查询)
     * @author: WangFucheng
     */
    public List<ExpenseStatistical> selectExpenseDescriptionStatistical(ExpenseStatistical expenseStatistical);

    /**
     * @return List<String>   返回类型
     * @throws
     * @Title: selectAllCostDescription
     * @Description: TODO(查询一共有哪些报销描述)
     * @author: WangFucheng
     */
    public List<TypeAndDescription> selectAllCostDescription();

}
