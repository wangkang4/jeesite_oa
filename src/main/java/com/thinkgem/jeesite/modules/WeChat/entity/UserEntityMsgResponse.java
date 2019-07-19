package com.thinkgem.jeesite.modules.WeChat.entity;

public class UserEntityMsgResponse extends MsgResponse{
	private static final long serialVersionUID = -4154816708672985619L;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DepartMsgResponse [id=" + id + ",errcode=" + this.getErrcode() + ", errmsg=" + this.getErrmsg() +"]";
	}
}
