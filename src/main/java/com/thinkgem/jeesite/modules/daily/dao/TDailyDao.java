package com.thinkgem.jeesite.modules.daily.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.daily.entity.TDaily;

import java.util.List;

/**
 * 日报Dao
 *
 * @author ShiLiangYu
 * @version 2017-8-2
 */
@MyBatisDao
public interface TDailyDao extends CrudDao<TDaily> {

    public TDaily getByTDaily(TDaily tDaily);

    public int update(TDaily tDaily);

    public List<TDaily> getMobileDaily(TDaily tDaily);

    public String getofficeName(String str1);
}
