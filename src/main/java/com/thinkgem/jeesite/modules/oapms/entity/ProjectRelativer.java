package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class ProjectRelativer extends DataEntity<ProjectRelativer> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 客户ID或者项目ID
     */
    private String Id;
    /**
     * 关联类别 0-客户 1-项目
     */
    private String relativeType;
    /**
     * 员工
     */
    private User employees;
    /**
     * 员工类型
     */
    private String employeesType;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRelativeType() {
        return relativeType;
    }

    public void setRelativeType(String relativeType) {
        this.relativeType = relativeType;
    }

    public User getEmployees() {
        return employees;
    }

    public void setEmployees(User employees) {
        this.employees = employees;
    }

    public String getEmployeesType() {
        return employeesType;
    }

    public void setEmployeesType(String employeesType) {
        this.employeesType = employeesType;
    }

    @Override
    public String toString() {
        return "ProjectRelativer [Id=" + Id + ", relativeType=" + relativeType + ", employees=" + employees
                + ", employeesType=" + employeesType + "]";
    }

}
