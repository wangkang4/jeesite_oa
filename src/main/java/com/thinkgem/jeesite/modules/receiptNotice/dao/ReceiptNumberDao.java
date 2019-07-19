package com.thinkgem.jeesite.modules.receiptNotice.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.receiptNotice.entity.ReceiptNumber;

@MyBatisDao
public interface ReceiptNumberDao extends CrudDao<ReceiptNumber> {

    void insertCount(ReceiptNumber rn);

    /**
     * 查询数据
     *
     * @param rn
     * @return
     */
    Integer find2(ReceiptNumber rn);
}
