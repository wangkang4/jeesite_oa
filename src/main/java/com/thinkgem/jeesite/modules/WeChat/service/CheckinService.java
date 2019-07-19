package com.thinkgem.jeesite.modules.WeChat.service;

import com.thinkgem.jeesite.modules.WeChat.dao.CheckinDao;
import com.thinkgem.jeesite.modules.WeChat.entity.Checkin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckinService {
	
	@Autowired
	private CheckinDao checkinDao;

	public void saveCheckinList(List<Checkin> checkinList) {
		
		checkinDao.saveCheckinList(checkinList);
	}
	
}
