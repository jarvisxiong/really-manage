package com.wuxincheng.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.MessageDao;
import com.wuxincheng.manage.model.Message;
import com.wuxincheng.manage.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Resource private MessageDao messageDao;
	
	@Override
	public List<Message> queryAll() {
		return messageDao.queryAll();
	}

}
