package com.thinkgem.jeesite.modules.expense.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.expense.entity.CostDescription;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 费用描述dao层
 *
 * @author vat
 */
@MyBatisDao
public interface CostDescriptionDao extends CrudDao<CostDescription> {
    /**
     * 通过关联id查询费用描述表
     */
    List<CostDescription> findById(String costTypeId);

    /**
     * 根据id查找一条数据
     */
    String findByOne(String costDescriptionId);

    /**
     * 查询费用描述表类容
     */
    List<CostDescription> findCostDescription();

    /**
     * 根据费用描述的id，查找费用详情
     */
    List<CostDescription> findByCostTypeId(String costTypeId);

    /**
     * 修改费用描述
     */
    void updateDescription(@Param("detailId") String detailId,
                           @Param("costTypeId") String costTypeId, @Param("costType") String costType,
                           @Param("costDescriptionId") String costDescriptionId, @Param("costDescription") String costDescription);

}
