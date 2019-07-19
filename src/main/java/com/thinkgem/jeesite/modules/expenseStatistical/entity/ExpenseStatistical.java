package com.thinkgem.jeesite.modules.expenseStatistical.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class ExpenseStatistical {
    /**
     * 费用类型
     */
    private String costType;
    /**
     * 报销金额
     */
    private double money;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 用户
     */
    private User user;
    /**
     * 部门
     */
    private Office office;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ExpenseStatistical [costType=" + costType + ", money=" + money + ", startTime=" + startTime
                + ", endTime=" + endTime + ", user=" + user + ", office=" + office + "]";
    }


}
