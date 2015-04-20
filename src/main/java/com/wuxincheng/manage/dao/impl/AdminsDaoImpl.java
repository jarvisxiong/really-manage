package com.wuxincheng.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.AdminsDao;
import com.wuxincheng.manage.model.Admins;

/**
 * 管理员
 * 
 * @author wuxincheng
 *
 */
@Repository("adminsDao")
public class AdminsDaoImpl extends BaseDao implements AdminsDao {

	@Override
	public Admins query(Admins admins) {
		return (Admins)this.getSqlMapClientTemplate().queryForObject("Admins.query", admins);
	}

	@Override
	public void insert(Admins admins) {
		this.getSqlMapClientTemplate().insert("Admins.insert", admins);
	}

	@Override
	public void update(Admins admins) {
		this.getSqlMapClientTemplate().update("Admins.update", admins);
	}

	@Override
	public void modifyPwd(Admins admins) {
		this.getSqlMapClientTemplate().update("Admins.modifyPwd", admins);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admins> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("Admins.queryAll");
	}

}
