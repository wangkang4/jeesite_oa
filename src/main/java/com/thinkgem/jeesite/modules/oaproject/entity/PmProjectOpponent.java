package com.thinkgem.jeesite.modules.oaproject.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目竞争对手关联
 *
 * @author zhangbingbing
 * @ClassName:@PmProjectOpponent.java
 * @Descriptopn:（XXX）
 * @date @2018年3月14日
 */
public class PmProjectOpponent extends DataEntity<PmProjectOpponent> {

    private static final long serialVersionUID = 1L;
    private String projectId;                    //项目id
    private String opponentId;                    //竞争对手id
    private String opponetName;                    //竞争对象姓名
    private String opponetContent;                //竞争对手描述

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
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

}
