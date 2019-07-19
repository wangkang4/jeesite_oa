package com.thinkgem.jeesite.modules.WeChat.entity;

import java.io.Serializable;
import java.util.Arrays;

public class UserEntity implements Serializable{

	private static final long serialVersionUID = 6102281663991601498L;
	private String userid;
	private String name;
	private String mobile;
	private String[] department;
	
	public String[] getDepartment() {
		return department;
	}
	public void setDepartment(String[] department) {
		this.department = department;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "UserEntity [userid=" + userid + ", name=" + name + ", mobile=" + mobile + ", department="
				+ Arrays.toString(department) + "]";
	}
	
}
