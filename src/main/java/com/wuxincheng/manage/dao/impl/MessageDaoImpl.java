package com.wuxincheng.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.MessageDao;
import com.wuxincheng.manage.model.Message;

@Repository("messageDao")
public class MessageDaoImpl extends BaseDao implements MessageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("Message.queryAll");
	}

}
