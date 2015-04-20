package com.wuxincheng.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.UserDao;
import com.wuxincheng.manage.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("User.queryAll");
	}

	@Override
	public void modifyState(String userId) {
		this.getSqlMapClientTemplate().update("User.modifyState", userId);
	}

	@Override
	public User queryBylogiName(String logiName) {
		return (User)this.getSqlMapClientTemplate().queryForObject("User.queryBylogiName", logiName);
	}
	
}
