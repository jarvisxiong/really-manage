package com.wuxincheng.manage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.WeChatDao;
import com.wuxincheng.manage.model.WeChat;

@Repository("weChatDao")
public class WeChatDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<WeChat> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("WeChat.queryAll");
	}

	public String checkOpenIdPK(String openId) {
		return (String)this.getSqlMapClientTemplate().queryForObject("WeChat.checkOpenIdPK", openId);
	}

	public void insert(WeChat wechat) {
		this.getSqlMapClientTemplate().insert("WeChat.insert", wechat);
	}

	public WeChat queryByOpenId(String openId) {
		return (WeChat)this.getSqlMapClientTemplate().queryForObject("WeChat.queryByOpenId", openId);
	}

	public void updateState(WeChat wechat) {
		this.getSqlMapClientTemplate().insert("WeChat.updateState", wechat);		
	}

	public void updateFetchTime(WeChat wechat) {
		this.getSqlMapClientTemplate().update("WeChat.updateFetchTime", wechat);
	}
	
	public void updateFetchEncry(WeChat wechat) {
		this.getSqlMapClientTemplate().insert("WeChat.updateFetchEncry", wechat);		
	}

}
