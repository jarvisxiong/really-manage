package com.wuxincheng.manage.service;

import java.util.List;

import com.wuxincheng.manage.model.Admins;

/**
 * 管理员
 * 
 * @author wuxincheng
 *
 */
public interface AdminsService {
	
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public abstract List<Admins> queryAll();

	/**
	 * 查询
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract Admins query(String adminsLogin);
	
	/**
	 * 登录查询
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract Admins login(String adminsLogin, String adminsPwd);
	
	/**
	 * 增加
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract void insert(Admins admins);
	
	/**
	 * 更新
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract void update(Admins admins);
	
	/**
	 * 更新
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract void modifyPwd(Admins admins);
	
	/**
	 * 添加
	 * 
	 * @param admins
	 */
	public abstract void edit(Admins admins);
	
}
