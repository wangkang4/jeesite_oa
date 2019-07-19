package com.thinkgem.jeesite.modules.costList.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.costList.entity.CostDetails;

import java.util.List;

/**
 * 费用详情表Dao层
 *
 * @author shengchanghao
 */
@MyBatisDao
public interface CostDetailsDao extends CrudDao<CostDetails> {
    /**
     * 通过关联id查询费用详情表
     */
    List<CostDetails> findById(Integer costId);


}
