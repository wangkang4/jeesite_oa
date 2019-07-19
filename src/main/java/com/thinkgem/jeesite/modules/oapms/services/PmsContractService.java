package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.PmsContractDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PmsContractService extends CrudService<PmsContractDao, PmsContract> {

    @Autowired
    private PmsContractDao pmsContractDao;

    public PmsContract get(String id) {
        return super.get ( id );
    }

    public Page<PmsContract> findPage(Page<PmsContract> page, PmsContract pmsContract) {
        pmsContract.setPage ( page );
        page.setList ( pmsContractDao.findList ( pmsContract ) );
        return page;
    }

    @Transactional(readOnly = false)
    public void save(PmsContract pmsContract) {
        super.save ( pmsContract );
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        pmsContractDao.deletePms ( id );
    }


    public PmsContract selectById(String id) {
        return pmsContractDao.selectById ( id );
    }

    //更新某一条数据；
    @Transactional(readOnly = false)
    public void update(PmsContract pmsContract) {
        pmsContractDao.update ( pmsContract );
    }

    public String selectFileNameById(String attachmentAddress) {
        return pmsContractDao.selectFileNameById ( attachmentAddress );

    }

}
