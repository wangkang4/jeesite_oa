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
public class PmProjectDetailed extends DataEntity<PmProjectDetailed> {

    private static final long serialVersionUID = 1L;
    private String projectId;        // 项目id
    private String projectClass;        // 项目分类：1.一期；2.二期；3.三期
    private String assistDepartment;        // 协助部门
    private String projectSource;        // 项目来源
    private String projectLevel;        // 项目级别：1.一级；2.二级；3.三级
    private String projectImportance;        // 项目重要性：1.极高；2.高；3.中；4.低；5.极低
    private String projectUrgent;        // 紧急程度：1.一般；2.非常；3.紧急
    private Date startTime;        // 预计开始时间
    private Date endTime;        // 预计结束时间
    private String customerName;        // 客户名称
    private String projectBackground;        // 项目背景
    private String projectRange;        // 项目范围
    private String projectTarget;        // 项目目标
    private String projectConstraint;        // 假设与约束

    private User customer;            //客户

    private PmProjectRelation projectRelation;                //项目与项目关联
    private PmProjectCustomer projectCustomer;                //项目与客户关联
    private PmProjectOpponent projectOpponent;                //项目与竞争对手关联
    private PmProjectCooperation projectCooperation;        //项目与合作单位关联

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public PmProjectRelation getProjectRelation() {
        return projectRelation;
    }

    public void setProjectRelation(PmProjectRelation projectRelation) {
        this.projectRelation = projectRelation;
    }

    public PmProjectCustomer getProjectCustomer() {
        return projectCustomer;
    }

    public void setProjectCustomer(PmProjectCustomer projectCustomer) {
        this.projectCustomer = projectCustomer;
    }

    public PmProjectOpponent getProjectOpponent() {
        return projectOpponent;
    }

    public void setProjectOpponent(PmProjectOpponent projectOpponent) {
        this.projectOpponent = projectOpponent;
    }

    public PmProjectCooperation getProjectCooperation() {
        return projectCooperation;
    }

    public void setProjectCooperation(PmProjectCooperation projectCooperation) {
        this.projectCooperation = projectCooperation;
    }

    public PmProjectDetailed() {
        super ();
    }

    public PmProjectDetailed(String id) {
        super ( id );
    }

    @Length(min = 1, max = 64, message = "项目id长度必须介于 1 和 64 之间")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectClass() {
        return projectClass;
    }

    public void setProjectClass(String projectClass) {
        this.projectClass = projectClass;
    }

    @Length(min = 0, max = 64, message = "协助部门长度必须介于 0 和 64 之间")
    public String getAssistDepartment() {
        return assistDepartment;
    }

    public void setAssistDepartment(String assistDepartment) {
        this.assistDepartment = assistDepartment;
    }

    @Length(min = 0, max = 64, message = "项目来源长度必须介于 0 和 64 之间")
    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    public String getProjectImportance() {
        return projectImportance;
    }

    public void setProjectImportance(String projectImportance) {
        this.projectImportance = projectImportance;
    }

    public String getProjectUrgent() {
        return projectUrgent;
    }

    public void setProjectUrgent(String projectUrgent) {
        this.projectUrgent = projectUrgent;
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

    @Length(min = 0, max = 200, message = "客户名称长度必须介于 0 和 200 之间")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectBackground() {
        return projectBackground;
    }

    public void setProjectBackground(String projectBackground) {
        this.projectBackground = projectBackground;
    }

    @Length(min = 0, max = 2000, message = "项目范围长度必须介于 0 和 2000 之间")
    public String getProjectRange() {
        return projectRange;
    }

    public void setProjectRange(String projectRange) {
        this.projectRange = projectRange;
    }

    @Length(min = 0, max = 2000, message = "项目目标长度必须介于 0 和 2000 之间")
    public String getProjectTarget() {
        return projectTarget;
    }

    public void setProjectTarget(String projectTarget) {
        this.projectTarget = projectTarget;
    }

    @Length(min = 0, max = 2000, message = "假设与约束长度必须介于 0 和 2000 之间")
    public String getProjectConstraint() {
        return projectConstraint;
    }

    public void setProjectConstraint(String projectConstraint) {
        this.projectConstraint = projectConstraint;
    }

}