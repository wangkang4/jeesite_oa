package com.thinkgem.jeesite.modules.customer.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author Cxq
 * @ClassName: Resume
 * @Description: 客户履历实体类
 * @date 2018年3月14日 下午2:06:36
 */
public class Resume extends DataEntity<Resume> {

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    private String id;//客户履历id
    private String customerCompany;//就职单位
    private String customerId;//客户id
    private String customerPosition;//职位
    private String positionTime;//就职时间

    private String createId; //创建者id
    private String updateId; //更新者id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPosition() {
        return customerPosition;
    }

    public void setCustomerPosition(String customerPosition) {
        this.customerPosition = customerPosition;
    }

    public String getPositionTime() {
        return positionTime;
    }

    public void setPositionTime(String positionTime) {
        this.positionTime = positionTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

}
