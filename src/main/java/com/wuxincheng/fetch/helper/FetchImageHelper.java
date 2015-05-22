package com.wuxincheng.fetch.helper;

import com.wuxincheng.fetch.util.FetchImageUtil;
import com.wuxincheng.manage.util.ConfigHelper;
import com.wuxincheng.manage.util.FileUtil;

public class FetchImageHelper {
	
	private static String imgHomePath = ConfigHelper.getInstance().getConfig("imgHomePath", "config.properties");
	
	public static void process(String imgUrl, String subPath, String fileName) throws Exception{
		String localPath = imgHomePath + subPath;
		FileUtil.createOrCheckFilePathExist(localPath);
		FetchImageUtil.downloadByURL(imgUrl, localPath+fileName);
	}
	
}
