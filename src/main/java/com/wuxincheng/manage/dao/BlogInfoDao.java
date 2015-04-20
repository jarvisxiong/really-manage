package com.wuxincheng.manage.dao;

import java.util.List;
import java.util.Map;

import com.wuxincheng.manage.model.BlogInfo;

/**
 * 博客信息Dao接口
 * 
 * @author wuxincheng
 *
 */
public interface BlogInfoDao {

	/**
	 * 查询所有文件信息
	 * 
	 * @return
	 */
	public abstract List<BlogInfo> queryAll();
	
	/**
	 * 分页查询
	 * 
	 * @param start
	 * @param end
	 * @param flag
	 * @return
	 */
	public abstract List<BlogInfo> queryPager(Map<String, Object> params);
	
	/**
	 * 根据主键查询
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract BlogInfo queryByBlogId(String blogId);
	
	/**
	 * 添加
	 * 
	 * @param blogInfo
	 */
	public abstract void insert(BlogInfo blogInfo);
	
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
	public abstract void update(BlogInfo blogInfo);
	
	/**
	 * 删除博客
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract Integer delete(String blogId);

	/**
	 * 统计总记录条数
	 * 
	 * @return
	 */
	public abstract Integer queryCount();
	
}
