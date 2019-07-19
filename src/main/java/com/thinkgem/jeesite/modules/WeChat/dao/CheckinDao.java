package com.thinkgem.jeesite.modules.WeChat.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.WeChat.entity.Checkin;

import java.util.List;

@MyBatisDao
public interface CheckinDao {

	void saveCheckinList(List<Checkin> checkinList);

}
