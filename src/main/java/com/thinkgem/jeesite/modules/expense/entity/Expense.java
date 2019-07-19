package com.thinkgem.jeesite.modules.expense.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 报销表实体类
 *
 * @author vat
 */
public class Expense extends DataEntity<Expense> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 报销表id
     */
    private String expenseId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 部门
     */
    private String dept;
    /**
     * 报销流程模版关联字段
     */
    private String proess;
    /**
     * 报销表备注
     */
    private String message;

    private ExpenseDetail expenseDetail;


    public ExpenseDetail getExpenseDetail() {
        return expenseDetail;
    }

    public void setExpenseDetail(ExpenseDetail expenseDetail) {
        this.expenseDetail = expenseDetail;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getProess() {
        return proess;
    }

    public void setProess(String proess) {
        this.proess = proess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
