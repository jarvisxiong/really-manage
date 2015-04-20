package com.wuxincheng.manage.service;

import com.wuxincheng.manage.exception.ServiceException;
import com.wuxincheng.manage.model.Comment;

public interface CommentService {

	Comment queryCommentByNewsId(String newsId);
	
	public abstract void commentsExpireProcess() throws ServiceException;

}
