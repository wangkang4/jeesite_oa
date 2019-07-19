package com.thinkgem.jeesite.modules.tb.driving.entity;


import com.thinkgem.jeesite.common.persistence.ActEntity;

import java.util.Date;
/**
 * 
 * @ClassName: Driving 
 * @Description: 自驾车出差申请表实体类 
 * @author: WangFucheng
 * @date 2018年7月16日 下午2:57:11 
 *
 */
public class Driving extends ActEntity<Driving>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 自驾车申请id */
	private String id;
	/** 自驾车出差申请标题*/
	private String title;
	/** 同行人员 */
	private String peer;
	/** 用车时间 */
	private Date transportTime;
	/** 出发地 */
	private String origin;
	/** 目的地 */
	private String destination;
	/** 预计公里数 */
	private Double estimatedMiles;
	/** 每升汽油费标准 */
	private Double gasoline;
	/** 预计费用 */
	private Double estimatedCost;
	/** 自驾原因 */
	private String reason;
	/** 审核状态 审核中 驳回 审核通过 */
	private String statu;
	/** 办事处主任意见 */
	private String proneText;
	
	/** 总裁 */
	private String prtwoText;
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getId() {
		return id;
	}
	public String getPeer() {
		return peer;
	}
	public Date getTransportTime() {
		return transportTime;
	}
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	public Double getEstimatedMiles() {
		return estimatedMiles;
	}
	public Double getGasoline() {
		return gasoline;
	}
	public Double getEstimatedCost() {
		return estimatedCost;
	}
	public String getReason() {
		return reason;
	}
	public String getProneText() {
		return proneText;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPeer(String peer) {
		this.peer = peer;
	}
	public void setTransportTime(Date transportTime) {
		this.transportTime = transportTime;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setEstimatedMiles(Double estimatedMiles) {
		this.estimatedMiles = estimatedMiles;
	}
	public void setGasoline(Double gasoline) {
		this.gasoline = gasoline;
	}
	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setProneText(String proneText) {
		this.proneText = proneText;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrtwoText() {
		return prtwoText;
	}
	public void setPrtwoText(String prtwoText) {
		this.prtwoText = prtwoText;
	}
	
}
