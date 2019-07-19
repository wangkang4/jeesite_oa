package com.thinkgem.jeesite.modules.leave.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class ActivityLeave2 extends ActEntity<ActivityLeave2> {

    private static final long serialVersionUID = 1L;

    private User user;     //申请用户
    private Office office; //所属部门

    private int a;
    private String status;//流程状态
    private String proInsId;   //流程实例编号
    private Date startTime;    //请假开始时间
    private Date endTime;      //请假结束时间
    private String leaveType;  //请假类型
    private Double days;       //请假天数
    private double removeDays; //消除的请假天数
    private String reason;     //请假标题
    private String notes;        //请假原因

    private String prText;     //项目经理意见
    private String leaderText; //地区领导意见
    private String hrText;     //人事主管意见
    private String leadertwoText;
    private String leaderthreeText;

    private String attachName;//存放的文件名
    private String attachAddress;//存放的文件路径

    private String url;//当前路径


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeadertwoText() {
        return leadertwoText;
    }

    public void setLeadertwoText(String leadertwoText) {
        this.leadertwoText = leadertwoText;
    }

    public String getLeaderthreeText() {
        return leaderthreeText;
    }

    public void setLeaderthreeText(String leaderthreeText) {
        this.leaderthreeText = leaderthreeText;
    }

    public double getRemoveDays() {
        return removeDays;
    }

    public void setRemoveDays(double removeDays) {
        this.removeDays = removeDays;
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

    public String getProInsId() {
        return proInsId;
    }

    public void setProInsId(String proInsId) {
        this.proInsId = proInsId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrText() {
        return prText;
    }

    public void setPrText(String prText) {
        this.prText = prText;
    }

    public String getLeaderText() {
        return leaderText;
    }

    public void setLeaderText(String leaderText) {
        this.leaderText = leaderText;
    }

    public String getHrText() {
        return hrText;
    }

    public void setHrText(String hrText) {
        this.hrText = hrText;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachAddress() {
        return attachAddress;
    }

    public void setAttachAddress(String attachAddress) {
        this.attachAddress = attachAddress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
