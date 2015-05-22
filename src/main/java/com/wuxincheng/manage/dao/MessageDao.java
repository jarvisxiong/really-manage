package com.wuxincheng.manage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.MessageDao;
import com.wuxincheng.manage.model.Message;

@Repository("messageDao")
public class MessageDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Message> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("Message.queryAll");
	}

}
