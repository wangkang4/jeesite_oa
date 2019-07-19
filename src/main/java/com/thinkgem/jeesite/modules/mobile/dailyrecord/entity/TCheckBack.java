package com.thinkgem.jeesite.modules.mobile.dailyrecord.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
import java.util.List;

public class TCheckBack extends DataEntity<TCheckBack> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Date dayTime;
    private String types;
    private String profId;
    private String dayContent;
    private String weekContent;
    private Integer performance;
    private String planContent;
    private String weekplanContent;
    private String weekRemark;
    private String dayRemark;
    private User user;
    private Date createrTime;
    private List<FileUploadResult> fileList;

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String getDayContent() {
        return dayContent;
    }

    public void setDayContent(String dayContent) {
        this.dayContent = dayContent;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public Date getDayTime() {
        return dayTime;
    }

    public void setDayTime(Date dayTime) {
        this.dayTime = dayTime;
    }

    public String getWeekContent() {
        return weekContent;
    }

    public void setWeekContent(String weekContent) {
        this.weekContent = weekContent;
    }

    public String getWeekplanContent() {
        return weekplanContent;
    }

    public void setWeekplanContent(String weekplanContent) {
        this.weekplanContent = weekplanContent;
    }

    public List<FileUploadResult> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileUploadResult> fileList) {
        this.fileList = fileList;
    }

    public String getWeekRemark() {
        return weekRemark;
    }

    public void setWeekRemark(String weekRemark) {
        this.weekRemark = weekRemark;
    }

    public String getDayRemark() {
        return dayRemark;
    }

    public void setDayRemark(String dayRemark) {
        this.dayRemark = dayRemark;
    }


}
