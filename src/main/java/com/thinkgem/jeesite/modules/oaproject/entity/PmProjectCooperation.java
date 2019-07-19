package com.thinkgem.jeesite.modules.oaproject.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目合作单位关联
 *
 * @author zhangbingbing
 * @ClassName:@PmProjectCooperation.java
 * @Descriptopn:（XXX）
 * @date @2018年3月14日
 */
public class PmProjectCooperation extends DataEntity<PmProjectCooperation> {
    private static final long serialVersionUID = 1L;
    private String projectId;                //项目id
    private String cooperationId;            //合作单位id
    private String cooperation;                //合作单位名称
    private String contacts;                //联系人名
    private String iphone;                    //电话
    private String position;                //职位
    private String cooperationPattern;        //合作模式

    public String getCooperationPattern() {
        return cooperationPattern;
    }

    public void setCooperationPattern(String cooperationPattern) {
        this.cooperationPattern = cooperationPattern;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(String cooperationId) {
        this.cooperationId = cooperationId;
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

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
