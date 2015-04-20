package com.wuxincheng.manage.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.BlogInfoDao;
import com.wuxincheng.manage.model.BlogInfo;

@Repository("blogInfoDao")
public class BlogInfoDaoImpl extends BaseDao implements BlogInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogInfo> queryPager(Map<String, Object> params) {
		return this.getSqlMapClientTemplate().queryForList("BlogInfo.queryPager", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BlogInfo> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("BlogInfo.queryAll");
	}

	@Override
	public void insert(BlogInfo blogInfo) {
		this.getSqlMapClientTemplate().insert("BlogInfo.insert", blogInfo);
	}

	@Override
	public Integer queryCount() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("BlogInfo.queryCount");
	}
	
	@Override
	public Integer queryMaxId() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("BlogInfo.queryMaxId");
	}

	@Override
	public BlogInfo queryByBlogId(String blogId) {
		return (BlogInfo) this.getSqlMapClientTemplate().queryForObject("BlogInfo.queryByBlogId", blogId);
	}

	@Override
	public void update(BlogInfo blogInfo) {
		this.getSqlMapClientTemplate().insert("BlogInfo.update", blogInfo);
	}
	
	@Override
	public Integer delete(String blogId) {
		return (Integer) this.getSqlMapClientTemplate().delete("BlogInfo.delete", blogId);
	}

}
