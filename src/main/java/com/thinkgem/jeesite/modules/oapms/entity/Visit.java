package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * @ClassName: Visit
 * @Description: TODO(拜访记录实体类)
 * @author: WangFucheng
 * @date 2018年1月28日 上午11:04:16
 */
public class Visit extends DataEntity<Visit> {
    private static final long serialVersionUID = 1L;
    /**
     * 拜访Id
     */
    private String visitId;
    /**
     * 标题
     */
    private String title;
    /**
     * 拜访时间
     */
    private Date visitTime;
    /**
     * 纪要
     */
    private String visitSummary;
    /**
     * 下一步计划
     */
    private String nextPlan;
    /**
     * 客户
     */
    private Customer customer;
    /**
     * 客户联系人
     */
    private CustomerContact customerContact;
    /**
     * 拜访地址
     */
    private String visitAddress;

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitSummary() {
        return visitSummary;
    }

    public void setVisitSummary(String visitSummary) {
        this.visitSummary = visitSummary;
    }

    public String getNextPlan() {
        return nextPlan;
    }

    public void setNextPlan(String nextPlan) {
        this.nextPlan = nextPlan;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }

    public String getVisitAddress() {
        return visitAddress;
    }

    public void setVisitAddress(String visitAddress) {
        this.visitAddress = visitAddress;
    }


}
