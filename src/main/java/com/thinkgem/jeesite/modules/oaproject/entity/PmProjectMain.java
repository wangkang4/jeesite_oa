/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

/**
 * 单表生成Entity
 *
 * @author zhangbingbing
 * @version 2018-03-13
 */
public class PmProjectMain extends DataEntity<PmProjectMain> {

    private static final long serialVersionUID = 1L;
    private String projectName;        // 项目名
    private String projectId;        // 项目编号
    private String projectType;        // 项目类型：1.自主开发；2.外包开发；
    private String projectIndustry;        // 项目行业：1.教育项目；2.矿产项目；3.政府项目；4.金融项目
    private String projectState;        // 项目状态：1.机会点；2.立项；3.合同；4.启动；5.验收；6.回款
    private String projectStage;        // 项目阶段：1.启动；2.开发；3.试运行；4.维护
    private String projectMoney;        // 项目金额
    private String projectSummary;        // 项目概述
    private String projectAddress;        // 项目地址
    private String projectCustomer;    //项目客户群

    private String userId;                //成员id（和用户表有）
    private String roleName;            //角色id(和字典表中有关)
    private String useraName;            //用户名称

    private User manager;                //项目经理
    private User member;                //赞助人
    private User administration;        //销售人员
    private User product;                //产品部成员
    private User research;                //商务人员
    private User warranty;                //研发负责人
    private User business;                //商务成员
    private User logistics;                //其他人员

    private String otherProjectId;        //被关联项目id

    private String opponetName;                    //竞争对象姓名
    private String opponetContent;                //竞争对手描述

    private String company;                    //企业名称
    private String customerName;            //客户名称
    private String phone;                    //电话
    private String position;                //职位

    private String cooperation;                //合作单位名称
    private String contacts;                //联系人名
    private String name;                    //客户群名

    private String cooperationPattern;        //合作模式

    private String documentName;            //文件名
    private String documentAddress;            //文件路径

    private String examine;                    //审批状态
    private String reject;                    //驳回原因

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentAddress() {
        return documentAddress;
    }

    public void setDocumentAddress(String documentAddress) {
        this.documentAddress = documentAddress;
    }

    public String getCooperationPattern() {
        return cooperationPattern;
    }

    public void setCooperationPattern(String cooperationPattern) {
        this.cooperationPattern = cooperationPattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectCustomer() {
        return projectCustomer;
    }

    public void setProjectCustomer(String projectCustomer) {
        this.projectCustomer = projectCustomer;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getCooperation() {
        return cooperation;
    }

    public void setCooperation(String cooperation) {
        this.cooperation = cooperation;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOpponetName() {
        return opponetName;
    }

    public void setOpponetName(String opponetName) {
        this.opponetName = opponetName;
    }

    public String getOpponetContent() {
        return opponetContent;
    }

    public void setOpponetContent(String opponetContent) {
        this.opponetContent = opponetContent;
    }

    public String getOtherProjectId() {
        return otherProjectId;
    }

    public void setOtherProjectId(String otherProjectId) {
        this.otherProjectId = otherProjectId;
    }

    public String getUseraName() {
        return useraName;
    }

    public void setUseraName(String useraName) {
        this.useraName = useraName;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public User getAdministration() {
        return administration;
    }

    public void setAdministration(User administration) {
        this.administration = administration;
    }

    public User getProduct() {
        return product;
    }

    public void setProduct(User product) {
        this.product = product;
    }

    public User getResearch() {
        return research;
    }

    public void setResearch(User research) {
        this.research = research;
    }

    public User getWarranty() {
        return warranty;
    }

    public void setWarranty(User warranty) {
        this.warranty = warranty;
    }

    public User getBusiness() {
        return business;
    }

    public void setBusiness(User business) {
        this.business = business;
    }

    public User getLogistics() {
        return logistics;
    }

    public void setLogistics(User logistics) {
        this.logistics = logistics;
    }

    public PmProjectMain() {
        super ();
    }

    public PmProjectMain(String id) {
        super ( id );
    }

    @Length(min = 1, max = 200, message = "项目名长度必须介于 1 和 200 之间")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Length(min = 1, max = 200, message = "项目编号长度必须介于 1 和 200 之间")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectIndustry() {
        return projectIndustry;
    }

    public void setProjectIndustry(String projectIndustry) {
        this.projectIndustry = projectIndustry;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public String getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(String projectMoney) {
        this.projectMoney = projectMoney;
    }


    @Length(min = 0, max = 2000, message = "项目概述长度必须介于 0 和 2000 之间")
    public String getProjectSummary() {
        return projectSummary;
    }

    public void setProjectSummary(String projectSummary) {
        this.projectSummary = projectSummary;
    }

}