package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class PmsProjectHelp extends DataEntity<PmsProjectHelp> {

    private static final long serialVersionUID = 1L;

    /**
     * 求助id
     */
    private String helpId;
    /**
     * 所属项目
     */
    private PmsProject project;
    /**
     * 项目内容
     */
    private String content;
    /**
     * 协助者
     */
    private User helper;
    /**
     * 被协助者
     */
    private User helpBy;
    /**
     * 状态(解决中,已解决、未解决)
     */
    private String status;
    /**
     * 请求时间
     */
    private Date helpTime;

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
    }

    public PmsProject getProject() {
        return project;
    }

    public void setProject(PmsProject project) {
        this.project = project;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getHelper() {
        return helper;
    }

    public void setHelper(User helper) {
        this.helper = helper;
    }

    public User getHelpBy() {
        return helpBy;
    }

    public void setHelpBy(User helpBy) {
        this.helpBy = helpBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getHelpTime() {
        return helpTime;
    }

    public void setHelpTime(Date helpTime) {
        this.helpTime = helpTime;
    }


}
