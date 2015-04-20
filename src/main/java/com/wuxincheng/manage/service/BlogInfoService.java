package com.wuxincheng.manage.service;

import java.util.List;
import java.util.Map;

import com.wuxincheng.manage.model.BlogInfo;

/**
 * 博客信息Service接口
 * 
 * @author wuxincheng
 *
 */
public interface BlogInfoService {
	
	/**
	 * 分页查询
	 * 
	 * @param start
	 * @param end
	 * @param flag
	 * @return
	 */
	public abstract Map<String, Object> queryPager(int start, int end);

	/**
	 * 查询博客信息
	 * 
	 * @return
	 */
	public abstract List<BlogInfo> queryAll();
	
	/**
	 * 添加
	 * 
	 * @param blogInfo
	 */
	public abstract void edit(BlogInfo blogInfo);
	
	/**
	 * 根据主键查询
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract BlogInfo queryByBlogId(String blogId);

	/**
	 * 删除博客
	 * 
	 * @param blogId
	 * @return
	 */
	public abstract boolean delete(String blogId);
	
}
