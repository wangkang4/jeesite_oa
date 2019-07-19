package com.thinkgem.jeesite.modules.expense.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.expense.entity.CostType;
import com.thinkgem.jeesite.sale.entity.Cost;

import java.util.List;

/**
 * 费用分类dao层
 *
 * @author vat
 */
@MyBatisDao
public interface CostTypeDao extends CrudDao<CostType> {
    /**
     * 查询费用分类表类容
     */
    List<CostType> findCostType();

    /**
     * 根据id查找一条数据
     */
    String findById(String costTypeId);

    /**
     * @return List<Cost>   返回类型
     * @throws
     * @Title: selectCostDescription
     * @Description: 查询费用描述和费用类型
     * @author: WangFucheng
     */
    List<Cost> selectCostDescription();

}
