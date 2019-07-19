package com.thinkgem.jeesite.modules.mobile.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mobile.sys.entity.UserMobile;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import java.util.List;

@MyBatisDao
public interface UserMobileDao extends CrudDao<UserMobile> {

    /**
     * 根据user获取其他信息
     */
    public UserMobile login(UserMobile user);

    public List<UserMobile> findMobileList(UserMobile user);

    public List<Office> findMobileOffice();

    public String getofficeName(String str1);

    public void uploadone(UserMobile userMobile);

    public UserMobile findone(String id);

}
