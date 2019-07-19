package com.thinkgem.jeesite.modules.tb.companyEmployeesList.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class CompanyEmployeesList extends DataEntity<CompanyEmployeesList> {
    private static final long serialVersionUID = 1L;
    /**
     * 员工id
     **/
    private String id;
    /**
     * 公司id
     **/
    private String company_id;
    /**
     * 部门id
     **/
    private String office_id;
    /**
     * 登录名
     **/
    private String login_name;
    /**
     * 姓名
     **/
    private String Name;
    /**
     * 手机号
     **/
    private String mobile;
    /**
     * 邮箱
     **/
    private String email;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
