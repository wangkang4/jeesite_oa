package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class PmsProjectExpense extends DataEntity<PmsProjectExpense> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目费用id
     */
    private String expenseId;
    /**
     * 所属项目
     */
    private PmsProject project;
    /**
     * 金额
     */
    private Double money;
    /**
     * 费用类型(市场费用、品牌、交付、收款、维护  类别可自定义)
     */
    private String status;


    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public PmsProject getProject() {
        return project;
    }

    public void setProject(PmsProject project) {
        this.project = project;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
