package com.wuxincheng.fetch.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxincheng.fetch.util.FetchImageUtil;
import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.model.News;
import com.wuxincheng.manage.util.ConfigHelper;
import com.wuxincheng.manage.util.FileUtil;

@Service("fetchService")
public class FetchService {

	private static Logger logger = LoggerFactory.getLogger(FetchService.class);
	
	private static final int batchSleepMillsec = 2000;
	
	String imgHomePath = ConfigHelper.getInstance().getConfig("imgHomePath", "config.properties");

	@Autowired
	private NewsDao newsDao;

	public void downloadAndProcessSendedIndexImg() {
		List<News> news = newsDao.querySended();
		if (null == news || news.size() < 1) {
			return;
		}
		
		logger.info("开始处理图片 size={}张", news.size());
		
		String fileName = null, localPath = null;
		for (News pojo : news) {
			fileName = pojo.getId()+".jpeg";

			// 下载图片
			try {
				localPath = imgHomePath + pojo.getSettleDate() + File.separator;
				FileUtil.createOrCheckFilePathExist(localPath);
				FetchImageUtil.downloadByURL(pojo.getImgLink(), localPath + fileName);
				logger.info("成功下载一张图片 fileName={}", fileName);
			} catch (Exception e) {
				logger.error("图片下载出现异常 imgLink={}", pojo.getImgLink());
				
				Map<String, Object> updateImg = new HashMap<String, Object>();
				updateImg.put("IMG_LOC_PATH", "logo");
				updateImg.put("id", pojo.getId());
				
				logger.info("换成网站Logo");
				try {
					newsDao.updateImgLocPath(updateImg);
					logger.info("图片更新成功 {}", updateImg);
				} catch (Exception ex) {
					logger.error("图片更新成功出现异常", ex);
				}
				
				continue;
			}
			
			try {
				Thread.sleep(batchSleepMillsec);
			} catch (InterruptedException e) {
			}
			
			// 处理图片
			try {
				File imgFileRes = new File(localPath + fileName);
				File imgFileCut = new File(localPath + "K-" +fileName);
				FetchImageUtil.cutImage(imgFileRes, imgFileCut);
				logger.info("成功截取图片 newFileName={}", "K-" +fileName);
			} catch (Exception e1) {
				logger.error("图片截取出现异常", e1);
				
				Map<String, Object> updateImg = new HashMap<String, Object>();
				updateImg.put("IMG_LOC_PATH", "logo");
				updateImg.put("id", pojo.getId());
				
				logger.info("换成网站Logo");
				try {
					newsDao.updateImgLocPath(updateImg);
					logger.info("图片更新成功 {}", updateImg);
				} catch (Exception e) {
					logger.error("图片更新成功出现异常", e);
				}
				
				continue;
			}
			
			try {
				Thread.sleep(batchSleepMillsec);
			} catch (InterruptedException e) {
			}
			
			// 更新
			Map<String, Object> updateImg = new HashMap<String, Object>();
			updateImg.put("IMG_LOC_PATH", fileName);
			updateImg.put("id", pojo.getId());
			try {
				newsDao.updateImgLocPath(updateImg);
				logger.info("图片更新成功 {}", updateImg);
			} catch (Exception e) {
				logger.error("图片更新成功出现异常", e);
			}
		}
		
		logger.info("结束处理图片");
	}
}
