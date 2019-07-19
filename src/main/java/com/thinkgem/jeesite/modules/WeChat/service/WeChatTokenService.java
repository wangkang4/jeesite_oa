package com.thinkgem.jeesite.modules.WeChat.service;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.WeChat.dao.WeChatTokenDao;
import com.thinkgem.jeesite.modules.WeChat.entity.AccessToken;
import com.thinkgem.jeesite.modules.WeChat.util.base.JwAccessTokenAPI;
import com.thinkgem.jeesite.modules.WeChat.util.base.JwParamesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WeChatTokenService {

	@Autowired
	private WeChatTokenDao weChatTokenDao;
	
	public AccessToken GetWeChatAccessToken(String type){
		AccessToken accessToken = null;
		accessToken = weChatTokenDao.getWeChatAccessToken(type);
		if(accessToken != null){
			long NowData = new Date().getTime()/1000;
			long createTime = accessToken.getCreateTime();
			if(NowData<createTime){
				return accessToken;
			}
			else{
				accessToken = JwAccessTokenAPI.getAccessToken(JwParamesAPI.corpId,type);
				weChatTokenDao.saveWeChatAccessToken(accessToken);
				return accessToken;
			}
		}else{
			accessToken = JwAccessTokenAPI.getAccessToken(JwParamesAPI.corpId,type);
			weChatTokenDao.saveWeChatAccessToken(accessToken);
			return accessToken;
		}
	}

	public int saveCreateTime() {
		long NowData = (new Date().getTime())/1000;
	    int createTime = (int) NowData;
	    AccessToken accessTokenTime = weChatTokenDao.getCheckinTime();
	    int getTime = accessTokenTime.getCreateTime();
	    if(createTime - getTime > 86400){
	    	int a = (createTime - getTime)/86400;
	    	getTime = getTime+a*86400;
	    	AccessToken accessToken = new AccessToken();
		    accessToken.setCreateTime(getTime);
		    accessToken.setId(IdGen.uuid());
		    weChatTokenDao.saveCreateTime(accessToken);
	    }
	    
	    return getTime;
	}

	public AccessToken getCheckinTime() {
		return weChatTokenDao.getCheckinTime();
	}

}
