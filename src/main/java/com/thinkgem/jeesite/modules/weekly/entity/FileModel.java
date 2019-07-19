package com.thinkgem.jeesite.modules.weekly.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

public class FileModel extends DataEntity<Weekly>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String name;  
    private String attasize;  
    private String attapath;  
    private String proftype;
    private String profid;
    private String createrid;
    private Date creatertime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAttapath() {
		return attapath;
	}
	public void setAttapath(String attapath) {
		this.attapath = attapath;
	}
	public String getProftype() {
		return proftype;
	}
	public void setProftype(String proftype) {
		this.proftype = proftype;
	}
	public String getProfid() {
		return profid;
	}
	public void setProfid(String profid) {
		this.profid = profid;
	}
	public String getCreaterid() {
		return createrid;
	}
	public void setCreaterid(String createrid) {
		this.createrid = createrid;
	}
	public Date getCreatertime() {
		return creatertime;
	}
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}
	public String getAttasize() {
		return attasize;
	}
	public void setAttasize(String attasize) {
		this.attasize = attasize;
	}
}
