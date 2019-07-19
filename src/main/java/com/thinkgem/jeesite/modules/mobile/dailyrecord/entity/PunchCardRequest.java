package com.thinkgem.jeesite.modules.mobile.dailyrecord.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class PunchCardRequest {

    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PunchCardRequest [id=" + id + ", user=" + user + ", punchDate=" + punchDate + ", punchType=" + punchType
                + ", latitude=" + latitude + ", longitude=" + longitude + ", place=" + place + ", punchTimeType="
                + punchTimeType + "]";
    }

}
