package com.thinkgem.jeesite.modules.mobile.dailyrecord.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.PunchCard;

import java.util.List;

@MyBatisDao
public interface PunchCardDao extends CrudDao<PunchCard> {

    public int insertOnePCard(PunchCard pCard);

    public int updateOnePCard(PunchCard pCard);

    public List<PunchCard> findPunchCards(PunchCard pCard);

    public PunchCard findPunchCard(PunchCard pCard);

}
