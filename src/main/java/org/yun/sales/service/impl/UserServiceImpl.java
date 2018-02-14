package org.yun.sales.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yun.sales.bean.PageInfo;
import org.yun.sales.common.Constant;
import org.yun.sales.dao.IUserDao;
import org.yun.sales.entity.User;
import org.yun.sales.service.IUserService;
import org.yun.sales.utils.Tools;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Override
	public User login(String acc, String pwd) throws Exception {
		User user = userDao.selectUserByAcc(acc);
		if(user == null){
			return null;
		}else{
			pwd = Tools.MD5(user.getId() + pwd);
			return user.getPwd().equals(pwd) ? user : null;
		}
	}

	@Override
	public PageInfo<User> selectUser(int pageNum) {
		PageHelper.startPage(pageNum, Constant.USER_PAGE_SIZE);
		Page<User> page = (Page<User>)userDao.selectUser();
		return page == null ? null : new PageInfo<User>(page);
	}

}
