package com.wuxincheng.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.UserDao;
import com.wuxincheng.manage.model.User;
import com.wuxincheng.manage.service.UserService;

@Repository("userService")
public class UserServiceImpl implements UserService {
	
	@Resource private UserDao userDao;

	@Override
	public List<User> queryAll() {
		return userDao.queryAll();
	}

	@Override
	public void modifyState(String userId) {
		// TODO 禁止用户登录
	}

	@Override
	public User queryBylogiName(String logiName) {
		return userDao.queryBylogiName(logiName);
	}

}
