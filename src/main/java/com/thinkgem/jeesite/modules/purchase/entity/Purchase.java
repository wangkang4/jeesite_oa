package com.thinkgem.jeesite.modules.purchase.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 采购申请流程
 *
 * @author dongxueyong
 */
public class Purchase extends ActEntity<Purchase> {

    /**
     * 采购申请
     */
    private static final long serialVersionUID = 1L;

    private String id;//业务表ID
    private User user; //用户信息
    private Office office;//部门信息
    private String pName;//物资名称
    private Double money;//合计金额

    private Date applyDate;//申请时间
    private Date purchaseDate;//采购时间
    private String applyAddress;//文件地址
    private String report;//备注

    private String title;//采购标题
    private String proneText;//研发总监
    private String prtwoText;//办事处意见
    private String prthreeText;//财务经理意见
    private String prfourText;//总经理
    private String prfiveText;//行政人员
    private String statu;//审核状态

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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPrthreeText() {
        return prthreeText;
    }

    public void setPrthreeText(String prthreeText) {
        this.prthreeText = prthreeText;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getPrfourText() {
        return prfourText;
    }

    public void setPrfourText(String prfourText) {
        this.prfourText = prfourText;
    }

    public String getPrfiveText() {
        return prfiveText;
    }

    public void setPrfiveText(String prfiveText) {
        this.prfiveText = prfiveText;
    }


}
