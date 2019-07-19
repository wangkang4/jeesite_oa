package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class PmsProjectDocument extends DataEntity<PmsProjectDocument> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目文档id
     */
    private String documentId;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 所属项目
     */
    private PmsProject project;
    /**
     * 文档名称
     */
    private String documentName;
    /**
     * 上传人
     */
    private User uploadBy;
    /**
     * 上传时间
     */
    private Date uploadTime;
    /**
     * 上传地址
     */
    private String uploadAddr;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public PmsProject getProject() {
        return project;
    }

    public void setProject(PmsProject project) {
        this.project = project;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public User getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(User uploadBy) {
        this.uploadBy = uploadBy;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadAddr() {
        return uploadAddr;
    }

    public void setUploadAddr(String uploadAddr) {
        this.uploadAddr = uploadAddr;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


}
