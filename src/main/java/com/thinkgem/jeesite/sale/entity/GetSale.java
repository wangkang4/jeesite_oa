package com.thinkgem.jeesite.sale.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.expense.entity.Expense;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 报销流程实体类
 * @author dxq
 *
 */
public class GetSale extends ActEntity<GetSale>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int status;
	private User user;     //申请用户
	private Office office; //所属部门
	private String id;         //编号；
	private String proInsId;
	/*private String procInsId;*/   //流程实例编号
	private Date startTime;    //报销开始时间
	private Date endTime;      //报销结束时间
	private Double forMoney;   //报销金额
	private String reason;     //报销标题
	private String sNum;	   //编号的，加在标题前面存到数据库标题字段
	private String costType;   //报销类型（大类）
	private String address;		//报销回执单地址
	
	private String reasonTitle; //报销原因
	private String userId;
	private String officeId;
	private String fileAddress;//附件地址
	
	
	private String prText;     //人事主管意见
	private String leaderText; //部门经理意见
	private String leadertwoText;  //主管意见
	private String managerText;//研发总监意见
	private String prthreeText;//出纳意见
	private String prfourText;//财务主管意见
	private String prtwoText;      //财务总监意见；
	private String prfiveText;//总经理意见
	
	private String saleDetailId;//关联expenseDetail表id
	
	private String statu;
	private Expense expense;
	/**公司名*/
	private String ename;
	
	/** 模糊查询时的开始时间 */
	private Date st;
	/** 模糊查询时的结束时间 */
	private Date et;

    /////////////////////以下是非持久化字段////////////////////////////
    private String opt;
    /** 总出差补贴 */
	private Double sumAllowance;
	
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Double getSumAllowance() {
		return sumAllowance;
	}

	public void setSumAllowance(Double sumAllowance) {
		this.sumAllowance = sumAllowance;
	}
    @ExcelField(title = "报销类型",sort=35)
    public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getsNum() {
		return sNum;
	}
	public void setsNum(String sNum) {
		this.sNum = sNum;
	}

	/** 报销人名字 */
    private String userName;
    /** 报销人部门 */
    private String officeName;
    /** 报销人地点 */
    private String areaName;
    
    @ExcelField(title = "报销人",sort=10)
    public String getUserName() {
		return userName;
	}
    @ExcelField(title = "报销人部门",sort=20)
	public String getOfficeName() {
		return officeName;
	}
    @ExcelField(title = "报销人地点",sort=30)
	public String getAreaName() {
		return areaName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPrfourText() {
		return prfourText;
	}

	public void setPrfourText(String prfourText) {
		this.prfourText = prfourText;
	}

	public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	@ExcelField(title = "报销原因",sort=50)
	public String getReasonTitle() {
		return reasonTitle;
	}
	public void setReasonTitle(String reasonTitle) {
		this.reasonTitle = reasonTitle;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getSt() {
		return st;
	}
	public void setSt(Date st) {
		this.st = st;
	}
	public Date getEt() {
		return et;
	}
	public void setEt(Date et) {
		this.et = et;
	}
	
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	public String getLeadertwoText() {
		return leadertwoText;
	}
	public void setLeadertwoText(String leadertwoText) {
		this.leadertwoText = leadertwoText;
	}
	public String getPrtwoText() {
		return prtwoText;
	}
	public void setPrtwoText(String prtwoText) {
		this.prtwoText = prtwoText;
	}
	public String getProInsId() {
		return proInsId;
	}
	public void setProInsId(String proInsId) {
		this.proInsId = proInsId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManagerText() {
		return managerText;
	}
	public void setManagerText(String managerText) {
		this.managerText = managerText;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
	
	public String getSaleDetailId() {
		return saleDetailId;
	}
	public void setSaleDetailId(String saleDetailId) {
		this.saleDetailId = saleDetailId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@ExcelField(title = "报销金额",sort=40)
	public Double getForMoney() {
		return forMoney;
	}
	public void setForMoney(Double forMoney) {
		this.forMoney = forMoney;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPrText() {
		return prText;
	}
	public void setPrText(String prText) {
		this.prText = prText;
	}
	public String getLeaderText() {
		return leaderText;
	}
	public void setLeaderText(String leaderText) {
		this.leaderText = leaderText;
	}
	
	public String getPrthreeText() {
		return prthreeText;
	}

	public void setPrthreeText(String prthreeText) {
		this.prthreeText = prthreeText;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrfiveText() {
		return prfiveText;
	}
	public void setPrfiveText(String prfiveText) {
		this.prfiveText = prfiveText;
	}
	public String getFileAddress() {
		return fileAddress;
	}
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	
}
