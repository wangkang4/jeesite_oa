package com.thinkgem.jeesite.modules.overtime.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 加班统计实体类；
 *
 * @author dxq
 */
public class OverTime extends ActEntity<OverTime> {

    private static final long serialVersionUID = 1L;
    private User user;     //申请用户
    private Office office; //所属部门

    private String proInsId;   //流程实例编号
    private Date startTime;    //加班开始时间
    private Date endTime;      //加班结束时间
    private String overtimeType;  //加班类型
    private Double days;       //加班天数，加班总时间；
    private String reason;     //加班标题

    private String url;//跳转路径
    private String status;//流程状态码
    private int anno;//用户所在地

    private String notes;        //加班原因
    private String prText;     //项目经理意见
    private String leaderText; //地区领导意见
    private String leadertwoText;//总经理意见
    private String hrText;     //人事主管意见

    private String leader;//上级领导


    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
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

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public String getOvertimeType() {
        return overtimeType;
    }

    public void setOvertimeType(String overtimeType) {
        this.overtimeType = overtimeType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
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

    public String getLeadertwoText() {
        return leadertwoText;
    }

    public void setLeadertwoText(String leadertwoText) {
        this.leadertwoText = leadertwoText;
    }

    public String getHrText() {
        return hrText;
    }

    public void setHrText(String hrText) {
        this.hrText = hrText;
    }

}
