package com.thinkgem.jeesite.modules.mobile.dailyrecord.entity;

import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;

import java.util.List;


/**
 * OA系统周报表对应实体类
 *
 * @author tanchaoyang
 * @version 2017-8-2
 */
public class WeeklyRequest {

    private String dayContent;        //本周内容
    private String planContent;        //下周计划
    private List<FileUploadResult> filelist;  //上传的附件
    private String sendName;//收件人名
    private String remark;

    public List<FileUploadResult> getFilelist() {
        return filelist;
    }

    public void setFilelist(List<FileUploadResult> filelist) {
        this.filelist = filelist;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getDayContent() {
        return dayContent;
    }

    public void setDayContent(String dayContent) {
        this.dayContent = dayContent;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
