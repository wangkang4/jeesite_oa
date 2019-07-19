package com.thinkgem.jeesite.modules.WeChat.entity;

import java.io.Serializable;
import java.util.Arrays;
/**
 * 
 * @ClassName:@Checkin.java
 * @Descriptopn:（打卡信息实体类）
 * @author zhangbingbing
 * @date @2017年12月27日
 */
public class Checkin implements Serializable{
	
	private static final long serialVersionUID = 6102281663991601498L;
	private String userid;
	private String groupname;
	private String checkin_type;
	private String exception_type;
	private int checkin_time;
	private String location_title;
	private String location_detail;
	private String wifiname;
	private String notes;
	private String wifimac;
	private String[] mediaids;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getCheckin_type() {
		return checkin_type;
	}
	public void setCheckin_type(String checkin_type) {
		this.checkin_type = checkin_type;
	}
	public String getException_type() {
		return exception_type;
	}
	public void setException_type(String exception_type) {
		this.exception_type = exception_type;
	}
	public int getCheckin_time() {
		return checkin_time;
	}
	public void setCheckin_time(int checkin_time) {
		this.checkin_time = checkin_time;
	}
	public String getLocation_title() {
		return location_title;
	}
	public void setLocation_title(String location_title) {
		this.location_title = location_title;
	}
	public String getLocation_detail() {
		return location_detail;
	}
	public void setLocation_detail(String location_detail) {
		this.location_detail = location_detail;
	}
	public String getWifiname() {
		return wifiname;
	}
	public void setWifiname(String wifiname) {
		this.wifiname = wifiname;
	}
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
	public String[] getMediaids() {
		return mediaids;
	}
	public void setMediaids(String[] mediaids) {
		this.mediaids = mediaids;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Checkin{userid=" + userid + ", groupname=" + groupname + ", checkin_type=" + checkin_type
				+ ", exception_type=" + exception_type + ", checkin_time=" + checkin_time + ", location_title="
				+ location_title + ", location_detail=" + location_detail + ", wifiname=" + wifiname + ", notes="
				+ notes + ", wifimac=" + wifimac + ", mediaids=" + Arrays.toString(mediaids) + "}";
	}
}
