package com.thinkgem.jeesite.modules.weekly.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
import java.util.List;


/**
 * OA系统周报表对应实体类
 * @author tanchaoyang
 * @version 2017-8-2
 */
public class Weekly extends DataEntity<Weekly>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date daytime;			//周报发送时间
	private String daycontent;		//本周内容
	private String plancontent;		//下周计划
	private String createrid;		//创建人
	private Date creatertime;		//创建时间
	private String remark;
	private User user;    //收件人
	private List<FileUploadResult> filelist;  //上传的附件
	private String fileid;
	private List<TCheckBacklog> TCheckBackloglist;
	private Date startTime;   //按时间查询的开始时间
	private Date endTime;	//按时间查询的结束时间
	
	private String sendname;//收件人名
	public Date getDaytime() {
		return daytime;
	}
	public void setDaytime(Date daytime) {
		this.daytime = daytime;
	}
	public String getDaycontent() {
		return daycontent;
	}
	public void setDaycontent(String daycontent) {
		this.daycontent = daycontent;
	}
	public String getPlancontent() {
		return plancontent;
	}
	public void setPlancontent(String plancontent) {
		this.plancontent = plancontent;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<TCheckBacklog> getTCheckBackloglist() {
		return TCheckBackloglist;
	}
	public void setTCheckBackloglist(List<TCheckBacklog> tCheckBackloglist) {
		TCheckBackloglist = tCheckBackloglist;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getSendname() {
		return sendname;
	}
	public void setSendname(String sendname) {
		this.sendname = sendname;
	}
	public List<FileUploadResult> getFilelist() {
		return filelist;
	}
	public void setFilelist(List<FileUploadResult> filelist) {
		this.filelist = filelist;
	}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
