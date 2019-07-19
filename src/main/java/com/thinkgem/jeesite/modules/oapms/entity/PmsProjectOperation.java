package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class PmsProjectOperation extends DataEntity<PmsProjectOperation> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目动态id
     */
    private String operationId;
    /**
     * 所属项目
     */
    private PmsProject project;
    /**
     * 操作人
     */
    private User operationBy;
    /**
     * 操作时间
     */
    private Date operationTime;
    /**
     * 操作内容
     */
    private String content;


    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public PmsProject getProject() {
        return project;
    }

    public void setProject(PmsProject project) {
        this.project = project;
    }

    public User getOperationBy() {
        return operationBy;
    }

    public void setOperationBy(User operationBy) {
        this.operationBy = operationBy;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
