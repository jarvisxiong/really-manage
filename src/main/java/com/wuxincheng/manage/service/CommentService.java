package com.wuxincheng.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.CommentDao;
import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.exception.ServiceException;
import com.wuxincheng.manage.model.Comment;

@Service("commentService")
public class CommentService {
	
	@Resource private NewsDao newsDao;
	@Resource private CommentDao commentDao;
	
	public Comment queryCommentByNewsId(String newsId) {
		return commentDao.queryCommentByNewsId(newsId);
	}
	
	public void commentsExpireProcess() throws ServiceException {
		System.out.println("");
		
		// 查询所有过期的帖子, 在当前时间的两天前的帖子
		List<Comment> expireComments = commentDao.queryExpireComments();
		if (expireComments != null && expireComments.size() > 0) {
			for (Comment comment : expireComments) {
				newsDao.delete(Long.parseLong(comment.getNewsId()));
				commentDao.delete(comment.getId());
			}
		} else {
		}
	}

}
