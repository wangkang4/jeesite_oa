package com.thinkgem.jeesite.modules.tb.patent.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;

public class Person extends ActEntity<Person>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 负表tb_person对应字段
	 */
	private String id;//实例id
	private String person;//姓名
	private String position;//所占比例
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
