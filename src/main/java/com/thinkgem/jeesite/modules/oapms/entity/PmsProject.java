package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class PmsProject extends DataEntity<PmsProject> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    private String projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 客户
     */
    private Customer customer;
    /**
     * 客户联系人
     */
    private CustomerContact customerContact;
    /**
     * 目前阶段(机会点、项目启动、技术交流、招标准备、招标、签订合同、交付中、已收款，可自定义见字典)
     */
    private String status;
    /**
     * 进度
     */
    private Double progress;
    /**
     * 项目金额
     */
    private Double money;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 结单日期
     */
    private Date statusmentTime;
    /**
     * 客户分析
     */
    private String costomerAnalysis;
    /**
     * 决策链分析
     */
    private String decMakChainAnalysis;
    /**
     * 竞争对手分析
     */
    private String competitorsAnalysis;
    /**
     * 机会点
     */
    private String chancePoint;
    /**
     * 问题点
     */
    private String problemPoint;
    /**
     * 项目目标
     */
    private String target;
    /**
     * 市场策略及战术
     */
    private String marketStrategyTactics;
    /**
     * 实施计划
     */
    private String implementationPlan;
    /**
     * 资源求助
     */
    private String resourceHelp;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 项目重要程度
     */
    private String importantDepende;

    /**
     * 销售经理
     */
    private User saler;
    /**
     * 产品经理
     */
    private User producter;
    /**
     * 研发经理
     */
    private User devloper;


    /**
     * 研发参与人
     */
    private String persons;

    /**
     * 研发参与人姓名
     */
    private String personsName;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    public Date getStatusmentTime() {
        return statusmentTime;
    }

    public void setStatusmentTime(Date statusmentTime) {
        this.statusmentTime = statusmentTime;
    }

    public String getCostomerAnalysis() {
        return costomerAnalysis;
    }

    public void setCostomerAnalysis(String costomerAnalysis) {
        this.costomerAnalysis = costomerAnalysis;
    }

    public String getDecMakChainAnalysis() {
        return decMakChainAnalysis;
    }

    public void setDecMakChainAnalysis(String decMakChainAnalysis) {
        this.decMakChainAnalysis = decMakChainAnalysis;
    }

    public String getCompetitorsAnalysis() {
        return competitorsAnalysis;
    }

    public void setCompetitorsAnalysis(String competitorsAnalysis) {
        this.competitorsAnalysis = competitorsAnalysis;
    }

    public String getChancePoint() {
        return chancePoint;
    }

    public void setChancePoint(String chancePoint) {
        this.chancePoint = chancePoint;
    }

    public String getProblemPoint() {
        return problemPoint;
    }

    public void setProblemPoint(String problemPoint) {
        this.problemPoint = problemPoint;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMarketStrategyTactics() {
        return marketStrategyTactics;
    }

    public void setMarketStrategyTactics(String marketStrategyTactics) {
        this.marketStrategyTactics = marketStrategyTactics;
    }

    public String getImplementationPlan() {
        return implementationPlan;
    }

    public void setImplementationPlan(String implementationPlan) {
        this.implementationPlan = implementationPlan;
    }

    public String getResourceHelp() {
        return resourceHelp;
    }

    public void setResourceHelp(String resourceHelp) {
        this.resourceHelp = resourceHelp;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }

    public User getSaler() {
        return saler;
    }

    public void setSaler(User saler) {
        this.saler = saler;
    }

    public User getProducter() {
        return producter;
    }

    public void setProducter(User producter) {
        this.producter = producter;
    }

    public User getDevloper() {
        return devloper;
    }

    public void setDevloper(User devloper) {
        this.devloper = devloper;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }


    @Override
    public String toString() {
        return "PmsProject [projectId=" + projectId + ", projectName=" + projectName + ", customer=" + customer
                + ", customerContact=" + customerContact + ", status=" + status + ", progress=" + progress + ", money="
                + money + ", startTime=" + startTime + ", endTime=" + endTime + ", statusmentTime=" + statusmentTime
                + ", costomerAnalysis=" + costomerAnalysis + ", decMakChainAnalysis=" + decMakChainAnalysis
                + ", competitorsAnalysis=" + competitorsAnalysis + ", chancePoint=" + chancePoint + ", problemPoint="
                + problemPoint + ", target=" + target + ", marketStrategyTactics=" + marketStrategyTactics
                + ", implementationPlan=" + implementationPlan + ", resourceHelp=" + resourceHelp + ", productType="
                + productType + ", saler=" + saler + ", producter=" + producter + ", devloper=" + devloper
                + ", persons=" + persons + "]";
    }

    public String getPersonsName() {
        return personsName;
    }

    public void setPersonsName(String personsName) {
        this.personsName = personsName;
    }

    public String getImportantDepende() {
        return importantDepende;
    }

    public void setImportantDepende(String importantDepende) {
        this.importantDepende = importantDepende;
    }

}
