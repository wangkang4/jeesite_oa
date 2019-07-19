package com.thinkgem.jeesite.modules.customer.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author Cxq
 * @ClassName: Family
 * @Description: 客户家庭成员实体类
 * @date 2018年3月14日 下午2:07:53
 */
public class CustomerAnalysis extends DataEntity<CustomerAnalysis> {

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    private String id;//客户分析id
    private String customerId;//客户id
    private String alyPerson;//分析人员
    private String alyContent;//分析内容
    private String alyTime;//分析日期

    private String createId;
    private String updateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAlyPerson() {
        return alyPerson;
    }

    public void setAlyPerson(String alyPerson) {
        this.alyPerson = alyPerson;
    }

    public String getAlyContent() {
        return alyContent;
    }

    public void setAlyContent(String alyContent) {
        this.alyContent = alyContent;
    }

    public String getAlyTime() {
        return alyTime;
    }

    public void setAlyTime(String alyTime) {
        this.alyTime = alyTime;
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
