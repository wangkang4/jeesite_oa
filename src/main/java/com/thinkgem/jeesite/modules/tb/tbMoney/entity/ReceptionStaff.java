package com.thinkgem.jeesite.modules.tb.tbMoney.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

public class ReceptionStaff extends DataEntity<ReceptionStaff>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 标识ID */
	private String id;
	/** 姓名 */
	private String name;
	/** 职位 */
	private String position;
	/** 类型 */
	private String type;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
