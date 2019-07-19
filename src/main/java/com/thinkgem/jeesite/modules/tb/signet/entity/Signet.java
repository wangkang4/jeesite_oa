package com.thinkgem.jeesite.modules.tb.signet.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class Signet extends ActEntity<Signet>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private String	id;
	/**  */
	private String title;
	/** 用户id */
	private User user;
	/** 申请日期 */
	private Date applyDate;
	/** 刻制应章名称 */
	private String signetName;
	/** 刻制原因 */
	private String notes;
	/** 财务总监 */
	private String proneText;
	/** 主管 */
	private String prtwoText;
	/** 筛选条件开始时间 */
	private Date st;
	/** 筛选条件结束时间 */
	private Date et;
	
	
	public Date getSt() {
		return st;
	}


	public Date getEt() {
		return et;
	}


	public void setSt(Date st) {
		this.st = st;
	}


	public void setEt(Date et) {
		this.et = et;
	}


	private String statu;


	public String getPrtwoText() {
		return prtwoText;
	}


	public void setPrtwoText(String prtwoText) {
		this.prtwoText = prtwoText;
	}


	public String getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public User getUser() {
		return user;
	}


	public Date getApplyDate() {
		return applyDate;
	}


	public String getSignetName() {
		return signetName;
	}


	public String getNotes() {
		return notes;
	}


	public String getProneText() {
		return proneText;
	}


	public String getStatu() {
		return statu;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}


	public void setSignetName(String signetName) {
		this.signetName = signetName;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public void setProneText(String proneText) {
		this.proneText = proneText;
	}


	public void setStatu(String statu) {
		this.statu = statu;
	}
	
}
