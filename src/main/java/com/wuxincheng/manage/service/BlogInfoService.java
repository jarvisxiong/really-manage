package com.wuxincheng.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.BlogInfoDao;
import com.wuxincheng.manage.model.BlogInfo;

/**
 * 博客信息
 * 
 * @author wuxincheng
 *
 */
@Service("blogInfoService")
public class BlogInfoService {
	
	@Resource private BlogInfoDao blogInfoDao;
	
	public Map<String, Object> queryPager(int start, int end) {
		// 返回结果
		Map<String, Object> reault = new HashMap<String, Object>();
		
		// 查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		
		int totalCount = blogInfoDao.queryCount(); // 总记录数
		List<BlogInfo> blogInfos = blogInfoDao.queryPager(params); // 当前页的数据
		
		reault.put("blogInfos", blogInfos);
		reault.put("totalCount", totalCount);
		
		return reault;
	}
	
	public List<BlogInfo> queryAll() {
		return blogInfoDao.queryAll();
	}

	public void edit(BlogInfo blogInfo) {
		if (null == blogInfo) {
			return;
		}
		
		// 图片URL处理, 即从内容中抽取一张图片URL地址
		/**
		String content = blogInfo.getBlogContent(); // 获得博客内容
		String imgURL = CatchImageURLUtil.getFirstImgURLFromContent(content);
		blogInfo.setPicLink(imgURL);
		
		logger.debug("图片地址 imgURL: " + imgURL);
		 */
		
		Integer blogId = blogInfo.getBlogId();
		
		if (blogId != null && !"".equals(blogId)) { // 更新
			blogInfoDao.update(blogInfo);
		} else { // 新增
			blogInfo.setBlogId(blogInfoDao.queryMaxId());
			blogInfoDao.insert(blogInfo);
		}
	}

	public BlogInfo queryByBlogId(String blogId) {
		return blogInfoDao.queryByBlogId(blogId);
	}
	
	public boolean delete(String blogId) {
		boolean deleteFlag = false;
		Integer deleteInt = blogInfoDao.delete(blogId);
		if (deleteInt > 0) {
			deleteFlag = true;
		}
		return deleteFlag;
	}
	
}
