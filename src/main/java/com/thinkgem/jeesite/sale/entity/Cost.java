package com.thinkgem.jeesite.sale.entity;

public class Cost {
	/** 费用描述id */
	private String id;
	/** 费用描述名称 */
	private String name;
	/** 费用类型id */
	private String pid;
	/** 费用类型名称 */
	private String pname;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPid() {
		return pid;
	}
	public String getPname() {
		return pname;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

	
}
