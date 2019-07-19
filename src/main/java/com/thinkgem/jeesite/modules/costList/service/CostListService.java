package com.thinkgem.jeesite.modules.costList.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.costList.dao.CostListDao;
import com.thinkgem.jeesite.modules.costList.entity.CostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 统计总表的Service层
 *
 * @author shengchanghao
 */
@Service
@Transactional(readOnly = false)
public class CostListService extends CrudService<CostListDao, CostList> {

    @Autowired
    private CostListDao costListDao;


    public List<CostList> findCostList() {
        List<CostList> list = costListDao.findCostList ();
        return list;
    }


}
