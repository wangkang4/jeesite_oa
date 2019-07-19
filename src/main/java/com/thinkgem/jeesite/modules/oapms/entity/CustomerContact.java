package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import java.util.Date;

/**
 * @ClassName: CustomerContact
 * @Description: TODO(客户联系人实体类)
 * @author: WangFucheng
 * @date 2018年1月28日 上午11:03:49
 */
public class CustomerContact extends DataEntity<CustomerContact> {
    private static final long serialVersionUID = 1L;
    /**
     * 客户联系人Id
     */
    private String customerContactId;
    /**
     * 所属客户
     */
    private Customer customer;
    /**
     * 所属客户名
     */
    private String customerName;
    /**
     * 客户联系人名字
     */
    private String customerContactName;
    /**
     * 客户联系人别名
     */
    private String codeName;
    /**
     * 客户联系人电话
     */
    private String phone;
    /**
     * 客户联系人微信
     */
    private String weixin;
    /**
     * 客户联系人邮箱
     */
    private String email;
    /**
     * 客户联系人职位
     */
    private String position;
    /**
     * 客户联系人生日
     */
    private Date birthday;
    /**
     * 客户联系人兴趣爱好
     */
    private String interest;
    /**
     * 客户联系人性格特征
     */
    private String customerCharacter;
    /**
     * 备注
     */
    private String note;

    public String getCustomerContactId() {
        return customerContactId;
    }

    public void setCustomerContactId(String customerContactId) {
        this.customerContactId = customerContactId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ExcelField(title = "联系人姓名", sort = 20)
    public String getCustomerContactName() {
        return customerContactName;
    }

    public void setCustomerContactName(String customerContactName) {
        this.customerContactName = customerContactName;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @ExcelField(title = "联系人电话", sort = 40)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ExcelField(title = "联系人微信", sort = 50)
    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    @ExcelField(title = "联系人邮箱", sort = 60)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ExcelField(title = "联系人职位", sort = 70)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @ExcelField(title = "联系人生日", sort = 80)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ExcelField(title = "联系人爱好", sort = 90)
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @ExcelField(title = "联系人性格特征", sort = 100)
    public String getCustomerCharacter() {
        return customerCharacter;
    }

    public void setCustomerCharacter(String customerCharacter) {
        this.customerCharacter = customerCharacter;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ExcelField(title = "公司名", sort = 30)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "CustomerContact [customerContactId=" + customerContactId + ", customer=" + customer
                + ", customerContactName=" + customerContactName + ", codeName=" + codeName + ", phone=" + phone
                + ", weixin=" + weixin + ", email=" + email + ", position=" + position + ", birthday=" + birthday
                + ", interest=" + interest + ", customerCharacter=" + customerCharacter + ", note=" + note + "]";
    }


}
