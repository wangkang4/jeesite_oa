package com.thinkgem.jeesite.sale.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class GetSaleCount extends DataEntity<GetSaleCount>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String time;
	private int count;
	
	public String getTime() {
		return time;
	}
	public int getCount() {
		return count;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
