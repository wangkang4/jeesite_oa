package com.thinkgem.jeesite.modules.daily.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.weekly.entity.Weekly;

import java.util.Date;

/**
 * 日报周报中间表实体类
 *
 * @author ShiLiangYu
 * @version 2017-8-2
 */
public class TCheckBacklog extends DataEntity<TCheckBacklog> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer types; // 业务类型 1:日报 ，2:周报
    private String profId; // 业务标识
    private User user; // user.id用户标识
    private String createrId; // 发布者
    private Date createrTime; // 发布时间

    // daily伪列
    private String successState;// 完成情况
    private String sendName;// 发送人
    private String dayContent;
    private String planContent;
    private String remark;
    // weekly伪列
    private Weekly weekly;

    public TCheckBacklog() {
        super ();
        // TODO Auto-generated constructor stub
    }

    public TCheckBacklog(String id) {
        super ( id );
        // TODO Auto-generated constructor stub
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    @ExcelField(title = "发送时间", sort = 20)
    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    @ExcelField(title = "完成情况", sort = 60)
    public String getSuccessState() {
        return successState;
    }

    public void setSuccessState(String successState) {
        this.successState = successState;
    }

    @ExcelField(title = "发送人", sort = 1)
    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public Weekly getWeekly() {
        return weekly;
    }

    public void setWeekly(Weekly weekly) {
        this.weekly = weekly;
    }

    @ExcelField(title = "今日任务", sort = 30)
    public String getDayContent() {
        return dayContent;
    }

    public void setDayContent(String dayContent) {
        this.dayContent = dayContent;
    }

    @ExcelField(title = "明日计划", sort = 40)
    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
