package com.wuxincheng.manage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.UserDao;
import com.wuxincheng.manage.model.User;

@Repository("userDao")
public class UserDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<User> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("User.queryAll");
	}

	public void modifyState(String userId) {
		this.getSqlMapClientTemplate().update("User.modifyState", userId);
	}

	public User queryBylogiName(String logiName) {
		return (User)this.getSqlMapClientTemplate().queryForObject("User.queryBylogiName", logiName);
	}
	
}
