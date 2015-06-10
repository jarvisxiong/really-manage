package com.wuxincheng.fetch.helper;

import com.wuxincheng.common.util.ConfigHelper;
import com.wuxincheng.common.util.FileUtil;
import com.wuxincheng.fetch.util.FetchImageUtil;

public class FetchImageHelper {
	
	private static String imgHomePath = ConfigHelper.getInstance().getConfig("imgHomePath", "config.properties");
	
	public static void process(String imgUrl, String subPath, String fileName) throws Exception{
		String localPath = imgHomePath + "res/" + subPath;
		FileUtil.createOrCheckFilePathExist(localPath);
		FetchImageUtil.downloadByURL(imgUrl, localPath+fileName);
	}
	
}
