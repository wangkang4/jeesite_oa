package com.thinkgem.jeesite.modules.expense.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 报销单详细内容实体类
 *
 * @author vat
 */
public class ExpenseDetail extends DataEntity<ExpenseDetail> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 报销详情单id
     */
    private String detailId;
    /**
     * 报销详情单时间
     */
    private Date detailDate;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 关联getSale表id
     */
    private String saleDetailId;
    /**
     * 删除关联getSale表id不需要的数据
     */
    private String saleDetailIds;
    /**
     * 报销金额
     */

    private BigDecimal money;
    /**
     * 费用类型
     */
    private String costType;

    /**
     * 费用描述
     */
    private String costDescription;
    /**
     * 可报销金额
     */
    private BigDecimal amountMoney;
    /**
     * 实际报销金额
     */
    private BigDecimal allowMoney;

    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 详细信息
     */
    private String information;
    /**
     * 关联部门表id
     */
    private String expenseId;
    /**
     * 费用类型表id
     */
    private String costTypeId;
    /**
     * 费用描述表id
     */
    private String costDescriptionId;
    /**
     * 预审批id
     */
    private String tbMoneyId;
    private String tbMoneyName;
    /**
     * 出差天数
     */
    private Double day;
    /**
     * 出发地
     */
    private String origin;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 出差编号
     */
    private String num;
    /**
     * 出差津贴
     */
    private Double allowance;
    /**
     * 最终出差津贴
     */
    private Double allowanceMoney;

    public Double getAllowanceMoney() {
        return allowanceMoney;
    }

    public void setAllowanceMoney(Double allowanceMoney) {
        this.allowanceMoney = allowanceMoney;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Date getDetailDate() {
        return detailDate;
    }

    public void setDetailDate(Date detailDate) {
        this.detailDate = detailDate;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostDescription() {
        return costDescription;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(String costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getCostDescriptionId() {
        return costDescriptionId;
    }

    public void setCostDescriptionId(String costDescriptionId) {
        this.costDescriptionId = costDescriptionId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSaleDetailId() {
        return saleDetailId;
    }

    public void setSaleDetailId(String saleDetailId) {
        this.saleDetailId = saleDetailId;
    }

    public String getSaleDetailIds() {
        return saleDetailIds;
    }

    public void setSaleDetailIds(String saleDetailIds) {
        this.saleDetailIds = saleDetailIds;
    }

    public String getTbMoneyId() {
        return tbMoneyId;
    }

    public void setTbMoneyId(String tbMoneyId) {
        this.tbMoneyId = tbMoneyId;
    }

    public String getTbMoneyName() {
        return tbMoneyName;
    }

    public void setTbMoneyName(String tbMoneyName) {
        this.tbMoneyName = tbMoneyName;
    }

    public BigDecimal getAllowMoney() {
        return allowMoney;
    }

    public void setAllowMoney(BigDecimal allowMoney) {
        this.allowMoney = allowMoney;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

}
