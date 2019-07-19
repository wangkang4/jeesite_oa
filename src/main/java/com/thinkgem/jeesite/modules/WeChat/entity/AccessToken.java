package com.thinkgem.jeesite.modules.WeChat.entity;

/** 
 * 微信通用接口凭证 
 * @author zhangbingbing
 */  
public class AccessToken {  
    // 获取到的凭证  
    private String accesstoken;  
    // 凭证有效时间，单位：秒  
    private int expiresIn;
    //唯一标识
    private String id;
    //凭证的类型
    private String type;
    //创建时间
    private int createTime;
    
	public String getId() {
		return id;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public int getExpiresIn() {  
        return expiresIn;  
    }  
  
    public void setExpiresIn(int expiresIn) {  
        this.expiresIn = expiresIn;  
    }

	public String toString() {
		return "AccessToken [accesstoken=" + accesstoken + ", expiresIn="
				+ expiresIn + "]";
	}

	
    
}  