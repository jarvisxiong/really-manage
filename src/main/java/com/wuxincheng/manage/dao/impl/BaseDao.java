package com.wuxincheng.manage.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author zhangjx
 * @version 1.0
 * @date 2014年1月14日
 * Description: ibatis持久化基础DAO类
 */
@Repository("baseDao")
public class BaseDao extends SqlMapClientDaoSupport {
	
	@Resource
	private SqlMapClient sqlMapClient;
	
	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}
	
}
