package com.wuxincheng.manage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.model.BlogInfo;

@Repository("blogInfoDao")
public class BlogInfoDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<BlogInfo> queryPager(Map<String, Object> params) {
		return this.getSqlMapClientTemplate().queryForList("BlogInfo.queryPager", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlogInfo> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("BlogInfo.queryAll");
	}

	public void insert(BlogInfo blogInfo) {
		this.getSqlMapClientTemplate().insert("BlogInfo.insert", blogInfo);
	}

	public Integer queryCount() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("BlogInfo.queryCount");
	}
	
	public Integer queryMaxId() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("BlogInfo.queryMaxId");
	}

	public BlogInfo queryByBlogId(String blogId) {
		return (BlogInfo) this.getSqlMapClientTemplate().queryForObject("BlogInfo.queryByBlogId", blogId);
	}

	public void update(BlogInfo blogInfo) {
		this.getSqlMapClientTemplate().insert("BlogInfo.update", blogInfo);
	}
	
	public Integer delete(String blogId) {
		return (Integer) this.getSqlMapClientTemplate().delete("BlogInfo.delete", blogId);
	}

}
