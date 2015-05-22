package com.wuxincheng.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.UserDao;
import com.wuxincheng.manage.model.User;
import com.wuxincheng.manage.service.UserService;

@Repository("userService")
public class UserService {
	
	@Resource private UserDao userDao;

	public List<User> queryAll() {
		return userDao.queryAll();
	}

	public void modifyState(String userId) {
		// TODO 禁止用户登录
	}

	public User queryBylogiName(String logiName) {
		return userDao.queryBylogiName(logiName);
	}

}
