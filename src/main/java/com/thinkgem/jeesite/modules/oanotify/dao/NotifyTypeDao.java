package com.thinkgem.jeesite.modules.oanotify.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oanotify.entity.NotifyType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface NotifyTypeDao extends CrudDao<NotifyType> {

    public List<String> findTypeList(NotifyType notifyType);

    public void addType(NotifyType notifyType);

    public NotifyType getTypeById(@Param("id") String id);

    public void updateNotifyType(NotifyType notifyType);

}
