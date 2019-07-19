package com.thinkgem.jeesite.modules.receiptNotice.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.receiptNotice.dao.ReceiptNoticeDao;
import com.thinkgem.jeesite.modules.receiptNotice.entity.ReceiptNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReceiptNoticeService extends CrudService<ReceiptNoticeDao, ReceiptNotice> {

    /**
     * 注入receiptNoticeDao属性
     */
    @Autowired
    private ReceiptNoticeDao receiptNoticeDao;

    /**
     * 查询提交人列表页
     *
     * @param page
     * @param receiptNotice
     * @return
     */
    @Transactional(readOnly = false)
    public Page<ReceiptNotice> findPages(Page<ReceiptNotice> page, ReceiptNotice receiptNotice) {
        receiptNotice.setPage ( page );
        page.setList ( receiptNoticeDao.findList ( receiptNotice ) );
        return page;
    }

    /**
     * 查询员工列表页
     *
     * @param page
     * @param receiptNotice
     * @return
     */
    @Transactional(readOnly = false)
    public Page<ReceiptNotice> findPages2(Page<ReceiptNotice> page, ReceiptNotice receiptNotice) {
        receiptNotice.setPage ( page );
        page.setList ( receiptNoticeDao.findList () );
        return page;
    }

    /**
     * 查询数据条数
     *
     * @return 数据库数据数目
     */
    public Integer find() {

        return receiptNoticeDao.find ();
    }

}
