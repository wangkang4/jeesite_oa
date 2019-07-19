package com.thinkgem.jeesite.modules.tb.patent.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
/**
 * 
 * @author dongxueyong
 * OA专科申请流程
 */
public class Patent extends ActEntity<Patent>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主表实例ID
	 */
	private String id;
	
	/**
	 * 申请人基本信息
	 */
	private User user;
	private Office office;
	
	/**
	 * 专利申请类型
	 */
	private String applyType;
	
	/**
	 * 专利名称
	 */
	private String patentName;
	
	/**
	 * 专利申请号
	 */
	private String applyTel;
	
	/**
	 * 申请日期
	 */
	private Date applyDate;
	
	/**
	 * 奖励金额
	 */
	private double money;
	
	/**
	 * 侵权描述
	 */
	private String reason;
	
	/**
	 * 负表(tb_person)关联ID
	 */
	private String personId;
	
	/**
	 * 审核状态
	 */
	private String statu;
	
	/**
	 * 研发总监
	 */
	private String proneText;
	/**
	 * 财务总监
	 */
	private String prtwoText;
	/**
	 * 总裁
	 */
	private String prthreeText;
	/**
	 * 各地行政
	 */
	private String prfourText;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getPatentName() {
		return patentName;
	}
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	public String getApplyTel() {
		return applyTel;
	}
	public void setApplyTel(String applyTel) {
		this.applyTel = applyTel;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getProneText() {
		return proneText;
	}
	public void setProneText(String proneText) {
		this.proneText = proneText;
	}
	public String getPrtwoText() {
		return prtwoText;
	}
	public void setPrtwoText(String prtwoText) {
		this.prtwoText = prtwoText;
	}
	public String getPrthreeText() {
		return prthreeText;
	}
	public void setPrthreeText(String prthreeText) {
		this.prthreeText = prthreeText;
	}
	public String getPrfourText() {
		return prfourText;
	}
	public void setPrfourText(String prfourText) {
		this.prfourText = prfourText;
	}
	
	
	
	
}
