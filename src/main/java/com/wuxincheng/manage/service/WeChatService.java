package com.wuxincheng.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.WeChatDao;
import com.wuxincheng.manage.model.WeChat;
import com.wuxincheng.manage.service.WeChatService;

@Service("weChatService")
public class WeChatService {

	@Resource private WeChatDao weChatDao;
	
	public List<WeChat> queryAll() {
		return weChatDao.queryAll();
	}

	public String checkOpenIdPK(String openId) {
		return weChatDao.checkOpenIdPK(openId);
	}

	public void insert(WeChat wechat) {
		weChatDao.insert(wechat);
	}

	public WeChat queryByOpenId(String openId) {
		return weChatDao.queryByOpenId(openId);
	}

	public void updateState(WeChat wechat) {
		weChatDao.updateState(wechat);		
	}
	
	public void updateFetchTime(WeChat wechat) {
		weChatDao.updateFetchTime(wechat);
	}
	
	public void updateFetchEncry(WeChat wechat) {
		weChatDao.updateFetchEncry(wechat);
	}

}
