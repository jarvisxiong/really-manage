package com.wuxincheng.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.WeChatDao;
import com.wuxincheng.manage.model.WeChat;

@Repository("weChatDao")
public class WeChatDaoImpl extends BaseDao implements WeChatDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<WeChat> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("WeChat.queryAll");
	}

	@Override
	public String checkOpenIdPK(String openId) {
		return (String)this.getSqlMapClientTemplate().queryForObject("WeChat.checkOpenIdPK", openId);
	}

	@Override
	public void insert(WeChat wechat) {
		this.getSqlMapClientTemplate().insert("WeChat.insert", wechat);
	}

	@Override
	public WeChat queryByOpenId(String openId) {
		return (WeChat)this.getSqlMapClientTemplate().queryForObject("WeChat.queryByOpenId", openId);
	}

	@Override
	public void updateState(WeChat wechat) {
		this.getSqlMapClientTemplate().insert("WeChat.updateState", wechat);		
	}

	@Override
	public void updateFetchTime(String publicNO) {
		this.getSqlMapClientTemplate().update("WeChat.updateFetchTime", publicNO);
	}

}
