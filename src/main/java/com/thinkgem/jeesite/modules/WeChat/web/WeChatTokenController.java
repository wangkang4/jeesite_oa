package com.thinkgem.jeesite.modules.WeChat.web;

import com.thinkgem.jeesite.modules.WeChat.entity.AccessToken;
import com.thinkgem.jeesite.modules.WeChat.entity.Checkin;
import com.thinkgem.jeesite.modules.WeChat.entity.UserEntity;
import com.thinkgem.jeesite.modules.WeChat.service.CheckinService;
import com.thinkgem.jeesite.modules.WeChat.service.WeChatTokenService;
import com.thinkgem.jeesite.modules.WeChat.util.checkin.CheckinAPI;
import com.thinkgem.jeesite.modules.WeChat.util.user.UserAPI;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @ClassName:@WeChatTokenController.java
 * @Descriptopn:（获取进入凭证access_token）
 * @author zhangbingbing
 * @date @2017年12月27日
 */
@Controller
@RequestMapping(value="${adminPath}/wechat/token")
public class WeChatTokenController{
	
	@Autowired
	private WeChatTokenService weChatTokenService;
	@Autowired
	private CheckinService checkinService;
	@Autowired
	private SystemService systemService;
	
	
	@RequestMapping("/sign")
	@ResponseBody
	public Map GetSignInformation(String type){
		type = "sign";
		Map<String, String> map = new HashMap<String, String>();
		AccessToken chechinAccessToken = weChatTokenService.GetWeChatAccessToken(type);
		String Usertype = "userList";
		AccessToken userAccessToken = weChatTokenService.GetWeChatAccessToken(Usertype);
		List<UserEntity> userList = UserAPI.getAllCheckin(userAccessToken.getAccesstoken());
		String a = "[";
		for(int i=0;i<userList.size();i++){
			if(i<userList.size()-1){
				a+="\""+userList.get(i).getUserid()+"\",";
			}else{
				a+="\""+userList.get(i).getUserid()+"\"]";
			}
		}
		AccessToken checkintime = weChatTokenService.getCheckinTime();
		int starttime = checkintime.getCreateTime();
		int endtime = weChatTokenService.saveCreateTime();
		String json = "{\"opencheckindatatype\": 3,\"starttime\":"+starttime+",\"endtime\":"+endtime+",\"useridlist\":"+a+"}";
		List<Checkin> checkinList = CheckinAPI.getAllCheckin(chechinAccessToken.getAccesstoken(),json);
		if(checkinList!=null&&checkinList.size()>0){
			checkinService.saveCheckinList(checkinList);
			map.put("result", "success");
		}else{
			map.put("result", "error");
		}
		
		return map;
	}
	@ResponseBody
	@RequestMapping("/updataWechatUserid")
	public Map<String,String> UpdataUserInformation(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", "no");
		String Usertype = "userList";
		AccessToken userAccessToken = weChatTokenService.GetWeChatAccessToken(Usertype);
		List<UserEntity> userInformation = UserAPI.getAllUserInformation(userAccessToken.getAccesstoken());
		List<User> userList = systemService.getWechatUserInformation();
		
		for(int i=0;i<userInformation.size();i++){
			System.out.println(userInformation.get(i).getName());
			for(int j=0;j<userList.size();j++){
				if(null == userList.get(j).getWechetid()){
					if(userInformation.get(i).getMobile().equals(userList.get(j).getPhone())&&!"".equals(userList.get(j).getPhone())){
						System.out.println(userList.get(j).getId()+"=========="+userInformation.get(i).getUserid());
						systemService.saveWechatId(userList.get(j).getId(),userInformation.get(i).getUserid());
						map.put("message", "have");
					}
				}
			}
		}
		return map;
	}
}
