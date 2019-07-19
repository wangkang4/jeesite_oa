package com.thinkgem.jeesite.modules.costList.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 报销账单表
 *
 * @author shengchanghao
 */
public class Account extends DataEntity<Account> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    private Date time;

    /**
     * 收据金额
     */
    private String money;

    /**
     * 费用描述
     */
    private String costDescription;
    /**
     * 可报销金额
     */
    private String amount;
    /**
     * 详细信息
     */
    private String information;
    /**
     * 部门id
     */
    private Integer deptId;
    /**
     * 部门name
     */
    private String deptName;
    /**
     * 统计总表id
     */
    private Integer costId;
    /**
     * 统计总表name
     */
    private String costName;
    /**
     * 统计详情表id
     */
    private Integer detailsId;
    /**
     * 统计详情表name
     */
    private String detailsName;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public String getDetailsName() {
        return detailsName;
    }

    public void setDetailsName(String detailsName) {
        this.detailsName = detailsName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCostDescription() {
        return costDescription;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
