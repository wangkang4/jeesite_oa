package com.thinkgem.jeesite.modules.receiptNotice.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 每一个通知到的指定人员计数实体类
 *
 * @author dongxueyong
 */
public class ReceiptNumber extends DataEntity<ReceiptNumber> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 通知到的人的id
     */
    private String id;
    /**
     * 对应的数据库数量
     */
    private Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
