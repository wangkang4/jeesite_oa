package com.thinkgem.jeesite.modules.WeChat.util.checkin;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinkgem.jeesite.modules.WeChat.entity.Checkin;
import com.thinkgem.jeesite.modules.WeChat.entity.CheckinMsgResponse;
import com.thinkgem.jeesite.modules.WeChat.util.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CheckinAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckinAPI.class);
	//查询打卡数据（post）   
	private static String checkin_list_url = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata?access_token=ACCESS_TOKEN";  
	
	public static List<Checkin> getAllCheckin(String accessToken,String json){
		CheckinMsgResponse checkinMsgResponse = null;
		String url = checkin_list_url.replace("ACCESS_TOKEN", accessToken);
		//json = "{\"opencheckindatatype\": 3,\"starttime\":1514217600,\"endtime\":1514461717,\"useridlist\":[\"ZhangBingBing\",\"XiongZhengGuo\",\"delymoon\",\"5058f1af8388633f609cadb75a75dc9d\",\"YiZhuXiang\",\"dxq\",\"dawn\"]}";
		JSONObject jsonObject = HttpUtil.sendPost(url,json);
		if (null != jsonObject) { 
	    	int errcode = jsonObject.getIntValue("errcode");
	    	String errmsg = jsonObject.getString("errmsg");
	    	String departmentjson = jsonObject.getString("checkindata");
	    	Gson gson = new Gson();
	    	List<Checkin> ps = gson.fromJson(departmentjson, new TypeToken<List<Checkin>>(){}.getType());
	    	return ps;
	    } 
		return null;
	}
}
