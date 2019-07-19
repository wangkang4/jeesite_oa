package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.PmsPaymentDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PmsPaymentService extends CrudService<PmsPaymentDao, PmsPayment> {

    @Autowired
    private PmsPaymentDao pmsPaymentDao;

    public Page<PmsPayment> findPage(Page<PmsPayment> page, PmsPayment pmsPayment) {
        pmsPayment.setPage ( page );
        page.setList ( pmsPaymentDao.findList ( pmsPayment ) );
        return page;
    }

    @Transactional(readOnly = false)
    public void save(PmsPayment pmsPayment) {
        super.save ( pmsPayment );
    }

    public PmsPayment selectById(String id) {
        return pmsPaymentDao.selectById ( id );
    }

    // 更新某一条数据；
    @Transactional(readOnly = false)
    public void update(PmsPayment pmsPayment) {
        pmsPaymentDao.update ( pmsPayment );
    }

    //删除某一条数据；
    @Transactional(readOnly = false)
    public void delete(String paymentId) {
        pmsPaymentDao.deletePms ( paymentId );

    }

}
