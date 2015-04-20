package com.wuxincheng.manage.service;

import java.util.List;

import com.wuxincheng.manage.model.User;

/**
 * 用户信息Dao接口
 * 
 * @author wuxincheng
 *
 */
public interface UserService {

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public abstract List<User> queryAll();

	/**
	 * 修改用户状态
	 * 
	 * @param userId
	 */
	public abstract void modifyState(String userId);

	/**
	 * 查看用户详细信息
	 * 
	 * @param logiName
	 * @return
	 */
	public abstract User queryBylogiName(String logiName);
	
}
