package com.thinkgem.jeesite.modules.tb.pay.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;

import java.util.Date;

public class Pay extends ActEntity<Pay> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 合同名称
     */
    private String projectName;
    /**
     * 合同编号
     */
    private String projectNum;

    /**
     * 合同总金额
     */
    private Double money;
    /**
     * 合同签订时间
     */
    private Date projectDate;
    /**
     * 本次应付金额
     */
    private Double payMoney;
    /**
     * 最晚付款时间
     */
    private Date lastTime;
    /**
     * 应付款情况说明
     */
    private String notes;
    /**
     * 审核状态
     */
    private String statu;
    /**
     * 标题
     */
    private String reason;
    /**
     * 付款方式
     */
    private int payMethods;
    /**
     * 付款类别大
     */
    private String payTypeBig;
    /**
     * 付款类别小
     */
    private String payTypeSmall;
    /**
     * 已付款金额
     */
    private String amountPaid;
    /**
     * 收款名称
     */
    private String payeeName;
    /**
     * 账户
     */
    private String payeeAccount;
    /**
     * 提交附件
     */
    private String applyAddress;
    /**
     * 完成后附件
     */
    private String invoicAddress;
    /**
     * 关联合同Id
     */
    private String contractId;
    /**
     * 筛选条件开始时间
     */
    private Date st;
    /**
     * 筛选条件结束时间
     */
    private Date et;
    /**
     * 公司名称
     */
    private String ename;


    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Date getSt() {
        return st;
    }

    public Date getEt() {
        return et;
    }

    public void setSt(Date st) {
        this.st = st;
    }

    public void setEt(Date et) {
        this.et = et;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    /**
     * 部门经理审核意见
     */
    private String proneText;
    /**
     * 研发总监审核意见
     */
    private String prtwoText;
    /**
     * 总管审核意见
     */
    private String prthreeText;
    /**
     * 财务总监审核意见
     */
    private String prfourText;
    /**
     * 总经理审核意见
     */
    private String prfiveText;
    /**
     * 商务主管审核意见
     */
    private String prsixText;
    /**
     * 出纳审核意见
     */
    private String prsevText;

    public String getReason() {
        return reason;
    }

    public String getProneText() {
        return proneText;
    }

    public String getPrtwoText() {
        return prtwoText;
    }

    public String getPrthreeText() {
        return prthreeText;
    }

    public String getPrfourText() {
        return prfourText;
    }

    public String getPrfiveText() {
        return prfiveText;
    }

    public void setProneText(String proneText) {
        this.proneText = proneText;
    }

    public void setPrtwoText(String prtwoText) {
        this.prtwoText = prtwoText;
    }

    public void setPrthreeText(String prthreeText) {
        this.prthreeText = prthreeText;
    }

    public void setPrfourText(String prfourText) {
        this.prfourText = prfourText;
    }

    public void setPrfiveText(String prfiveText) {
        this.prfiveText = prfiveText;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public Double getMoney() {
        return money;
    }

    public Date getProjectDate() {
        return projectDate;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPayMethods() {
        return payMethods;
    }

    public String getPayTypeBig() {
        return payTypeBig;
    }

    public String getPayTypeSmall() {
        return payTypeSmall;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayMethods(int payMethods) {
        this.payMethods = payMethods;
    }

    public void setPayTypeBig(String payTypeBig) {
        this.payTypeBig = payTypeBig;
    }

    public void setPayTypeSmall(String payTypeSmall) {
        this.payTypeSmall = payTypeSmall;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public String getInvoicAddress() {
        return invoicAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public void setInvoicAddress(String invoicAddress) {
        this.invoicAddress = invoicAddress;
    }

    public String getPrsixText() {
        return prsixText;
    }

    public void setPrsixText(String prsixText) {
        this.prsixText = prsixText;
    }

    public String getPrsevText() {
        return prsevText;
    }

    public void setPrsevText(String prsevText) {
        this.prsevText = prsevText;
    }


}
