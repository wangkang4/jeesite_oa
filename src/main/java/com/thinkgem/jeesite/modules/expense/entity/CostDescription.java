package com.thinkgem.jeesite.modules.expense.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 费用描述表实体类
 *
 * @author vat
 */
public class CostDescription extends DataEntity<CostDescription> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 费用描述表id
     */
    private String costDescriptionId;
    /**
     * 费用描述表内容
     */
    private String costDescription;
    /**
     * 费用类型表id
     */
    private String costTypeId;

    public String getCostDescriptionId() {
        return costDescriptionId;
    }

    public void setCostDescriptionId(String costDescriptionId) {
        this.costDescriptionId = costDescriptionId;
    }

    public String getCostDescription() {
        return costDescription;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    public String getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(String costTypeId) {
        this.costTypeId = costTypeId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
