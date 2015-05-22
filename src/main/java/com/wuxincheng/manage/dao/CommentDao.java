package com.wuxincheng.manage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.CommentDao;
import com.wuxincheng.manage.model.Comment;

@Repository("commentDao")
public class CommentDao extends BaseDao {

	public void insert(Comment comment) {
		this.getSqlMapClientTemplate().insert("Comment.insert", comment);
	}

	public void update(Comment comment) {
		this.getSqlMapClientTemplate().insert("Comment.update", comment);
	}

	public void sendNews4App(String newsId) {
		this.getSqlMapClientTemplate().delete("Comment.sendNews4App", newsId);
	}

	public Comment queryCommentByNewsId(String newsId) {
		return (Comment) this.getSqlMapClientTemplate().queryForObject(
				"Comment.queryCommentByNewsId", newsId);
	}

	public Integer delete(Long commentId) {
		return (Integer) this.getSqlMapClientTemplate().delete("Comment.delete", commentId);
	}

	@SuppressWarnings("unchecked")
	public List<Comment> queryExpireComments() {
		return this.getSqlMapClientTemplate().queryForList("Comment.queryExpireComments");
	}

}
