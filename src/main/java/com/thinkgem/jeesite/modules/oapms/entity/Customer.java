package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * @ClassName: Customer
 * @Description: TODO(客户实体类)
 * @author: WangFucheng
 * @date 2018年1月28日 上午11:03:00
 */
public class Customer extends DataEntity<Customer> {
    private static final long serialVersionUID = 1L;
    /**
     * 客户Id
     */
    private String customerId;
    /**
     * 客户名字
     */
    private String customerName;
    /**
     * 客户地址
     */
    private String address;
    /**
     * 所属类别(公司级别、办事处级别 自定义见字典)
     */
    private String category;
    /**
     * 所属行业(大企业、矿山、高校、金融、司法 自定义见字典)
     */
    private String industry;
    /**
     * 所属办事处(合肥办、济南办 自定义见字典)
     */
    private String office;
    /**
     * 所属区域(办事处是一级、区域是二级，区域对应城市 自定义见字典)
     */
    private String area;
    /**
     * 附件名字
     */
    private String attachmentName;
    /**
     * 附件地址
     */
    private String attachmentAddress;
    /**
     * 销售经理
     */
    private User saler;
    /**
     * 产品经理
     */
    private User producter;
    /**
     * 相关人
     */
    private String persons;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
                + ", category=" + category + ", industry=" + industry + ", office=" + office + ", area=" + area
                + ", attachmentName=" + attachmentName + ", attachmentAddress=" + attachmentAddress + ", saler=" + saler
                + ", producter=" + producter + ", persons=" + persons + "]";
    }

}
