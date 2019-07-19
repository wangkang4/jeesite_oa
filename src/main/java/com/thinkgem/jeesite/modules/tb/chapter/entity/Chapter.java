package com.thinkgem.jeesite.modules.tb.chapter.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class Chapter extends ActEntity<Chapter>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private String	id;
	
//	private String proc_ins_id;
	/** 用户id */
	private User user;
	/** 部门id */
	private Office office;
	/** 部门经理意见 */
	private String proneText;
	/** 研发总监意见 */
	private String prtwoText;
	/** 主管意见 */
	private String prthreeText;
	/** 财务总监意见 */
	private String prfourText;
	/** 行政主观意见 */
	private String prfiveText;
	/** 商务主管审核意见 */
	private String prsixText;
	/** 标题 */
	private String reason;
	/** 申请用印日期 */
	private Date applyDate;
	/** 用印时间 */
	private String chapterTime;
	/** 归还日期 */
	private Date returnDate;
	/** 用印种类 */
	private String chapterType;
	/** 用印文件名 */
	private String fileForChapter;
	/** 外借原因（非外借的可以不填） */
	private String reasonForBorrow;
	/** 用印文件数量 */
	private Integer numberForFile;
	/** 使用地点 */
	private String placeOfUser;
	/** 用印文件类型 */
	private String fileType;
	/** 审核状态 */
	private String statu;
	/** 文件地址 */
	private String address;
	private String remarks;
	private String contractId;
	/** 搜索条件起始时间 */
	private Date st;
	/** 搜索条件结束时间 */
	private Date et;
	
	
	public String getPrsixText() {
		return prsixText;
	}
	public void setPrsixText(String prsixText) {
		this.prsixText = prsixText;
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
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getReason() {
		return reason;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public String getChapterTime() {
		return chapterTime;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public String getChapterType() {
		return chapterType;
	}
	public String getFileForChapter() {
		return fileForChapter;
	}
	public String getReasonForBorrow() {
		return reasonForBorrow;
	}
	public Integer getNumberForFile() {
		return numberForFile;
	}
	public String getPlaceOfUser() {
		return placeOfUser;
	}
	public String getFileType() {
		return fileType;
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
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public void setChapterTime(String chapterTime) {
		this.chapterTime = chapterTime;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public void setChapterType(String chapterType) {
		this.chapterType = chapterType;
	}
	public void setFileForChapter(String fileForChapter) {
		this.fileForChapter = fileForChapter;
	}
	public void setReasonForBorrow(String reasonForBorrow) {
		this.reasonForBorrow = reasonForBorrow;
	}
	public void setNumberForFile(Integer numberForFile) {
		this.numberForFile = numberForFile;
	}
	public void setPlaceOfUser(String placeOfUser) {
		this.placeOfUser = placeOfUser;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	

}
