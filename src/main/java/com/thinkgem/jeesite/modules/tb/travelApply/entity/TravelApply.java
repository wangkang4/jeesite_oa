package com.thinkgem.jeesite.modules.tb.travelApply.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
/**
 * OA出差申请流程
 * @author Mr.dong
 *
 */
public class TravelApply extends ActEntity<TravelApply>{

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
	 * 出发地
	 */
	private String startAddress;
	
	/**
	 * 目的地
	 */
	private String endAddress;
	
	/**
	 * 项目名称
	 */
	private String project;
	
	/**
	 * 出差开始时间
	 */
	private Date startDate;
	
	/**
	 * 出差结束时间
	 */
	private Date endDate;
	
	/**
	 * 出差天数
	 */
	private double day;
	
	/**
	 * 交通工具
	 */
	private String traffic;
	
	/**
	 * 同行人员
	 */
	private String person;
	
	/**
	 * 出差事由
	 */
	private String cause;
	
	/**
	 * 城际交通费
	 */
	private double costOne;
	
	/**
	 * 住宿费
	 */
	private double costTwo;
	
	/**
	 * 差旅补助
	 */
	private double costThree;
	
	/**
	 * 其他费用
	 */
	private double costFour;
	
	/**
	 * 合计
	 */
	private double allCost;
	
	/**
	 * 出差总结
	 */
	private String summary;
	
	/**
	 * 负表(tb_travelPlan)关联ID
	 */
	private String planId;
	
	/**
	 * 审核状态
	 */
	private String statu;
	
	/**
	 * 出差编码
	 */
	private String num;
	
	/**
	 主管意见
	 */
	private String proneText;
	
	/**
	 * 总裁意见
	 */
	private String prtwoText;
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
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getDay() {
		return day;
	}
	public void setDay(double day) {
		this.day = day;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public double getCostOne() {
		return costOne;
	}
	public void setCostOne(double costOne) {
		this.costOne = costOne;
	}
	public double getCostTwo() {
		return costTwo;
	}
	public void setCostTwo(double costTwo) {
		this.costTwo = costTwo;
	}
	public double getCostThree() {
		return costThree;
	}
	public void setCostThree(double costThree) {
		this.costThree = costThree;
	}
	public double getCostFour() {
		return costFour;
	}
	public void setCostFour(double costFour) {
		this.costFour = costFour;
	}
	public double getAllCost() {
		return allCost;
	}
	public void setAllCost(double allCost) {
		this.allCost = allCost;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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
	
	
}
