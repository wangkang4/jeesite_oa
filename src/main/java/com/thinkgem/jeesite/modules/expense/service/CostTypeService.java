package com.thinkgem.jeesite.modules.expense.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.expense.dao.CostTypeDao;
import com.thinkgem.jeesite.modules.expense.entity.CostType;
import com.thinkgem.jeesite.sale.entity.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 费用分类表Service层
 *
 * @author vat
 */
@Service
@Transactional(readOnly = false)
public class CostTypeService extends CrudService<CostTypeDao, CostType> {
    @Autowired
    private CostTypeDao costTypeDao;

    public List<CostType> findCostType() {
        List<CostType> list = costTypeDao.findCostType ();
        return list;
    }

    public String findById(String costTypeId) {
        String costType = costTypeDao.findById ( costTypeId );
        return costType;
    }

    public List<Cost> selectCostDescription() {
        return dao.selectCostDescription ();
    }


}
