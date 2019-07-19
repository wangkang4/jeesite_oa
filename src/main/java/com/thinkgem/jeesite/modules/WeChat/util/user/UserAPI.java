package com.thinkgem.jeesite.modules.WeChat.util.user;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinkgem.jeesite.modules.WeChat.entity.UserEntity;
import com.thinkgem.jeesite.modules.WeChat.entity.UserEntityMsgResponse;
import com.thinkgem.jeesite.modules.WeChat.util.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserAPI {
	private static final Logger logger = LoggerFactory.getLogger(UserAPI.class);
	//查询部门用户数据（post）   
	private static String user_list_url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";
	//查询部门用户详细数据（GET）
	private static String userinformation_url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";
	
	public static List<UserEntity> getAllCheckin(String accessToken){
		logger.info("[CREATEDEPARTMENT]", "createDepartment param:accessToken:{},menu:{}", new Object[]{accessToken});
		UserEntityMsgResponse userEntityMsgResponse = null;
		String url = user_list_url.replace("ACCESS_TOKEN", accessToken).replace("DEPARTMENT_ID","1").replace("FETCH_CHILD", "1");
		JSONObject jsonObject = HttpUtil.sendPost(url);
		logger.info("[CREATEDEPARTMENT]", "createDepartment response:{}", new Object[]{jsonObject.toJSONString()});
		if (null != jsonObject) { 
	    	int errcode = jsonObject.getIntValue("errcode");
	    	String errmsg = jsonObject.getString("errmsg");
	    	String departmentjson = jsonObject.getString("userlist");
	    	Gson gson = new Gson();
	    	List<UserEntity> ps = gson.fromJson(departmentjson, new TypeToken<List<UserEntity>>(){}.getType());
	    	return ps;
	    } 
		return null;
	}
	
	public static List<UserEntity> getAllUserInformation(String accessToken){
		logger.info("[CREATEDEPARTMENT]", "createDepartment param:accessToken:{},menu:{}", new Object[]{accessToken});
		String url = userinformation_url.replace("ACCESS_TOKEN", accessToken).replace("DEPARTMENT_ID","1").replace("FETCH_CHILD", "1");
		JSONObject jsonObject = HttpUtil.sendPost(url);
		logger.info("[CREATEDEPARTMENT]", "createDepartment response:{}", new Object[]{jsonObject.toJSONString()});
		if (null != jsonObject) { 
	    	int errcode = jsonObject.getIntValue("errcode");
	    	String errmsg = jsonObject.getString("errmsg");
	    	String departmentjson = jsonObject.getString("userlist");
	    	Gson gson = new Gson();
	    	List<UserEntity> ps = gson.fromJson(departmentjson, new TypeToken<List<UserEntity>>(){}.getType());
	    	return ps;
	    } 
		return null;
	}
	
}
