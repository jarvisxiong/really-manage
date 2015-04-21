package com.wuxincheng.manage.service;

import java.util.List;
import java.util.Map;

import com.wuxincheng.manage.model.News;

/**
 * 信息Service接口
 * 
 * @author wuxincheng
 *
 */
public interface NewsService {
	
	/**
	 * 分页查询
	 * 
	 * @param start
	 * @param end
	 * @param flag
	 * @return
	 */
	public abstract Map<String, Object> queryPager(Map<String, Object> queryParam);

	/**
	 * 根据ID查询帖子
	 * 
	 * @param newsId
	 * @return
	 */
	public abstract News queryNewsById(String newsId);

	/**
	 * 编辑帖子
	 * 
	 * @param news
	 */
	public abstract void edit(News news);

	/**
	 * 发布帖子
	 * 
	 * @param newsId
	 */
	public abstract void sendNews4App(String newsId);

	/**
	 * 删除帖子
	 * 
	 * @param newsId
	 */
	public abstract void delete(Long newsId);
	
	public abstract void intoDBatch(Long[] newsIds);

	public abstract void sendBatch(Long[] newsIds);

	public abstract List<News> getNewsByIds(Long[] newsIds);

	public abstract void rollback(Long newsId);

}
