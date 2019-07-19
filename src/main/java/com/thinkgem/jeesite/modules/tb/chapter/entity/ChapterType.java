package com.thinkgem.jeesite.modules.tb.chapter.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class ChapterType extends DataEntity<ChapterType>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** id */
	private String id;
	/** 对应的类型 */
	private String type;
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
