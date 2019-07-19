package com.thinkgem.jeesite.modules.CostExcel.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 项目实体类
 *
 * @author tanchaoyang
 */
public class Project extends DataEntity<Project> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 客户信息
     */
    private Client client;
    /**
     * 项目负责人
     */
    private User projectManager;
    /**
     * 项目金额
     */
    private Double projectMoney;
    /**
     * 创建人
     */
    private User createrBy;
    /**
     * 创建时间
     */
    private Date createrTime;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public User getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(User projectManager) {
        this.projectManager = projectManager;
    }

    public Double getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(Double projectMoney) {
        this.projectMoney = projectMoney;
    }

    public User getCreaterBy() {
        return createrBy;
    }

    public void setCreaterBy(User createrBy) {
        this.createrBy = createrBy;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
