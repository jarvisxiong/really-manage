package com.wuxincheng.manage.dao;

import java.util.List;
import java.util.Map;

import com.wuxincheng.manage.model.News;

/**
 * 文章信息Dao接口
 * 
 * @author wuxincheng
 *
 */
public interface NewsDao {

	/**
	 * 查询所有文件信息
	 * 
	 * @return
	 */
	public abstract List<News> queryAll();
	
	/**
	 * 分页查询
	 * 
	 * @param start
	 * @param end
	 * @param flag
	 * @return
	 */
	public abstract List<News> queryPager(Map<String, Object> params);
	
	/**
	 * 添加
	 * 
	 * @param blogInfo
	 */
	public abstract void insert(News news);
	
	/**
	 * 查询最大ID值
	 * 
	 * @return
	 */
	public abstract Integer queryMaxId();

	/**
	 * 更新
	 * 
	 * @param blogInfo
	 */
	public abstract void update(News news);
	
	/**
	 * 删除文章
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract Integer delete(Long newsId);

	/**
	 * 统计总记录条数
	 * 
	 * @return
	 */
	public abstract Integer queryCount();
	
	/**
	 * 根据条件统计总记录条数
	 * 
	 * @param queryParam
	 * @return
	 */
	public abstract int queryCountByParams(Map<String, Object> queryParam);

	/**
	 * 查询所有微信DOCID
	 * 
	 * @return
	 */
	public abstract List<String> getAllWeChatDocid();
	
	/**
	 * 根据DOCID查询帖子
	 * 
	 * @param docId
	 * @return
	 */
	public abstract Integer queryNewsIdByDocid(String docId);
	
	/**
	 * 根据主键查询帖子
	 * 
	 * @param newsId
	 * @return
	 */
	public abstract News queryNewsById(String newsId);

	/**
	 * 发布帖子
	 * 
	 * @param newsId
	 */
	public abstract void sendNews4App(String newsId);
	
	/**
	 * 查询到所有过期的帖子
	 * 
	 * @return
	 */
	public abstract List<News> queryExpireNews();

}
