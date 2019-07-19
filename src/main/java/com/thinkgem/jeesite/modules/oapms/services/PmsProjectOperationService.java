package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.PmsProjectOperationDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PmsProjectOperationService extends CrudService<PmsProjectOperationDao, PmsProjectOperation> {

    @Autowired
    private PmsProjectOperationDao pmsProjectOperationDao;

    @Transactional(readOnly = false)
    public void insertOneOpertion(PmsProjectOperation pmsProjectOperation) {
        pmsProjectOperationDao.insertOneOpertion ( pmsProjectOperation );
    }

    public Page<PmsProjectOperation> findProjectOperationList(Page<PmsProjectOperation> page,
                                                              PmsProjectOperation pmsProjectOperation) {
        pmsProjectOperation.setPage ( page );
        page.setList ( pmsProjectOperationDao.findProjectOperationList ( pmsProjectOperation ) );
        return page;
    }

}
