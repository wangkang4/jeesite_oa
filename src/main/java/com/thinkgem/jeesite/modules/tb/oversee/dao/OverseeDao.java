package com.thinkgem.jeesite.modules.tb.oversee.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.oversee.entity.Oversee;

import java.util.List;

@MyBatisDao
public interface OverseeDao extends CrudDao<Oversee> {
    List<Oversee> findOverseeList(Oversee oversee);
}
