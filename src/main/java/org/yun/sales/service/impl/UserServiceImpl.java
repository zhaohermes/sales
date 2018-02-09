package org.yun.sales.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yun.sales.bean.PageInfo;
import org.yun.sales.common.Constant;
import org.yun.sales.dao.IUserDao;
import org.yun.sales.entity.User;
import org.yun.sales.service.IUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Override
	public User login(String acc, String pwd) {
		return null;
	}

	@Override
	public PageInfo<User> selectUser(int pageNum) {
		PageHelper.startPage(pageNum, Constant.USER_PAGE_SIZE);
		Page<User> page = (Page<User>)userDao.selectUser();
		return page == null ? null : new PageInfo<User>(page);
	}

}
