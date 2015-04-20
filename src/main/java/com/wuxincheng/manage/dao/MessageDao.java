package com.wuxincheng.manage.dao;

import java.util.List;

import com.wuxincheng.manage.model.Message;

public interface MessageDao {
	
	public abstract List<Message> queryAll();
	
}
