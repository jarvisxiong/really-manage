package com.wuxincheng.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.CommentDao;
import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.model.Comment;
import com.wuxincheng.manage.model.News;
import com.wuxincheng.manage.util.DateUtil;

/**
 * 帖子Service
 * 
 * @author wuxincheng
 *
 */
@Service("newsService")
public class NewsService {

	@Resource private NewsDao newsDao;
	@Resource private CommentDao commentDao;
	
	private String settleDate = DateUtil.getCurrentDate(new Date(), "yyyyMMdd");
	
	public Map<String, Object> queryPager(Map<String, Object> queryParam) {
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

	public News queryNewsById(String newsId) {
		return newsDao.queryNewsById(newsId);
	}

	public void edit(News news) {
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
			news.setState("1"); // 1-不显示, 0-显示
			news.setCreator("2"); // 为默认用户
			news.setReaderCount(0); // 设置文章访问量0
			news.setSettleDate(settleDate);
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

	public void sendNews4App(String newsId) {
		newsDao.sendNews4App(newsId);
		commentDao.sendNews4App(newsId);
	}

	public void delete(Long newsId) {
		// 删除帖子
		newsDao.delete(newsId);
		
		News news = newsDao.queryNewsById(newsId+"");
		// 删除对应的评论
		commentDao.delete(news.getCommentId());
	}

	public void intoDBatch(Long[] newsIds) {
		for (Long newsId : newsIds) {
			newsDao.intoDBatch(newsId+"");
		}
	}

	public void sendBatch(Long[] newsIds) throws Exception {
		for (Long newsId : newsIds) {
			newsDao.sendNews4App(newsId+"");
			commentDao.sendNews4App(newsId+"");
			Thread.sleep(1000);
		}
	}

	public List<News> getNewsByIds(Long[] newsIds) {
		List<News> news = new ArrayList<News>();
		for (long newsId : newsIds) {
			news.add(newsDao.queryNewsById(newsId+""));
		}
		return news;
	}

	public void rollback(Long newsId) {
		newsDao.rollback(newsId+"");
	}
	
}
