package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

public class PmsPayment extends DataEntity<PmsPayment> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String paymentId;  //回款计划id
    private String contractId;  //合同管理Id;
    private Date paymentTime;  //回款时间
    private String paymentAmount;  //回款金额
    private String paymentMode;    //回款方式；
    private String paymentStatus;   //回款状态；


    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }


}
