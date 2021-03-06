package org.yun.sales.service;

import org.yun.sales.bean.PageInfo;
import org.yun.sales.entity.User;

public interface IUserService {

	/**
	 * 登录
	 * @param acc 账号
	 * @param pwd 密码
	 * @return 登录成功返回User对象，否则返回Null
	 * @exception Exception 数据库操作失败或者
	 */
	User login(String acc, String pwd) throws Exception;
	
	/**
	 * 查询用户分页
	 * @param pageNum 页码
	 * @return 用户分页
	 */
	PageInfo<User> selectUser(int pageNum);
	
}
