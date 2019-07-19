package com.thinkgem.jeesite.modules.costList.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 费用统计总表
 *
 * @author shegchanghao
 */
public class CostList extends DataEntity<CostList> {

    private static final long serialVersionUID = 1L;

    /**
     * 费用统计总表id
     */
    private Integer costId;
    /**
     * 费用统计总表名字
     */
    private String costName;

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
