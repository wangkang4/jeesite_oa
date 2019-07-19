package com.thinkgem.jeesite.modules.costList.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.costList.dao.CostDetailsDao;
import com.thinkgem.jeesite.modules.costList.entity.CostDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 费用统计详情表Service层
 *
 * @author shengchanghao
 */
@Service
@Transactional(readOnly = false)
public class CostDetailsService extends CrudService<CostDetailsDao, CostDetails> {
    @Autowired
    private CostDetailsDao costDetailsDao;

    public List<CostDetails> findById(Integer costId) {
        List<CostDetails> costDetails = costDetailsDao.findById ( costId );
        return costDetails;
    }

}
