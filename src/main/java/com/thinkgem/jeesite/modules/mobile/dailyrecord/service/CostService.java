package com.thinkgem.jeesite.modules.mobile.dailyrecord.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcel;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.dao.CostDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class CostService extends CrudService<CostDao, CostRecondExcel> {

    @Autowired
    private CostDao costDao;

    public List<CostRecondExcel> findAllCost(String id, int pageSize, Integer offset) {
        CostRecondExcel costRecondExcel = new CostRecondExcel ();
        User user = costDao.getUser ( id );
        if (!user.getOffice ().getName ().equals ( "公司领导" )) {
            costRecondExcel.setCreaterBy ( user );
        }
        Page<CostRecondExcel> page = new Page<CostRecondExcel> ();
        page.setPageSize ( pageSize );
        page.setOffset ( offset );
        page.setMobilePage ( true );

        costRecondExcel.setPage ( page );

        return costDao.findAllCost ( costRecondExcel );
    }

    public CostRecondExcel getone(String id) {
        return costDao.getone ( id );
    }

}
