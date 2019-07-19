package com.thinkgem.jeesite.modules.mobile.sys.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mobile.sys.dao.UserMobileDao;
import com.thinkgem.jeesite.modules.mobile.sys.entity.UserMobile;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserMobileService extends CrudService<UserMobileDao, UserMobile> {

    @Autowired
    private UserMobileDao userMobileDao;

    /**
     * 根据登录名获取对应的用户信息
     */
    public UserMobile login(String userName) {

        UserMobile user = new UserMobile ();

        user.setLoginName ( userName );

        return userMobileDao.login ( user );

    }

    public List<UserMobile> findMobilelist(int offset, int pageSize) {
        UserMobile user = new UserMobile ();

        Page<UserMobile> page = new Page<UserMobile> ();

        page.setPageSize ( pageSize );

        page.setOffset ( offset );

        page.setMobilePage ( true );

        user.setPage ( page );

        return userMobileDao.findMobileList ( user );
    }

    public List<Office> findMobileOffice() {
        return userMobileDao.findMobileOffice ();
    }

    public String getofficeName(String str1) {
        return userMobileDao.getofficeName ( str1 );
    }

    @Transactional(readOnly = false)
    public void uploadone(UserMobile userMobile) {
        userMobileDao.uploadone ( userMobile );
    }

    public UserMobile findone(String id) {
        return userMobileDao.findone ( id );
    }


}
