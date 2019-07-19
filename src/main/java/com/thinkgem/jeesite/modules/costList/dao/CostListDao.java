package com.thinkgem.jeesite.modules.costList.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.costList.entity.CostList;

import java.util.List;

/**
 * 费用统计总表dao层
 *
 * @author shengchanghao
 */
@MyBatisDao
public interface CostListDao extends CrudDao<CostList> {

    /**
     * 查询统计总表的内容
     */
    List<CostList> findCostList();

    /**
     *
     */


}
