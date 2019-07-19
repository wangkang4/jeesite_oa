package com.thinkgem.jeesite.modules.tb.contract.entity;


import com.thinkgem.jeesite.common.persistence.ActEntity;

import java.util.Date;

public class  Contract extends ActEntity<Contract>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 合同id */
	private String id;
	/** 合同申请人 */
	private String contractApply;
	/** 申请日期 */
	private Date contractDate;
	/** 合同类型 */
	private String contractType;
	/** 合同编号 */
	private String contractNum;
	/** 合同名称 */
	private String contractName;
	/** 合同金额 */
	private Double money;
	/** 对方公司名称 */
	private String companyName;
	/** 合同期限 */
	private String contractLimit;
	/** 主要合作内容 */
	private String cooperationContent;
	/** 毛利及毛利率情况说明 */
	private String grossMargin;
	/** 结算方式 */
	private String settlement;
	/** 违约责任 */
	private String responsibility;
	/** 其他 */
	private String other;
	/** 项目经理id */
	private String manager;
	/** 项目经理名称 */
	private String managerName;
	/** 项目经理id */
	private String managerTwo;
	/** 项目经理名称 */
	private String managerTwoName;
	/** 所属地区 */
	private String area;
	/** 所属地区名称*/
	private String areaName;
	/** 支付比例 (单位：月)*/
	private String paymentCycle;
	/** 维保期限(单位：月) */
	private Integer maintenance;
	/** 采购合同对应的销售合同 */
	private String friendId;
	/** 项目信息(目前没有) */
	private String project;
	/** 上传的合同地址 */
	private String address;
	/** 审核状态 */
	private String statu;
	/** 产品经理 */
	private String proneText;
	/** 项目经理 */
	private String prsevenText;
	/** 商务部长 */
	private String prtwoText;
	/** 总管 */
	private String prthreeText;
	/** 市场营销总经理 */
	private String prfourText;
	/** 财务总监 */
	private String prfiveText;
	/** 总经理 */
	private String prsixText;
	/** 作废合同流程 */
	private String status;
	/** 作废合同的流程实例id */
	private String procId;
	/** 作废原因 */
	private String abandon;
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
	public String getAbandon() {
		return abandon;
	}
	public void setAbandon(String abandon) {
		this.abandon = abandon;
	}
	public String getStatus() {
		return status;
	}
	public String getProcId() {
		return procId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setProcId(String procId) {
		this.procId = procId;
	}
	public String getId() {
		return id;
	}
	public String getContractApply() {
		return contractApply;
	}
	public Date getContractDate() {
		return contractDate;
	}
	public String getContractType() {
		return contractType;
	}
	public String getContractNum() {
		return contractNum;
	}
	public String getContractName() {
		return contractName;
	}
	public Double getMoney() {
		return money;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getContractLimit() {
		return contractLimit;
	}
	public String getCooperationContent() {
		return cooperationContent;
	}
	public String getGrossMargin() {
		return grossMargin;
	}
	public String getSettlement() {
		return settlement;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public String getOther() {
		return other;
	}
	public String getManager() {
		return manager;
	}
	public String getArea() {
		return area;
	}
	public String getPaymentCycle() {
		return paymentCycle;
	}
	public Integer getMaintenance() {
		return maintenance;
	}
	public String getProject() {
		return project;
	}
	public String getStatu() {
		return statu;
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
	public String getPrfourText() {
		return prfourText;
	}
	public String getPrfiveText() {
		return prfiveText;
	}
	public String getPrsixText() {
		return prsixText;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setContractApply(String contractApply) {
		this.contractApply = contractApply;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setContractLimit(String contractLimit) {
		this.contractLimit = contractLimit;
	}
	public void setCooperationContent(String cooperationContent) {
		this.cooperationContent = cooperationContent;
	}
	public void setGrossMargin(String grossMargin) {
		this.grossMargin = grossMargin;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	public void setMaintenance(Integer maintenance) {
		this.maintenance = maintenance;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public void setStatu(String statu) {
		this.statu = statu;
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
	public void setPrfourText(String prfourText) {
		this.prfourText = prfourText;
	}
	public void setPrfiveText(String prfiveText) {
		this.prfiveText = prfiveText;
	}
	public void setPrsixText(String prsixText) {
		this.prsixText = prsixText;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrsevenText() {
		return prsevenText;
	}
	public void setPrsevenText(String prsevenText) {
		this.prsevenText = prsevenText;
	}
	public String getManagerTwo() {
		return managerTwo;
	}
	public String getManagerTwoName() {
		return managerTwoName;
	}
	public void setManagerTwo(String managerTwo) {
		this.managerTwo = managerTwo;
	}
	public void setManagerTwoName(String managerTwoName) {
		this.managerTwoName = managerTwoName;
	}
	
}
