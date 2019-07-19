/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	/**
	 * 查询和微信平台用户用户的对接部分用户信息
	 * @return
	 */
	public List<User> getWechatUserInformation();
	/**
	 * 更新微信平台用户的用户id
	 * @param id
	 * @param userid
	 */
	public void saveWechatId(@Param("id")String id,@Param("userid")String userid);
	/**
	 * 查询用户累计加班天数
	 */
	public double selectNumOverTimeById(User user);
	/**
	 * 更新用户累计加班天数
	 */
	public int updateNumOverTimeById(User user);
	/**
	 * 查询用户可用加班天数
	 */
	public double selectUseOverTimeById(User user);
	/**
	 * 更新用户可用加班天数
	 */
	public int updateUseOverTimeById(User user);
	
	/**
	 * 查询用户总的加班天数
	 */
	public double selectNumLeaveDays(User user);
	
	/**
	 * 更新总的请假天数
	 */
	public int updateNumLeaveDays(User user);

	public List<String> findByRoleEnname(@Param("enname")String enname);
	

}
