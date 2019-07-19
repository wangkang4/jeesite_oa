/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mobile.test.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mobile.test.dao.UserDao_M;
import com.thinkgem.jeesite.modules.mobile.test.entity.User_M;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class UserService_M extends CrudService<UserDao_M, User_M> {

    /**
     * 根据登录名密码 登陆 并返回用户信息
     *
     * @param loginName
     * @return
     */
    public User_M login(String loginName, String password) {

        User_M user = new User_M ();
        user.setLoginName ( loginName );
        user.setPassword ( password );

        return dao.getByLoginName ( user );
    }

    /**
     * 根据登录名称查询用户
     *
     * @param loginName
     * @return
     */
    public User_M getByLoginName(String loginName) {

        return dao.getByLoginName ( new User_M ( null, loginName ) );

    }

    /**
     * 查询所有用户
     *
     * @param loginName
     * @return
     */
    public List<User_M> queryAllUser(int offset, int pageSize) {

        User_M user = new User_M ();

        Page<User_M> page = new Page<User_M> ();

        page.setPageSize ( pageSize );

        page.setOffset ( offset );

        page.setMobilePage ( true );

        user.setPage ( page );

        return dao.findList ( user );

    }

}
