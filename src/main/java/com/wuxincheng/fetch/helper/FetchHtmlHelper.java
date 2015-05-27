package com.wuxincheng.fetch.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wuxincheng.fetch.util.FetchImageUtil;
import com.wuxincheng.manage.util.ParseHtmlTool;

public class FetchHtmlHelper {

	private static Logger logger = LoggerFactory.getLogger(FetchHtmlHelper.class);
	
	public static void main(String[] args) {
		getData("http://news.163.com/15/0527/14/AQKLV61N0001124J.html");
	}
	
	public static Map<String, String> getData(String url) {
		Map<String, String> parseData = ParseHtmlTool.parse(url);
		
		try {
			List<String> imgs = FetchImageUtil.getHtmlAllImgLink(url);
			List<Long> imgSizes = new ArrayList<Long>();
			Map<Long, Object> imgss = new HashMap<Long, Object>();
			File firstImg = null, secendImg = null, maxFile = null;
			for (int i = 0; i < imgs.size(); i++) {
				logger.info("下载第{}张图片", i+1);
				String imgFile = "C:/imgbase/tmp/"+(i+1);
				// FileUtil.createOrCheckFilePathExist(imgFile);
				FetchImageUtil.downloadByURL(imgs.get(i), imgFile);
				
				File file = new File(imgFile);
				
				logger.info("图片大小为{}", file.length());
				imgSizes.add(file.length());
				
				imgss.put(file.length(), file);
				
				if (i == 0) {
					firstImg = file;
					continue;
				}
				
				if (i == imgs.size()-1) {
					continue;
				}
				
				secendImg = file;
				
				File deleteTmp = null;
				if (firstImg.length() > secendImg.length()) {
					maxFile = firstImg;
					deleteTmp = secendImg;
				} else {
					maxFile = secendImg;
					deleteTmp = firstImg;
				}
				
				deleteTmp.delete();
				firstImg = maxFile;
			}
			
			logger.info("最大的图片大小为{}", maxFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parseData;
	}
	
}
