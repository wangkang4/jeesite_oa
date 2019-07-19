package com.thinkgem.jeesite.modules.invoice.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class InvoiceApply extends ActEntity<InvoiceApply> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private User user;//用户信息
    private String a;

    /*客户信息*/
    private String id;//表ID
    private String taxName;//开票名称
    private String taxNumber;//纳税人识别号
    private String address;//地址
    private String phone;//电话
    private String bank;//开户银行
    private String account;//账号
    private Double total;//合计
    private String maAddress;//邮寄地址
    private String colPerson;//收件人
    private String colPhone;//收件人电话
    //private String detId;//明细标识ID

    /*审核信息*/
    private User manage;//项目经理
    private User offPerson;//办事处负责人
    private String oneText;//商务部部长

    private String proneText;//财务总监
    private String prtwoText;//开票人

    private String report;//验收报告
    private String statu;//审核状态

    private String applyAddress;


    private String proname;//项目名称
    private String invoiceStatu;//是否收到采购情况
    private String alMoney;//已开票金额
    private String nowMoney;//本次开票金额
    private String invoiceDate;//开票日期

    private String invoiceInfo;


    public String getOneText() {
        return oneText;
    }

    public void setOneText(String oneText) {
        this.oneText = oneText;
    }


    public String getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(String invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }


    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }


    public User getManage() {
        return manage;
    }

    public void setManage(User manage) {
        this.manage = manage;
    }

    public User getOffPerson() {
        return offPerson;
    }

    public void setOffPerson(User offPerson) {
        this.offPerson = offPerson;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getMaAddress() {
        return maAddress;
    }

    public void setMaAddress(String maAddress) {
        this.maAddress = maAddress;
    }

    public String getColPerson() {
        return colPerson;
    }

    public void setColPerson(String colPerson) {
        this.colPerson = colPerson;
    }

    public String getColPhone() {
        return colPhone;
    }

    public void setColPhone(String colPhone) {
        this.colPhone = colPhone;
    }

    public String getProneText() {
        return proneText;
    }

    public void setProneText(String proneText) {
        this.proneText = proneText;
    }

    public String getPrtwoText() {
        return prtwoText;
    }

    public void setPrtwoText(String prtwoText) {
        this.prtwoText = prtwoText;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getInvoiceStatu() {
        return invoiceStatu;
    }

    public void setInvoiceStatu(String invoiceStatu) {
        this.invoiceStatu = invoiceStatu;
    }

    public String getAlMoney() {
        return alMoney;
    }

    public void setAlMoney(String alMoney) {
        this.alMoney = alMoney;
    }

    public String getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(String nowMoney) {
        this.nowMoney = nowMoney;
    }


}
