package com.thinkgem.jeesite.modules.sys.utils;

import java.util.Date;
import java.util.UUID;

public class CustomerUtils {

	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "").toUpperCase();
		return uuid;
	}
	
	public static Date getNowDate(){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String nowDate = df.format(new Date());
		Date nowDate = new Date();
		return nowDate;
	}
	public static void main(String[] args) {
		System.out.println(CustomerUtils.getNowDate());
		System.out.println(CustomerUtils.getUUID());
	}
}
