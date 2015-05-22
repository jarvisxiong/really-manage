package com.wuxincheng.fetch.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.model.News;

@Service("fetchService")
public class FetchService {
	
	private static Logger logger = LoggerFactory.getLogger(FetchService.class);
	
	@Autowired private NewsDao newsDao;

	public void downloadAndProcessSendedIndexImg(){
		List<News> news = newsDao.querySended();
		logger.info("news={}", news);
	}
	
}
