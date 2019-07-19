package com.thinkgem.jeesite.modules.expenseStatistical.entity;

public class TypeAndDescription {
    /**
     * 费用类型
     */
    private String costType;
    /**
     * 费用描述
     */
    private String costDescription;

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostDescription() {
        return costDescription;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    @Override
    public String toString() {
        return "TypeAndDescription [costType=" + costType + ", costDescription=" + costDescription + "]";
    }

}
