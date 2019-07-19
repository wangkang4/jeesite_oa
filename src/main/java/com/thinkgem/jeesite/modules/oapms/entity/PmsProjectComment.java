package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class PmsProjectComment extends DataEntity<PmsProjectComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private String commentId;
    /**
     * 所属项目
     */
    private PmsProject project;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论人
     */
    private User commentBy;
    /**
     * 评论时间
     */
    private Date commentTime;


    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    public User getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(User commentBy) {
        this.commentBy = commentBy;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
