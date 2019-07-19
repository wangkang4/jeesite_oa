package com.thinkgem.jeesite.modules.daily.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 日报实体类
 *
 * @author ShiLiangYu
 * @version 2017-8-2
 */
public class TDaily extends DataEntity<TDaily> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date dayTime; // 日报时间
    private Integer performance; // 完成情况
    private String dayContent; // 日报内容
    private String planContent; // 明日计划
    private User user; // user.id发报人
    private Date createrTime; // 发布时间
    private String remark;

    // 伪列
    private String sendName;// 收报人

    public TDaily() {
        super ();
        // TODO Auto-generated constructor stub
    }

    public TDaily(String id) {
        super ( id );
        // TODO Auto-generated constructor stub
    }

    public Date getDayTime() {
        return dayTime;
    }

    public void setDayTime(Date dayTime) {
        this.dayTime = dayTime;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
