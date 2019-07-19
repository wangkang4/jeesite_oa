package com.thinkgem.jeesite.modules.newTrends.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

public class NewTrends extends ActEntity<NewTrends> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /*首页最新动态*/
    /**
     * 实例id
     */
    private String id;
    /**
     * 用户信息
     */
    private User user;
    /**
     * 部门信息
     */
    private Office office;
    /**
     * 发布内容标题
     */
    private String title;
    /**
     * 发布内容信息表id
     */
    private String textId;
    /**
     * 发布内容信息
     */
    private String text;
    /**
     * 发布时间
     */
    private Date date;
    /**
     * 状态
     */
    private String statu;
    /**
     * 附件
     */
    private String address;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
