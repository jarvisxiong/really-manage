package com.wuxincheng.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.WeChatDao;
import com.wuxincheng.manage.model.WeChat;
import com.wuxincheng.manage.service.WeChatService;

@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {

	@Resource private WeChatDao weChatDao;
	
	@Override
	public List<WeChat> queryAll() {
		return weChatDao.queryAll();
	}

	@Override
	public String checkOpenIdPK(String openId) {
		return weChatDao.checkOpenIdPK(openId);
	}

	@Override
	public void insert(WeChat wechat) {
		weChatDao.insert(wechat);
	}

	@Override
	public WeChat queryByOpenId(String openId) {
		return weChatDao.queryByOpenId(openId);
	}

	@Override
	public void updateState(WeChat wechat) {
		weChatDao.updateState(wechat);		
	}

}
