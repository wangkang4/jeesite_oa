package com.thinkgem.jeesite.modules.tb.party.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;

import java.util.Date;
/**
 * 
 * @ClassName: Party 
 * @Description: 团建申请表实体类
 * @author: WangFucheng
 * @date 2018年8月6日 下午2:44:35 
 *
 */
public class Party extends ActEntity<Party>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** id */
	private String id;
	/** 标题 */
	private String title;
	/** 部门id */
	private String officeId;
	/** 部门名字 */
	private String officeName;
	/** 总人数 */
	private int count;
	/** 计划团建时间 */
	private Date planTime;
	/** 计划参加人数 */
	private int planCount;
	/** 可用团建经费 */
	private Double availableFunds;
	/** 本次团建经费预算 */
	private Double budget;
	/** 本次团建活动方案描述 */
	private String notes;
	/** 审批状态 */
	private String statu;
	/** 0-该数据未被审批 1-该数据被审批过 */
	private String status;
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getOfficeId() {
		return officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public int getCount() {
		return count;
	}
	public Date getPlanTime() {
		return planTime;
	}
	public int getPlanCount() {
		return planCount;
	}
	public Double getAvailableFunds() {
		return availableFunds;
	}
	public Double getBudget() {
		return budget;
	}
	public String getNotes() {
		return notes;
	}
	public String getStatu() {
		return statu;
	}
	public String getStatus() {
		return status;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}
	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}
	public void setAvailableFunds(Double availableFunds) {
		this.availableFunds = availableFunds;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
