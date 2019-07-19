package com.thinkgem.jeesite.modules.risk.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;
/**
 * 风险反馈实体类
 * 
 * **/
public class RiskBack extends DataEntity<RiskBack>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	/** 风险id */
	private String riskId;		
	/**解决时间 */
	private Date solveTime;	
	/**反馈内容	*/
	private String riskContent;	
	
	/**风险信息实体类*/
	private RiskInfo riskInfo;
	
	
	
	private String delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public Date getSolveTime() {
		return solveTime;
	}

	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
	}

	public String getRiskContent() {
		return riskContent;
	}

	public void setRiskContent(String riskContent) {
		this.riskContent = riskContent;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	

	public RiskInfo getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(RiskInfo riskInfo) {
		this.riskInfo = riskInfo;
	}

	@Override
	public String toString() {
		return "RiskBack [id=" + id + ", riskId=" + riskId + ", solveTime=" + solveTime + ", riskContent=" + riskContent
				+ ", riskInfo=" + riskInfo + ", delFlag=" + delFlag + "]";
	}

	
	
	
	
	
}
