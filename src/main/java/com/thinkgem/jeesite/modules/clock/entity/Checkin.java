package com.thinkgem.jeesite.modules.clock.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * @ClassName: Checkin
 * @Description: TODO(打卡表实体类)
 * @author: WangFucheng
 * @date 2017年12月26日 上午11:13:06
 */
public class Checkin extends DataEntity<Checkin> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    private User user;
    /**
     * 打卡规则名称
     */
    private String groupname;
    /**
     * 打卡类型
     */
    private String checkinType;
    /**
     * 异常类型，如果有多个异常，以分号间隔
     */
    private String exceptionType;
    /**
     * 打卡时间.Unix时间戳
     */
    private Long checkinTime;
    /**
     * 打卡时间
     */
    private String checkinTime1;

    @ExcelField(title = "打卡时间", sort = 40)
    public String getCheckinTime1() {
        return checkinTime1;
    }

    public void setCheckinTime1(String checkinTime1) {
        this.checkinTime1 = checkinTime1;
    }

    /**
     * 打卡地点title
     */
    private String locationTitle;
    /**
     * 打卡地点详情
     */
    private String locationDetail;
    /**
     * 打卡wifi名称
     */
    private String wifiname;
    /**
     * 打卡备注
     */
    private String notes;
    /**
     * 打卡的MAC地址/bssid
     */
    private String wifimac;
    /**
     * 打卡的附件media_id,可使用media/get获取附件
     */
    private String mediaids;
    /**
     * 查询打卡时间区间的开始时间
     */
    private Long startTime;
    /**
     * 查询打卡时间区间的结束时间
     */
    private Long endTime;
    /**
     * 打卡人名
     */
    private String pname;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @ExcelField(title = "打卡类型", sort = 20)
    public String getCheckinType() {
        return checkinType;
    }

    public void setCheckinType(String checkinType) {
        this.checkinType = checkinType;
    }

    @ExcelField(title = "异常类型", sort = 55)
    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public Long getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Long checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    @ExcelField(title = "打卡地点", sort = 30)
    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    @ExcelField(title = "打卡wifi", sort = 50)
    public String getWifiname() {
        return wifiname;
    }

    public void setWifiname(String wifiname) {
        this.wifiname = wifiname;
    }

    @ExcelField(title = "打卡备注", sort = 60)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWifimac() {
        return wifimac;
    }

    public void setWifimac(String wifimac) {
        this.wifimac = wifimac;
    }

    public String getMediaids() {
        return mediaids;
    }

    public void setMediaids(String mediaids) {
        this.mediaids = mediaids;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @ExcelField(title = "打卡人", sort = 1)
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Checkin [user=" + user + ", groupname=" + groupname + ", checkinType=" + checkinType
                + ", exceptionType=" + exceptionType + ", checkinTime=" + checkinTime + ", checkinTime1=" + checkinTime1
                + ", locationTitle=" + locationTitle + ", locationDetail=" + locationDetail + ", wifiname=" + wifiname
                + ", notes=" + notes + ", wifimac=" + wifimac + ", mediaids=" + mediaids + ", startTime=" + startTime
                + ", endTime=" + endTime + ", pname=" + pname + "]";
    }

}
