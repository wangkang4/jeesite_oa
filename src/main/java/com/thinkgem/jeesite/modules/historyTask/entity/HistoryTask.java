package com.thinkgem.jeesite.modules.historyTask.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;

import java.util.Date;


public class HistoryTask extends ActEntity<HistoryTask> {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*private String id;*/
    /**
     *
     */
    private String userName;

    /**
     * 当前人登录名
     */
    private String loginName;

    /**
     * 标题详情字段（展现在页面标题栏）
     */
    private String reason;

    /**
     * 审批任务的结束时间
     */
    private Date cdDate;

    /**
     * 流程实例ID
     */
    private String procId;

    /**
     * 流程标识ID（如 报销流程或者其他流程等）
     */
    private String prdId;

    /**
     * 记录数目
     */
    private String count;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 历史流程任务ID
     */
    private String taskId;

    /**
     * 历史流程任务定义Key
     */
    private String taskDefKey;

    /**
     * 标题字段
     */
    private String title;

    /**
     * 流程名
     */
    private String prdName;

    /**
     * 从前台获取的流程查询名
     *
     * @return
     */
    private String preceName;

	/*public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/


    public String getReason() {
        return reason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCdDate() {
        return cdDate;
    }

    public void setCdDate(Date cdDate) {
        this.cdDate = cdDate;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getPreceName() {
        return preceName;
    }

    public void setPreceName(String preceName) {
        this.preceName = preceName;
    }


}
