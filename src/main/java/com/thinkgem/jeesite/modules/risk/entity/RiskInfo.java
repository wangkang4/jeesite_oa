package com.thinkgem.jeesite.modules.risk.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProject;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
/**
 * 风险信息类
 * **/
public class RiskInfo  extends DataEntity<RiskInfo>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	/**项目id*/
	private String proId;
	/**项目信息实体对象*/
	private PmsProject pmsPro;
	
	/**负责人实体对象*/
	private User user;
	/**风险标题*/
	private String riskName;
	
	/**风险概率：1.极低；2.低；3.中；4.高；5.极高*/
	private String riskPro;
	
	/**风险影响：1.极小；2.小；3.中；4.大；5.极大*/
	private String riskInfu;
	
	/**预期发生时间：1.极远；2.远；3.中；4.近；5.极近*/
	private String expecteTime;
	
	/**风险类别：1.技术风险；2.市场风险；3.成本风险；4.进度风险；5.管理风险*/
	private String riskType;
	
	/**风险状态：1.已关闭；2.未关闭*/
	private String riskState;
	
	/**负责人*/
	private String responseId;
	
	/**开始时间*/
	private Date startTime;
	/**结束时间*/
	private Date endTime;
	
	/**细节描述*/
	private String riskDescrible;
	
	/**缓解和应对措施*/
	private String solveProgramme;
	
	/**应对策略：1.预防；2.缓解；3.转移；4.接受；5备用*/
	private String riskAnswer;
	
	private String delFlag;
	

	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getProId() {
		return proId;
	}



	public void setProId(String proId) {
		this.proId = proId;
	}



	public PmsProject getPmsPro() {
		return pmsPro;
	}



	public void setPmsPro(PmsProject pmsPro) {
		this.pmsPro = pmsPro;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getRiskName() {
		return riskName;
	}



	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}



	public String getRiskPro() {
		return riskPro;
	}



	public void setRiskPro(String riskPro) {
		this.riskPro = riskPro;
	}



	public String getRiskInfu() {
		return riskInfu;
	}



	public void setRiskInfu(String riskInfu) {
		this.riskInfu = riskInfu;
	}



	



	public String getExpecteTime() {
		return expecteTime;
	}



	public void setExpecteTime(String expecteTime) {
		this.expecteTime = expecteTime;
	}



	public String getRiskType() {
		return riskType;
	}



	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}



	public String getRiskState() {
		return riskState;
	}



	public void setRiskState(String riskState) {
		this.riskState = riskState;
	}



	public String getResponseId() {
		return responseId;
	}



	public void setResponseId(String responseId) {
		this.responseId = responseId;
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



	public String getRiskDescrible() {
		return riskDescrible;
	}



	public void setRiskDescrible(String riskDescrible) {
		this.riskDescrible = riskDescrible;
	}



	public String getSolveProgramme() {
		return solveProgramme;
	}



	public void setSolveProgramme(String solveProgramme) {
		this.solveProgramme = solveProgramme;
	}



	public String getRiskAnswer() {
		return riskAnswer;
	}



	public void setRiskAnswer(String riskAnswer) {
		this.riskAnswer = riskAnswer;
	}



	public String getDelFlag() {
		return delFlag;
	}



	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}



	@Override
	public String toString() {
		return "RiskInfo [ id=" + id + ", proId=" + proId + ", pmsPro=" + pmsPro + ", user="
				+ user + ", riskName=" + riskName + ", riskPro=" + riskPro + ", riskInfu=" + riskInfu + ", expecteTime="
				+ expecteTime + ", riskType=" + riskType + ", riskState=" + riskState + ", responseId=" + responseId
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", riskDescrible=" + riskDescrible
				+ ", solveProgramme=" + solveProgramme + ", riskAnswer=" + riskAnswer + ", delFlag=" + delFlag + "]";
	}



	

	
	
	
	
}
