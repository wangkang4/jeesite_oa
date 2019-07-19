package com.thinkgem.jeesite.modules.tb.tbMoney.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class Special extends ActEntity<Special>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 标识ID */
	private String id;
	/** 用户 */
	private User user;
	/** 部门 */
	private Office office;
	/** 流程实例编号 */
//	private String procInsId;
	/** 需要日期 */
	private Date tbDate;
	/** 支付方式 */
	private String payType;
	/** 账户信息 */
	private String account;
	/** 金额 */
	private Double money;
	/** 标题 */
	private String reason;
	/** 申请理由 */
	private String notes;
	private String address;
	/** 主管审批 */
	private String proneText;
	/** 财务总监审批 */
	private String prtwoText;
	/** 总经理审批 */
	private String prthreeText;
	/** 总裁审批 */
	private String prfourText;
	private String remarks;
	private int leader;
	/** 审核状态 */
	private String statu;
	
	public String getPrfourText() {
		return prfourText;
	}
	public void setPrfourText(String prfourText) {
		this.prfourText = prfourText;
	}
	public String getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public Office getOffice() {
		return office;
	}
	public Date getTbDate() {
		return tbDate;
	}
	public String getPayType() {
		return payType;
	}
	public String getAccount() {
		return account;
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
	public String getRemarks() {
		return remarks;
	}
	public int getLeader() {
		return leader;
	}
	public String getStatu() {
		return statu;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public void setTbDate(Date tbDate) {
		this.tbDate = tbDate;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setLeader(int leader) {
		this.leader = leader;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getPrthreeText() {
		return prthreeText;
	}
	public void setPrthreeText(String prthreeText) {
		this.prthreeText = prthreeText;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
