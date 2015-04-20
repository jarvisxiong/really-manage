package com.wuxincheng.manage.dao;

import java.util.List;

import com.wuxincheng.manage.model.Admins;

/**
 * 管理员Dao
 * 
 * @author wuxincheng
 *
 */
public interface AdminsDao {
	
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public abstract List<Admins> queryAll();

	/**
	 * 查询
	 * 
	 * @param admins
	 * @return
	 */
	public abstract Admins query(Admins admins);
	
	/**
	 * 增加
	 * 
	 * @param admins
	 * @return
	 */
	public abstract void insert(Admins admins);
	
	/**
	 * 更新
	 * 
	 * @param admins
	 * @return
	 */
	public abstract void update(Admins admins);
	
	/**
	 * 更新
	 * 
	 * @param admins
	 * @return
	 */
	public abstract void modifyPwd(Admins admins);
	
}
