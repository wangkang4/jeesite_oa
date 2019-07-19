package com.thinkgem.jeesite.modules.mobile.dailyrecord.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class PunchCard extends DataEntity<PunchCard> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 打卡人
     */
    private User user;
    /**
     * 打卡时间
     */
    private Date punchDate;
    /**
     * 打卡类型 0：上班打卡，1：下班打卡
     */
    private String punchType;
    /**
     * 经度
     */
    private String latitude;
    /**
     * 纬度
     */
    private String longitude;
    /**
     * 打卡地点
     */
    private String place;
    /**
     * 打开状态 0：正常，1：迟到，2：早退
     */
    private String state;
    /**
     * 查询的开始时间
     */
    private Date startTime;
    /**
     * 查询的结束时间
     */
    private Date endTime;
    /**
     * 时间类型0：新增，1：更新
     */
    private String punchTimeType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPunchType() {
        return punchType;
    }

    public void setPunchType(String punchType) {
        this.punchType = punchType;
    }

    public String getPunchTimeType() {
        return punchTimeType;
    }

    public void setPunchTimeType(String punchTimeType) {
        this.punchTimeType = punchTimeType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(Date punchDate) {
        this.punchDate = punchDate;
    }

}
