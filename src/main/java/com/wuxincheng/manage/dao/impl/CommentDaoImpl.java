package com.wuxincheng.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.CommentDao;
import com.wuxincheng.manage.model.Comment;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDao implements CommentDao {

	@Override
	public void insert(Comment comment) {
		this.getSqlMapClientTemplate().insert("Comment.insert", comment);
	}

	@Override
	public void update(Comment comment) {
		this.getSqlMapClientTemplate().insert("Comment.update", comment);
	}

	@Override
	public void sendNews4App(String newsId) {
		this.getSqlMapClientTemplate().delete("Comment.sendNews4App", newsId);
	}

	@Override
	public Comment queryCommentByNewsId(String newsId) {
		return (Comment) this.getSqlMapClientTemplate().queryForObject(
				"Comment.queryCommentByNewsId", newsId);
	}

	@Override
	public Integer delete(Long commentId) {
		return (Integer) this.getSqlMapClientTemplate().delete("Comment.delete", commentId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> queryExpireComments() {
		return this.getSqlMapClientTemplate().queryForList("Comment.queryExpireComments");
	}

}
