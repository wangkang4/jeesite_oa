package com.thinkgem.jeesite.modules.receiptNotice.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class ReceiptNotice extends ActEntity<ReceiptNotice> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    /*用户信息*/
    private User user;
    private Office office;
    /*收款通知信息*/
    private String receiptDate;//收款日期
    private String paymentName;//付款方名称
    private String receiptName;//收款项目
    private String contractNumber;//对应合同编号
    private String totalMoney;//总金额
    private String alreadyMoney;//已经收款金额
    private String nowMoney;//本次收款金额
    private String receiptNature;//收款性质
    private String times;//属第几次收款

    /*付款方账户信息*/
    private String pbank;//开户行
    private String pname;//账户名
    private String pnumber;//账号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAlreadyMoney() {
        return alreadyMoney;
    }

    public void setAlreadyMoney(String alreadyMoney) {
        this.alreadyMoney = alreadyMoney;
    }

    public String getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(String nowMoney) {
        this.nowMoney = nowMoney;
    }

    public String getReceiptNature() {
        return receiptNature;
    }

    public void setReceiptNature(String receiptNature) {
        this.receiptNature = receiptNature;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPbank() {
        return pbank;
    }

    public void setPbank(String pbank) {
        this.pbank = pbank;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

}
