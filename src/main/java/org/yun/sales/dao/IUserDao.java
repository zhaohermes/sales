package org.yun.sales.dao;

import java.util.List;

import org.yun.sales.entity.User;

public interface IUserDao {

	/**
	 * 添加用户
	 * @param user 用户对象
	 */
	void insertUser(User user);
	
	/**
	 * 根据账号查询用户
	 * @param acc 账号
	 * @return 用户对象
	 */
	User selectUserByAcc(String acc);
	
	/**
	 * 查询所有用户
	 * @return 用户列表
	 */
	List<User> selectUser();
}
