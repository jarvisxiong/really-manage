package com.wuxincheng.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.CommentDao;
import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.model.Comment;
import com.wuxincheng.manage.model.News;
import com.wuxincheng.manage.service.NewsService;

/**
 * 帖子Service
 * 
 * @author wuxincheng
 *
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {
	private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Resource private NewsDao newsDao;
	@Resource private CommentDao commentDao;
	
	@Override
	public Map<String, Object> queryPager(Map<String, Object> queryParam) {
		logger.info("查询所有博客信息");
		
		// 返回结果
		Map<String, Object> reault = new HashMap<String, Object>();
		
		// 查询条件
		// int start, int end
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("start", start);
		// params.put("end", end);
		
		int totalCount = newsDao.queryCountByParams(queryParam); // 总记录数
		List<News> news = newsDao.queryPager(queryParam); // 当前页的数据
		
		reault.put("news", news);
		reault.put("totalCount", totalCount);
		
		return reault;
	}

	@Override
	public News queryNewsById(String newsId) {
		logger.info("根据主键查询帖子, newsId: " + newsId);
		
		return newsDao.queryNewsById(newsId);
	}

	@Override
	public void edit(News news) {
		logger.info("编辑帖子");
		
		if (news.getId() != null && !"".equals(news.getId())) { // 更新
			newsDao.update(news);
			Comment comment = new Comment();
			comment.setNewsId(news.getId()+"");
			comment.setState(news.getState());
			comment.setContent(news.getComment());
			commentDao.update(comment);
		} else { // 新增
			String mockDocid = UUID.randomUUID()+"";
			news.setSogouDocid(mockDocid); // 这个ID不是来自搜狗微信搜索
			// news.setState("1"); // 1-不显示, 0-显示
			news.setCreator("2"); // 为默认用户
			newsDao.insert(news);
			
			int newsid = newsDao.queryNewsIdByDocid(mockDocid);
			Comment comment = new Comment();
			comment.setNewsId(newsid+"");
			comment.setContent(news.getComment());
			comment.setBackground(2014000000);
			comment.setAlpha(-1);
			comment.setCreator("2");
			comment.setState(news.getState()); // 1-不显示, 0-显示
			commentDao.insert(comment);
		}
	}

	@Override
	public void sendNews4App(String newsId) {
		logger.info("发布帖子");
		
		newsDao.sendNews4App(newsId);
		commentDao.sendNews4App(newsId);
	}

	@Override
	public void delete(Long newsId) {
		logger.info("删除帖子");
		
		// 删除帖子
		newsDao.delete(newsId);
		
		News news = newsDao.queryNewsById(newsId+"");
		// 删除对应的评论
		commentDao.delete(news.getCommentId());
	}
	
}
