package com.wuxincheng.fetch.helper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wuxincheng.fetch.util.FetchImageUtil;
import com.wuxincheng.manage.util.ConfigHelper;
import com.wuxincheng.manage.util.FileUtil;
import com.wuxincheng.manage.util.ParseHtmlTool;

public class FetchHtmlHelper {

	private static Logger logger = LoggerFactory.getLogger(FetchHtmlHelper.class);
	
	private static String imgHomePath = ConfigHelper.getInstance().getConfig("imgHomePath", "config.properties");
	
	private static final String SEPARATOR = File.separator;
	
	public static void main(String[] args) {
		// System.out.println(getGeneratorId());
		getData("http://news.163.com/15/0527/14/AQKLV61N0001124J.html");
	}
	
	public static Map<String, String> getData(String url) {
		Map<String, String> parseData = ParseHtmlTool.parse(url);
		
		try {
			List<String> imgs = FetchImageUtil.getHtmlAllImgLink(url);
			File firstImg = null, secendImg = null, maxFile = null;
			String imgFilePath = imgHomePath + "manl" + SEPARATOR + getGeneratorId() + SEPARATOR;
			FileUtil.createOrCheckFilePathExist(imgFilePath);
			for (int i = 0; i < imgs.size(); i++) {
				logger.info("下载第{}张图片", i+1);
				imgFilePath = imgFilePath + i;
				FetchImageUtil.downloadByURL(imgs.get(i), imgFilePath);
				
				File file = new File(imgFilePath);
				
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
			
			// 验证这个图片的格式
			String imgType = FetchImageUtil.checkImageType(maxFile);
			// 创建新的带有后缀名的图片
			File imgLast = new File(maxFile.getAbsolutePath() + "." +imgType);
			// 重命名
			maxFile.renameTo(imgLast);
			// 删除原来没有后缀名的图片
			maxFile.delete();
			
			logger.info("最大的图片为{}", imgLast.getAbsolutePath());
			
			parseData.put("news_img_link", imgLast.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parseData;
	}
	
	public static String getGeneratorId(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
