package com.thinkgem.jeesite.modules.oaproject.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目与客户的关联
 *
 * @author zhangbingbing
 * @ClassName:@PmProjectCustomer.java
 * @Descriptopn:（XXX）
 * @date @2018年3月14日
 */
public class PmProjectCustomer extends DataEntity<PmProjectCustomer> {
    private static final long serialVersionUID = 1L;
    private String projectId;            //项目id
    private String customerId;            //客户id

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
