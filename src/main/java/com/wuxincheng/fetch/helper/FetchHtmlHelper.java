package com.wuxincheng.fetch.helper;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wuxincheng.common.util.ParseHtmlTool;

/**
 * 网页抓取帮助类
 * 
 * @author wuxincheng
 *
 */
public class FetchHtmlHelper {

	private static Logger logger = LoggerFactory.getLogger(FetchHtmlHelper.class);
	
	/**
	 * 下载并处理文章首页显示的图片
	 */
	public static Map<String, String> getPraseFetchData(String url) {
		logger.info("开始执行首页图片的选取 url={}", url);

		Map<String, String> parseData = ParseHtmlTool.parse(url);
		parseData.put("img_loc_path", FetchImageHelper.processFetchIndexImg(url));
		
		return parseData;
	}

	public static String getGeneratorId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
