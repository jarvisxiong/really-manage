package com.wuxincheng.manage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.model.News;

@Repository("newsDao")
public class NewsDao extends BaseDao {
	
	public Integer queryNewsIdByDocid(String sogouDocid) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("News.queryNewsIdByDocid", sogouDocid);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllWeChatDocid() {
		return this.getSqlMapClientTemplate().queryForList("News.getAllWechatDocid");
	}

	@SuppressWarnings("unchecked")
	public List<News> queryPager(Map<String, Object> params) {
		return this.getSqlMapClientTemplate().queryForList("News.queryPager", params);
	}
	
	public int queryCountByParams(Map<String, Object> queryParam) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("News.queryCountByParams", queryParam);
	}
	
	public Integer queryCount() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("News.queryCount");
	}
	
	@SuppressWarnings("unchecked")
	public List<News> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("News.queryAll");
	}

	public void insert(News news) {
		this.getSqlMapClientTemplate().insert("News.insert", news);
	}
	
	public Integer queryMaxId() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("News.queryMaxId");
	}

	public void update(News news) {
		this.getSqlMapClientTemplate().insert("News.update", news);
	}
	
	public Integer delete(Long id) {
		return (Integer) this.getSqlMapClientTemplate().delete("News.delete", id);
	}

	public News queryNewsById(String newsId) {
		return (News) this.getSqlMapClientTemplate().queryForObject("News.queryNewsById", newsId);
	}

	public void sendNews4App(String newsId) {
		this.getSqlMapClientTemplate().delete("News.sendNews4App", newsId);
	}

	@SuppressWarnings("unchecked")
	public List<News> queryExpireNews() {
		return this.getSqlMapClientTemplate().queryForList("News.queryExpireNews");
	}

	public void intoDBatch(String newsId) {
		this.getSqlMapClientTemplate().update("News.intoDBatch", newsId);
	}

	public void rollback(String newsId) {
		this.getSqlMapClientTemplate().update("News.rollback", newsId);
	}

	@SuppressWarnings("unchecked")
	public List<News> querySended() {
		return this.getSqlMapClientTemplate().queryForList("News.querySended");
	}

	public void updateImgLocPath(Map<String, Object> updateImg) {
		this.getSqlMapClientTemplate().update("News.updateImgLocPath", updateImg);
	}

}
