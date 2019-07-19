package com.thinkgem.jeesite.modules.expense.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.expense.dao.CostDescriptionDao;
import com.thinkgem.jeesite.modules.expense.entity.CostDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 费用描述表Service层
 *
 * @author Administrator
 */
@Service
@Transactional(readOnly = false)
public class CostDescriptionService extends CrudService<CostDescriptionDao, CostDescription> {

    @Autowired
    private CostDescriptionDao costDescriptionDao;

    public List<CostDescription> findById(String costTypeId) {
        List<CostDescription> list = costDescriptionDao.findById ( costTypeId );
        return list;
    }

    public String findByOne(String costDescriptionId) {
        String costDescription = costDescriptionDao.findByOne ( costDescriptionId );
        return costDescription;
    }

    public List<CostDescription> findCostDescription() {
        List<CostDescription> list = costDescriptionDao.findCostDescription ();
        return list;
    }

    //通过返回值找返回类型
    public List<CostDescription> findByCostTypeId(String costTypeId) {
        List<CostDescription> list = costDescriptionDao.findByCostTypeId ( costTypeId );
        return list;
    }

    public void updateDescription(String detailId,
                                  String costTypeId, String costType,
                                  String costDescriptionId, String costDescription) {
        costDescriptionDao.updateDescription ( detailId, costTypeId, costType, costDescriptionId, costDescription );

    }
}
