package com.thinkgem.jeesite.modules.history.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * @ClassName: GetSaleSummary
 * @Description: TODO(用于审批汇总中的报销汇总展示和导出)
 * @author: WangFucheng
 * @date 2018年4月11日 下午2:17:18
 */
public class DownGetSale extends DataEntity<DownGetSale> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String userName;
    private String officeName;
    private String areaName;
    private String detailDate;
    private String costDescription;
    private String amountMoney;
    private String projectName;
    private String information;

    /*private String reasonTitle;*/
    @ExcelField(title = "报销人", sort = 10)
    public String getUserName() {
        return userName;
    }

    @ExcelField(title = "报销人部门", sort = 20)
    public String getOfficeName() {
        return officeName;
    }

    @ExcelField(title = "报销人所在地", sort = 25)
    public String getAreaName() {
        return areaName;
    }

    @ExcelField(title = "单据日期", sort = 30)
    public String getDetailDate() {
        return detailDate;
    }

    @ExcelField(title = "费用类型", sort = 40)
    public String getCostDescription() {
        return costDescription;
    }

    @ExcelField(title = "报销金额", sort = 50)
    public String getAmountMoney() {
        return amountMoney;
    }

    @ExcelField(title = "项目名称", sort = 60)
    public String getProjectName() {
        return projectName;
    }

    @ExcelField(title = "详细信息", sort = 70)
    public String getInformation() {
        return information;
    }

    /*@ExcelField(title = "报销原因",sort=80)
    public String getReasonTitle() {
        return reasonTitle;
    }*/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setDetailDate(String detailDate) {
        this.detailDate = detailDate;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    public void setAmountMoney(String amountMoney) {
        this.amountMoney = amountMoney;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setInformation(String information) {
        this.information = information;
    }
	/*public void setReasonTitle(String reasonTitle) {
		this.reasonTitle = reasonTitle;
	}*/


}