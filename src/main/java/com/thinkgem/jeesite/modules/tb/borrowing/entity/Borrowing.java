package com.thinkgem.jeesite.modules.tb.borrowing.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class Borrowing extends ActEntity<Borrowing>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**  */
	private String id;
	/** 标题 */
	private String title;
	/** 借款人 */
	private User name;
	/** 部门 */
	private Office office;
	/** 借款时间 */
	private Date time;
	/** 借款金额 */
	private Double money;
	/** 申请原因 */
	private String reason;
	/** 备注 */
	private String notes;
	/** 银行流水单 */
	private String address;
	/** 审核状态 */
	private String statu;
	/** 部门经理审核 */
	private String proneText;
	/** 研发总监审核 */
	private String prtwoText;
	/** 主管审核 */
	private String prthreeText;
	/** 出纳审核 */
	private String prfourText;
	/** 财务主管审核 */
	private String prfiveText;
	/** 财务总监审核 */
	private String prsixText;
	/** 总裁审核 */
	private String prsevenText;
	/** 总经理审核 */
	private String preightText;
	/** 搜索条件起始时间 */
	private Date st;
	/** 搜索条件结束时间 */
	private Date et;
	/**公司名称*/
	private String ename;
	
	
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
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
	public User getName() {
		return name;
	}
	public Office getOffice() {
		return office;
	}
	public Date getTime() {
		return time;
	}
	public Double getMoney() {
		return money;
	}
	public String getReason() {
		return reason;
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
	public void setName(User name) {
		this.name = name;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public String getPrfourText() {
		return prfourText;
	}
	public String getPrfiveText() {
		return prfiveText;
	}
	public String getPrsixText() {
		return prsixText;
	}
	public String getPrsevenText() {
		return prsevenText;
	}
	public String getPreightText() {
		return preightText;
	}
	public void setPrfourText(String prfourText) {
		this.prfourText = prfourText;
	}
	public void setPrfiveText(String prfiveText) {
		this.prfiveText = prfiveText;
	}
	public void setPrsixText(String prsixText) {
		this.prsixText = prsixText;
	}
	public void setPrsevenText(String prsevenText) {
		this.prsevenText = prsevenText;
	}
	public void setPreightText(String preightText) {
		this.preightText = preightText;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
