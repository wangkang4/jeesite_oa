/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mobile.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mobile.test.entity.User_M;

import java.util.List;

/**
 * 用户DAO接口
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao_M extends CrudDao<User_M> {

    /**
     * 根据登录名密码 登陆 并返回用户信息
     *
     * @param loginName
     * @return
     */
    public User_M login(User_M user);

    /**
     * 根据登录名称查询用户
     *
     * @param loginName
     * @return
     */
    public User_M getByLoginName(User_M user);

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     *
     * @param entity
     * @return
     */
    public List<User_M> findListMobile(User_M entity);

}
