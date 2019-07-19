package com.thinkgem.jeesite.modules.CostExcel.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
import java.util.List;


/**
 * 客户表实体类
 *
 * @author tanchaoyang
 */
public class Client extends DataEntity<Client> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 客户公司
     */
    private String clientCompany;
    /**
     * 客户负责人名称
     */
    private String clientManagerName;
    /**
     * 客户负责人手机
     */
    private String clientManagerPhone;
    /**
     * 客户负责人性别
     */
    private String clientManagerSex;
    /**
     * 备注
     */
    private String content;
    /**
     * 创建人
     */
    private User createrBy;
    /**
     * 创建时间
     */
    private Date createrTime;

    /**
     * 客户下的项目list
     */
    private List<Project> projectList;


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(String clientCompany) {
        this.clientCompany = clientCompany;
    }

    public String getClientManagerName() {
        return clientManagerName;
    }

    public void setClientManagerName(String clientManagerName) {
        this.clientManagerName = clientManagerName;
    }

    public String getClientManagerPhone() {
        return clientManagerPhone;
    }

    public void setClientManagerPhone(String clientManagerPhone) {
        this.clientManagerPhone = clientManagerPhone;
    }

    public String getClientManagerSex() {
        return clientManagerSex;
    }

    public void setClientManagerSex(String clientManagerSex) {
        this.clientManagerSex = clientManagerSex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

}
