package com.thinkgem.jeesite.modules.oaproject.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目与项目关联
 *
 * @author zhangbingbing
 * @ClassName:@PmProjectRelation.java
 * @Descriptopn:（XXX）
 * @date @2018年3月14日
 */
public class PmProjectRelation extends DataEntity<PmProjectRelation> {

    private static final long serialVersionUID = 1L;
    private String projectId;                //项目的id
    private String otherProjectId;            //被关联的项目id

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getOtherProjectId() {
        return otherProjectId;
    }

    public void setOtherProjectId(String otherProjectId) {
        this.otherProjectId = otherProjectId;
    }

}
