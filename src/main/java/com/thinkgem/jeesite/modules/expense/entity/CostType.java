package com.thinkgem.jeesite.modules.expense.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 费用类型表实体类
 *
 * @author vat
 */
public class CostType extends DataEntity<CostType> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 费用类型id
     */
    private String costTypeId;
    /**
     * 费用类型名字
     */
    private String costType;

    public String getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(String costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
