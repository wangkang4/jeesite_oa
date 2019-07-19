/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 单表生成Entity
 *
 * @author zhangbingbing
 * @version 2018-03-14
 */
public class PmTaskMain extends DataEntity<PmTaskMain> {

    private static final long serialVersionUID = 1L;
    private String projectId;        // 项目id
    private String tashkTheme;        // 任务主题
    private String customerId;        // 客户名称
    private String taskType;        // 任务类型:1.关系维护；2.需求调研；3.项目启动；4.需求调研；5.项目展示；6.问题收集；7.风险讨论
    private String projectStage;        // 项目阶段:1.启动；2.开发；3.试运行；4.维护
    private Date startTime;        // 开始执行时间
    private Date endTime;        // 结束执行时间
    private String taskContent;        // 任务内容
    private String priority;        // 优先级:1.紧急；2.高；3.中；4.低
    private String appendixId;        // 附件表id

    private User responsibility;    //责任人
    private User copy;                //抄送人

    private String taskId;            //任务id
    private String userId;            //用户id
    private String peoplePosition;    //人员类型
    private String userName;        //用户名

    private String projectName;        //项目名称
    private String customerName;    //客户名称


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPeoplePosition() {
        return peoplePosition;
    }

    public void setPeoplePosition(String peoplePosition) {
        this.peoplePosition = peoplePosition;
    }

    public User getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(User responsibility) {
        this.responsibility = responsibility;
    }

    public User getCopy() {
        return copy;
    }

    public void setCopy(User copy) {
        this.copy = copy;
    }

    public PmTaskMain() {
        super ();
    }

    public PmTaskMain(String id) {
        super ( id );
    }


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Length(min = 1, max = 200, message = "任务主题长度必须介于 1 和 200 之间")
    public String getTashkTheme() {
        return tashkTheme;
    }

    public void setTashkTheme(String tashkTheme) {
        this.tashkTheme = tashkTheme;
    }

    @Length(min = 0, max = 64, message = "客户名称长度必须介于 0 和 64 之间")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Length(min = 0, max = 2000, message = "任务内容长度必须介于 0 和 2000 之间")
    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAppendixId() {
        return appendixId;
    }

    public void setAppendixId(String appendixId) {
        this.appendixId = appendixId;
    }

}