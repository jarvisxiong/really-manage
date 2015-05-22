package com.wuxincheng.manage.util;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * 文件工具类
 *
 * @author	 wuxincheng(wxcking)
 * @version	 V1.0  
 * @Date	 2013年11月26日 上午10:55:18
 */
public class FileUtil {

	private static final Logger logger =Logger.getLogger(FileUtil.class);
	
	/**
	 * 创建文件夹
	 * 
	 * @param filePath
	 */
	public static void createOrCheckFilePathExist(String filePath){
		if (filePath != null && !"".equals(filePath)) {
			File checkFileTemp = new File(filePath);
			if (!checkFileTemp.exists()) {
				// 创建文件夹, 如：在f盘创建/TXT文件夹/testTXT/两个文件夹
				checkFileTemp.mkdirs();
				logger.warn(filePath + "目录不存在, 系统已经自动创建");
			} else {
				
			}
		}
	}
	
}
