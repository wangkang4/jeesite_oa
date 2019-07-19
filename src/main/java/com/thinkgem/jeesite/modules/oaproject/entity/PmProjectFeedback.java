/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 单表生成Entity
 *
 * @author zhangbingbing
 * @version 2018-03-14
 */
public class PmProjectFeedback extends DataEntity<PmProjectFeedback> {

    private static final long serialVersionUID = 1L;
    private Date feedbackTime;        // feedback_time
    private String progress;        // progress
    private String taskId;        // task_id

    private String taskName;    //任务名称

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public PmProjectFeedback() {
        super ();
    }

    public PmProjectFeedback(String id) {
        super ( id );
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    @Length(min = 0, max = 2000, message = "progress长度必须介于 0 和 2000 之间")
    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Length(min = 0, max = 64, message = "task_id长度必须介于 0 和 64 之间")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}