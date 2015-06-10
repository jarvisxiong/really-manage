package com.wuxincheng.fetch.helper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wuxincheng.common.util.ConfigHelper;
import com.wuxincheng.common.util.FileUtil;
import com.wuxincheng.common.util.ParseHtmlTool;
import com.wuxincheng.fetch.util.FetchImageUtil;

public class FetchHtmlHelper {

	private static Logger logger = LoggerFactory.getLogger(FetchHtmlHelper.class);

	private static String imgHomePath = ConfigHelper.getInstance().getConfig("imgHomePath",
			"config.properties");

	private static final String SEPARATOR = File.separator;

	private static final String FETCH_SUB_PATH = "manl";

	public static Map<String, String> getData(String url) {
		logger.info("开始执行首页图片的选取 url={}", url);

		Map<String, String> parseData = ParseHtmlTool.parse(url);
		logger.debug("已获取部分数据");

		try {
			List<String> imgs = FetchImageUtil.getHtmlAllImgLink(url);

			if (null == imgs || imgs.size() < 1) {
				logger.warn("没有抓取到图片");
				parseData.put("img_loc_path", "logo");
				logger.info("抓取解析部分数据 parseData={}", parseData);
				return parseData;
			}

			logger.debug("imgs size={}", imgs.size());
			
			File firstImg = null, secendImg = null, maxFile = null;
			String imgFileBasePath = imgHomePath + FETCH_SUB_PATH + SEPARATOR + getGeneratorId()
					+ SEPARATOR;
			// 检查目录是否存在
			FileUtil.createOrCheckFilePathExist(imgFileBasePath);
			for (int i = 0; i < imgs.size(); i++) {
				logger.debug("下载第{}张待选择图片", i + 1);

				String imgFilePath = imgFileBasePath + i;
				FetchImageUtil.downloadByURL(imgs.get(i), imgFilePath);

				logger.debug("成功下载待选择图片 {}", imgs.get(i));

				File file = new File(imgFilePath);

				if (i == 0) {
					firstImg = file;
					continue;
				}

				if (i >= imgs.size()) {
					break;
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
			logger.info("验证这个图片的格式 imgType={}", imgType);
			// 创建新的带有后缀名的图片
			File imgLast = new File(maxFile.getAbsolutePath() + "." + imgType);
			// 重命名
			maxFile.renameTo(imgLast);
			// 删除原来没有后缀名的图片
			maxFile.delete();

			// 截取图像
			FetchImageUtil.cutImage(imgLast, imgLast);
			logger.debug("截取图像成功");

			String imgLastImgPath = imgLast.getAbsolutePath();
			logger.debug("选取的首页图片为 {}", imgLastImgPath);
			int firstIndex = imgLastImgPath.indexOf(FETCH_SUB_PATH);
			String saveLastImgPath = imgLastImgPath.substring(firstIndex, imgLastImgPath.length());

			// parseData.put("news_img_link", saveLastImgPath);
			parseData.put("img_loc_path", saveLastImgPath);
			logger.debug("选取的首页图片最终引用路径为 {}", saveLastImgPath);

			logger.info("抓取解析后的数据 parseData={}", parseData);
		} catch (Exception e) {
			logger.error("下载处理首页图片异常", e);
			parseData.put("img_loc_path", "logo");
		}

		logger.info("首页图片的选取结束");

		return parseData;
	}

	public static String getGeneratorId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
