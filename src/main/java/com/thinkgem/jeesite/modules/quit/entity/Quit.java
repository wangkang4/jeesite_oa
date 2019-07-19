package com.thinkgem.jeesite.modules.quit.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class Quit extends ActEntity<Quit> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;//id
    private User user;//用户信息
    private Office office;//用户部门信息

    private Date applyDate;//申请离职日期
    private Date quitDate;//拟离职日期
    private String position;//职位
    private String reason;//离职原因
    private String address;//文件地址
    private String statu;//审核状态

    /**
     * 主管意见
     */
    private String proneText;
    /**
     * 行政意见
     */
    private String prtwoText;
    /**
     * 人事经理
     */
    private String prthreeText;
    /**
     * 财务主管
     */
    private String prfourText;
    /**
     * 财务总监
     */
    private String prfiveText;
    /**
     * 总裁
     */
    private String prsixText;


    public String getPrsixText() {
        return prsixText;
    }

    public void setPrsixText(String prsixText) {
        this.prsixText = prsixText;
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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getQuitDate() {
        return quitDate;
    }

    public void setQuitDate(Date quitDate) {
        this.quitDate = quitDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }


}
