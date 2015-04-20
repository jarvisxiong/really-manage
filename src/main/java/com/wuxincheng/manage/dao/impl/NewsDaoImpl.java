package com.wuxincheng.manage.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.model.News;

@Repository("newsDao")
public class NewsDaoImpl extends BaseDao implements NewsDao {
	
	@Override
	public Integer queryNewsIdByDocid(String sogouDocid) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("News.queryNewsIdByDocid", sogouDocid);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllWeChatDocid() {
		return this.getSqlMapClientTemplate().queryForList("News.getAllWechatDocid");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> queryPager(Map<String, Object> params) {
		return this.getSqlMapClientTemplate().queryForList("News.queryPager", params);
	}
	
	@Override
	public int queryCountByParams(Map<String, Object> queryParam) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("News.queryCountByParams", queryParam);
	}
	
	@Override
	public Integer queryCount() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("News.queryCount");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<News> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("News.queryAll");
	}

	@Override
	public void insert(News news) {
		this.getSqlMapClientTemplate().insert("News.insert", news);
	}
	
	@Override
	public Integer queryMaxId() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("News.queryMaxId");
	}

	@Override
	public void update(News news) {
		this.getSqlMapClientTemplate().insert("News.update", news);
	}
	
	@Override
	public Integer delete(Long id) {
		return (Integer) this.getSqlMapClientTemplate().delete("News.delete", id);
	}

	@Override
	public News queryNewsById(String newsId) {
		return (News) this.getSqlMapClientTemplate().queryForObject("News.queryNewsById", newsId);
	}

	@Override
	public void sendNews4App(String newsId) {
		this.getSqlMapClientTemplate().delete("News.sendNews4App", newsId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> queryExpireNews() {
		return this.getSqlMapClientTemplate().queryForList("News.queryExpireNews");
	}

}
