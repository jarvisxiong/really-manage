package com.wuxincheng.fetch.helper;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wuxincheng.common.util.ConfigHelper;
import com.wuxincheng.common.util.DateUtil;
import com.wuxincheng.common.util.FileUtil;
import com.wuxincheng.fetch.util.FetchImageUtil;

/**
 * 页面图片抓取图片帮助类
 */
public class FetchImageHelper {
	private static Logger logger = LoggerFactory.getLogger(FetchImageHelper.class);

	private static String imgHomePath = ConfigHelper.getInstance().getConfig("imgHomePath",
			"config.properties");

	private static final String SEPARATOR = File.separator;

	private static final String FETCH_SUB_PATH = "manl";

	public static void process(String imgUrl, String subPath, String fileName) throws Exception {
		String localPath = imgHomePath + "res/" + subPath;
		FileUtil.createOrCheckFilePathExist(localPath);
		FetchImageUtil.downloadByURL(imgUrl, localPath + fileName);
	}

	/**
	 * 根据网页链接下载并处理图片
	 * 
	 * @param url 网页链接
	 * @return
	 */
	public static String processFetchIndexImg(String url) {
		logger.info("开始执行首页图片的选取 url={}", url);

		// -- 1. 获取网页中最大的图片：

		File firstImg = null, secendImg = null, maxFile = null;
		try {
			// 获取网页中所有的图片链接
			List<String> imgs = FetchImageUtil.getHtmlAllImgLink(url);

			// 如果网页中没有图片, 默认显示网站的Logo图片
			if (null == imgs || imgs.size() < 1) {
				logger.warn("没有抓取到图片");
				return "logo";
			}

			logger.debug("imgs size={}", imgs.size());

			String imgFileBasePath = imgHomePath + FETCH_SUB_PATH + SEPARATOR
					+ DateUtil.getCurrentDate(new Date(), "yyyyMMdd") + SEPARATOR
					+ getGeneratorId() + SEPARATOR;
			// 检查目录是否存在
			FileUtil.createOrCheckFilePathExist(imgFileBasePath);

			// 图片下载网页中的图片并选取出最大的图片
			for (int i = 0; i < imgs.size(); i++) {
				logger.debug("下载第{}张待选择图片", i + 1);

				String imgFilePath = imgFileBasePath + i;
				FetchImageUtil.downloadByURL(imgs.get(i), imgFilePath);

				logger.debug("成功下载待选择图片 {}", imgs.get(i));

				File file = new File(imgFilePath);

				// 第一张图片下载后不作处理
				if (i == 0) {
					firstImg = file;
					continue;
				}

				secendImg = file;
				
				System.out.println("图片大小: " + secendImg.length());

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

				// 最后一张图片结束操作
				if (i >= imgs.size() - 1) {
					break;
				}
				
				// 如果图片大小超过3000返回
				if (firstImg.length() > 10000) {
					break;
				}
				
				// Thread.sleep(2000);
			}

			logger.debug("网页中最大的图片已经成功获取");
		} catch (Exception e) {
			logger.error("下载图片异常", e);
			// return "logo";
		}

		// -- 2. 验证图片的格式：
		File imgLast = null;
		try {
			// 验证这个图片的格式
			String imgType = FetchImageUtil.checkImageType(maxFile);
			logger.info("验证这个图片的格式 imgType={}", imgType);
			// 创建新的带有后缀名的图片
			imgLast = new File(maxFile.getAbsolutePath() + "." + imgType);
			// 重命名
			maxFile.renameTo(imgLast);
			// 删除原来没有后缀名的图片
			maxFile.delete();
		} catch (IOException e) {
			logger.error("验证图片格式异常", e);
		}

		// -- 3. 截取图像：
		String saveLastImgPath = null;
		try {
			FetchImageUtil.cutImage(imgLast, imgLast);
			logger.debug("截取图像成功");

			String imgLastImgPath = imgLast.getAbsolutePath();
			logger.debug("选取的首页图片为 {}", imgLastImgPath);
			int firstIndex = imgLastImgPath.indexOf(FETCH_SUB_PATH);
			saveLastImgPath = imgLastImgPath.substring(firstIndex, imgLastImgPath.length());

			logger.debug("选取的首页图片最终引用路径为 {}", saveLastImgPath);

			logger.info("首页图片的选取结束");
		} catch (Exception e) {
			logger.error("截取图像异常", e);
		}

		return saveLastImgPath;
	}

	/**
	 * 根据图片链接下载并处理图片
	 * 
	 * @param imageLink 图片链接
	 * @return
	 */
	public static String processFetchImage(String imageLink) {
		logger.info("开始执行图片的抓取 imageLink={}", imageLink);

		// -- 1. 获取网页中最大的图片：

		File imageFile = null;
		try {
			String imgFileBasePath = imgHomePath + FETCH_SUB_PATH + SEPARATOR
					+ DateUtil.getCurrentDate(new Date(), "yyyyMMdd") + SEPARATOR
					+ getGeneratorId() + SEPARATOR;
			// 检查目录是否存在
			FileUtil.createOrCheckFilePathExist(imgFileBasePath);

			// 图片下载
			logger.debug("开始下载图片");

			String imgFilePath = imgFileBasePath + "000";
			FetchImageUtil.downloadByURL(imageLink, imgFilePath);

			File thisImageFile = new File(imgFilePath);
			
			logger.debug("成功下载待选择图片 {}", thisImageFile);
			imageFile = thisImageFile;
		} catch (Exception e) {
			logger.error("下载图片异常", e);
		}

		// -- 2. 验证图片的格式：
		File imgLast = null;
		try {
			// 验证这个图片的格式
			String imgType = FetchImageUtil.checkImageType(imageFile);
			logger.info("验证这个图片的格式 imgType={}", imgType);
			// 创建新的带有后缀名的图片
			imgLast = new File(imageFile.getAbsolutePath() + "." + imgType);
			// 重命名
			imageFile.renameTo(imgLast);
			// 删除原来没有后缀名的图片
			imageFile.delete();
		} catch (IOException e) {
			logger.error("验证图片格式异常", e);
		}

		// -- 3. 截取图像：
		String saveLastImgPath = null;
		try {
			FetchImageUtil.cutImage(imgLast, imgLast);
			logger.debug("截取图像成功");

			String imgLastImgPath = imgLast.getAbsolutePath();
			logger.debug("选取的首页图片为 {}", imgLastImgPath);
			int firstIndex = imgLastImgPath.indexOf(FETCH_SUB_PATH);
			saveLastImgPath = imgLastImgPath.substring(firstIndex, imgLastImgPath.length());

			logger.debug("选取的首页图片最终引用路径为 {}", saveLastImgPath);

			logger.info("首页图片的选取结束");
		} catch (Exception e) {
			logger.error("截取图像异常", e);
		}

		return saveLastImgPath;
	}
	
	/**
	 * 获取随机串
	 */
	private static String getGeneratorId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
