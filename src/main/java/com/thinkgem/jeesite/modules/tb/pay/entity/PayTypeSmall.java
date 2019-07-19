package com.thinkgem.jeesite.modules.tb.pay.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class PayTypeSmall extends DataEntity<PayTypeSmall>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String pid;
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getPid() {
		return pid;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}
