package com.wuxincheng.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.CommentDao;
import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.exception.ServiceException;
import com.wuxincheng.manage.model.Comment;
import com.wuxincheng.manage.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	private static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

	@Resource private NewsDao newsDao;
	@Resource private CommentDao commentDao;
	
	@Override
	public Comment queryCommentByNewsId(String newsId) {
		return commentDao.queryCommentByNewsId(newsId);
	}
	
	@Override
	public void commentsExpireProcess() throws ServiceException {
		System.out.println("");
		logger.info("----------- 帖子过期定时任务处理开始:");
		
		// 查询所有过期的帖子, 在当前时间的两天前的帖子
		List<Comment> expireComments = commentDao.queryExpireComments();
		if (expireComments != null && expireComments.size() > 0) {
			logger.info("已经查询到"+expireComments.size()+"条待删除的帖子");
			logger.info("--- ---");
			for (Comment comment : expireComments) {
				newsDao.delete(Long.parseLong(comment.getNewsId()));
				commentDao.delete(comment.getId());
			}
			logger.info("已经删除"+expireComments.size()+"条帖子");
		} else {
			logger.info("没有查询到当前时间的两天前未发布的帖子");
		}
		
		logger.info("----------- 帖子过期定时任务处理结束.");
		System.out.println("");
	}

}
