package com.thinkgem.jeesite.modules.customer.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author Cxq
 * @ClassName: Family
 * @Description: 客户家庭成员实体类
 * @date 2018年3月14日 下午2:07:53
 */
public class Family extends DataEntity<Family> {

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    //客户家庭信息id
    private String id;

    //客户id
    private String customerId;

    //姓名
    private String familyName;

    //出生日期
    private String familyBrithday;

    //与客户关系
    private String relationship;

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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyBrithday() {
        return familyBrithday;
    }

    public void setFamilyBrithday(String familyBrithday) {
        this.familyBrithday = familyBrithday;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
