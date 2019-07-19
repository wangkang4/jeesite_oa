package com.thinkgem.jeesite.modules.receiptNotice.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.receiptNotice.entity.ReceiptNotice;

import java.util.List;

@MyBatisDao
public interface ReceiptNoticeDao extends CrudDao<ReceiptNotice> {
    /**
     * 查询数据信息
     *
     * @return
     */
    List<ReceiptNotice> findList();

    /**
     * 查询数据数量
     *
     * @return
     */
    Integer find();
}
