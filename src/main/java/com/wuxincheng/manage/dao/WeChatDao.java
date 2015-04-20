package com.wuxincheng.manage.dao;

import java.util.List;

import com.wuxincheng.manage.model.WeChat;

/**
 * 微信公众号接口
 * 
 * @author wuxincheng
 *
 */
public interface WeChatDao {

	/**
	 * 查询微信公众号信息
	 * 
	 * @return
	 */
	public abstract List<WeChat> queryAll();

	/**
	 * 根据openId查检公众号是否存在
	 * 
	 * @param openId
	 * @return
	 */
	public abstract String checkOpenIdPK(String openId);

	/**
	 * 插入一条新的公众号
	 * 
	 * @param wechat
	 */
	public abstract void insert(WeChat wechat);
	
	/**
	 * 根据openId查询公众信息
	 * 
	 * @param openId
	 * @return
	 */
	public abstract WeChat queryByOpenId(String openId);
	
	/**
	 * 更新状态
	 * 
	 * @param openId
	 */
	public abstract void updateState(WeChat wechat);
	
}
