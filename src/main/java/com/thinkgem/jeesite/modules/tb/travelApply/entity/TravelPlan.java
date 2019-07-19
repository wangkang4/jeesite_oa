package com.thinkgem.jeesite.modules.tb.travelApply.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;

public class TravelPlan extends ActEntity<TravelPlan>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 负表ID
	 */
	private String id;
	
	/**
	 * 日期填写
	 */
	private String planDate;
	
	/**
	 * 客户名称
	 */
	private String customerName;
	
	/**
	 * 工作内容
	 */
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
