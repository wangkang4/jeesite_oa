package com.thinkgem.jeesite.modules.tb.induction.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import java.util.Date;

public class Induction extends ActEntity<Induction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	/** 标题 */
	private String title;
	/** 申请地点 */
	private Area applyAddress;
	/** 申请部门 */
	private Office applyOffice;
	/** 面试日期 */
	private Date interviewDate;
	/** 面试地点 */
	private String interviewAddress;
	/** 录用人姓名 */
	private String employedName;
	/** 转正后工资 */
	private Double positiveMoney;
	/** 试用期工资 */
	private Double trialMoney;
	/** 录用部门 */
	private Office employedOffice;
	/** 录用岗位 */
	private String employedJob;
	/** 岗位级别 */
	private String jobLevel;
	/** 到岗日期 */
	private Date workDate;
	/** 联系电话 */
	private String phone;
	/** 转正日期 */
	private Date positiveDate;
	/** 合同签订日期 */
	private Date contractSignedDate;
	/** 合同起始日期 */
	private Date contractStartDate;
	/** 合同结束日期 */
	private Date contractEndDate;
	/** 应聘人简历和面试意见 */
	private String fileOneAddress;
	/** 相关证件 */
	private String fileTwoAddress;
	
	/** 部门经理 */
	private String proneText;
	/** 研发总监 */
	private String prtwoText;
	/** 商务主管 */
	private String prthreeText;
	/** 主管 */
	private String prfourText;
	/** 财务主管 */
	private String prfiveText;
	/** 财务总监 */
	private String prsixText;
	
	private String statu;
	
	
	
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public Office getEmployedOffice() {
		return employedOffice;
	}
	public void setEmployedOffice(Office employedOffice) {
		this.employedOffice = employedOffice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public Area getApplyAddress() {
		return applyAddress;
	}
	public Office getApplyOffice() {
		return applyOffice;
	}
	public Date getInterviewDate() {
		return interviewDate;
	}
	public String getInterviewAddress() {
		return interviewAddress;
	}
	public String getEmployedName() {
		return employedName;
	}
	public Double getPositiveMoney() {
		return positiveMoney;
	}
	public Double getTrialMoney() {
		return trialMoney;
	}
	public String getEmployedJob() {
		return employedJob;
	}
	public String getJobLevel() {
		return jobLevel;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public String getPhone() {
		return phone;
	}
	public Date getPositiveDate() {
		return positiveDate;
	}
	public Date getContractSignedDate() {
		return contractSignedDate;
	}
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public String getFileOneAddress() {
		return fileOneAddress;
	}
	public String getFileTwoAddress() {
		return fileTwoAddress;
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
	public void setTitle(String title) {
		this.title = title;
	}
	public void setApplyAddress(Area applyAddress) {
		this.applyAddress = applyAddress;
	}
	public void setApplyOffice(Office applyOffice) {
		this.applyOffice = applyOffice;
	}
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}
	public void setEmployedName(String employedName) {
		this.employedName = employedName;
	}
	public void setPositiveMoney(Double positiveMoney) {
		this.positiveMoney = positiveMoney;
	}
	public void setTrialMoney(Double trialMoney) {
		this.trialMoney = trialMoney;
	}
	public void setEmployedJob(String employedJob) {
		this.employedJob = employedJob;
	}
	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPositiveDate(Date positiveDate) {
		this.positiveDate = positiveDate;
	}
	public void setContractSignedDate(Date contractSignedDate) {
		this.contractSignedDate = contractSignedDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public void setFileOneAddress(String fileOneAddress) {
		this.fileOneAddress = fileOneAddress;
	}
	public void setFileTwoAddress(String fileTwoAddress) {
		this.fileTwoAddress = fileTwoAddress;
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
	
	
}
