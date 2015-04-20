package com.wuxincheng.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.BlogInfoDao;
import com.wuxincheng.manage.model.BlogInfo;
import com.wuxincheng.manage.service.BlogInfoService;

/**
 * 博客信息
 * 
 * @author wuxincheng
 *
 */
@Service("blogInfoService")
public class BlogInfoServiceImpl implements BlogInfoService {
	
	private static Logger logger = LoggerFactory.getLogger(BlogInfoServiceImpl.class);

	@Resource private BlogInfoDao blogInfoDao;
	
	@Override
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
	
	@Override
	public List<BlogInfo> queryAll() {
		return blogInfoDao.queryAll();
	}

	@Override
	public void edit(BlogInfo blogInfo) {
		if (null == blogInfo) {
			logger.warn("博客信息为空blogInfo");
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

	@Override
	public BlogInfo queryByBlogId(String blogId) {
		return blogInfoDao.queryByBlogId(blogId);
	}
	
	@Override
	public boolean delete(String blogId) {
		boolean deleteFlag = false;
		Integer deleteInt = blogInfoDao.delete(blogId);
		if (deleteInt > 0) {
			deleteFlag = true;
		}
		return deleteFlag;
	}
	
}
