package com.thinkgem.jeesite.modules.mobile.dailyrecord.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.TCheckBack;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

@MyBatisDao
public interface TCheckBackDao extends CrudDao<TCheckBack> {

    public List<TCheckBack> findRecondlist(TCheckBack tCheckBack);

    public User getUserone(String userid);

    public List<TCheckBack> findgetsendlist(TCheckBack tCheckBack);

}
