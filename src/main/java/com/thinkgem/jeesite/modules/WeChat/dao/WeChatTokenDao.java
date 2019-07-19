package com.thinkgem.jeesite.modules.WeChat.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.WeChat.entity.AccessToken;

@MyBatisDao
public interface WeChatTokenDao {

	AccessToken getWeChatAccessToken(String type);

	void saveWeChatAccessToken(AccessToken accessToken);

	void saveCreateTime(AccessToken accessToken);

	AccessToken getCheckinTime();
}
