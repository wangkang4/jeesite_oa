package com.thinkgem.jeesite.modules.mobile.dailyrecord.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcel;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

@MyBatisDao
public interface CostDao extends CrudDao<CostRecondExcel> {

    public List<CostRecondExcel> findAllCost(CostRecondExcel costRecondExcel);

    public CostRecondExcel getone(String id);

    public User getUser(String id);

}
