/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.daysign.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 打卡成功Entity
 *
 * @author ShiLiangYu
 * @version 2017-07-31
 */
public class Udaysign extends DataEntity<Udaysign> {

    private static final long serialVersionUID = 1L;
    private User user; // 用户ID
    private String name; // 用户名
    private String ip; // ip地址
    private String state; // 状态：0代表正常1代表迟到2代表早退
    private Date end; // 下班打卡时间
    private String endState;// 下班打卡状态 1：代表早退

    public Udaysign() {
        super ();
    }

    public Udaysign(String id) {
        super ( id );
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Length(min = 0, max = 64, message = "用户名长度必须介于 0 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 0, max = 100, message = "ip地址长度必须介于 0 和 100 之间")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Length(min = 0, max = 10, message = "状态：0代表正常1代表迟到2代表早退长度必须介于 0 和 10 之间")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

}