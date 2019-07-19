package com.thinkgem.jeesite.modules.tb.licenses.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class Licenses extends ActEntity<Licenses>{

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
	/** 使用日期 */
	private Date useDate;
	/** 归还时间 */
	private Date returnDate;
	/** 证照种类 */
	private String type;
	/** 用途 */
	private String notes;
	/** 主管 */
	private String proneText;
	/** 财务总监 */
	private String prtwoText;
	/** 应章保管人 */
	private String prthreeText;
	private String statu;
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
	public Date getUseDate() {
		return useDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public String getType() {
		return type;
	}
	public String getNotes() {
		return notes;
	}
	public String getProneText() {
		return proneText;
	}
	public String getPrtwoText() {
		return prtwoText;
	}
	public String getPrthreeText() {
		return prthreeText;
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
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public void setProneText(String proneText) {
		this.proneText = proneText;
	}
	public void setPrtwoText(String prtwoText) {
		this.prtwoText = prtwoText;
	}
	public void setPrthreeText(String prthreeText) {
		this.prthreeText = prthreeText;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	
}
