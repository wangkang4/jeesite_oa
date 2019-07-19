package com.thinkgem.jeesite.modules.tb.tbMoney.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class Business extends ActEntity<Business>{

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
	
	/** 使用日期 */
	private Date tbDate;
	/** 用餐地点 */
	private String place;
	/** 用餐金额 */
	private Double money;
	/** 人员表id */
	private String receptionStaff;
	/** 我司陪同人员 */
	private String persons;
	
	/** 标题 */
	private String reason;
	/** 备注招待事由  */
	private String notes;
	/** 附件地址 */
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
	public String getPlace() {
		return place;
	}
	public Double getMoney() {
		return money;
	}
	public String getReceptionStaff() {
		return receptionStaff;
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
	public void setPlace(String place) {
		this.place = place;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public void setReceptionStaff(String receptionStaff) {
		this.receptionStaff = receptionStaff;
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
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getPersons() {
		return persons;
	}
	public void setPersons(String persons) {
		this.persons = persons;
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
