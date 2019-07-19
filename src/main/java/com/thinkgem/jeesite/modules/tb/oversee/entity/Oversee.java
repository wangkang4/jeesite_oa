package com.thinkgem.jeesite.modules.tb.oversee.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Oversee extends DataEntity<Oversee> {
    private static final long serialVersionUID = 1L;
    /**
     * 部门
     **/
    private String officeName;
    /**
     * 员工id
     **/
    private String userId;
    /**
     * 登录名
     **/
    private String loginName;
    /**
     * 姓名
     **/
    private String userName;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 角色
     **/
    private String role;
    /**
     * 手机号
     **/
    private String phone;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
