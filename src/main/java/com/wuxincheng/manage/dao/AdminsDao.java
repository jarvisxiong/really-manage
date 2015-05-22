package com.wuxincheng.manage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.model.Admins;

/**
 * 管理员
 * 
 * @author wuxincheng
 *
 */
@Repository("adminsDao")
public class AdminsDao extends BaseDao {

	public Admins query(Admins admins) {
		return (Admins)this.getSqlMapClientTemplate().queryForObject("Admins.query", admins);
	}

	public void insert(Admins admins) {
		this.getSqlMapClientTemplate().insert("Admins.insert", admins);
	}

	public void update(Admins admins) {
		this.getSqlMapClientTemplate().update("Admins.update", admins);
	}

	public void modifyPwd(Admins admins) {
		this.getSqlMapClientTemplate().update("Admins.modifyPwd", admins);
	}

	@SuppressWarnings("unchecked")
	public List<Admins> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("Admins.queryAll");
	}

}
