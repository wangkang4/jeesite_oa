package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * @author dingxianqian
 * @ClassName: pmsContract
 * @Description: TODO(合同管理实体类)
 */
public class PmsContract extends DataEntity<PmsContract> {


    private static final long serialVersionUID = 1L;
    private String id;  //合同id
    private String title;  //合同标题
    private String customerId;// 客户id
    private String projectId;  //项目ID
    private Customer customer;
    private PmsProject pmsProject;
    private Double amount;   //合同金额
    private Double totalAmount; //累计金额
    private Date startTime;    //开始时间
    private Date endTime;      //结束时间；
    private String servicePeriod;  //服务期限
    private String contractTerms;  //合同条款
    private String attachmentName;   //附件名称
    private String attachmentAddress;  //附件地址；

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PmsProject getPmsProject() {
        return pmsProject;
    }

    public void setPmsProject(PmsProject pmsProject) {
        this.pmsProject = pmsProject;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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

    public String getServicePeriod() {
        return servicePeriod;
    }

    public void setServicePeriod(String servicePeriod) {
        this.servicePeriod = servicePeriod;
    }

    public String getContractTerms() {
        return contractTerms;
    }

    public void setContractTerms(String contractTerms) {
        this.contractTerms = contractTerms;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentAddress() {
        return attachmentAddress;
    }

    public void setAttachmentAddress(String attachmentAddress) {
        this.attachmentAddress = attachmentAddress;
    }

}
