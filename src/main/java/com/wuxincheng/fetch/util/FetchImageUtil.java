package com.wuxincheng.fetch.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class FetchImageUtil {

	/** 编码 */
	private static final String ECODING = "UTF-8";
	/** 获取img标签正则 */
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	/** 获取src路径的正则 */
	private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";

	/**
	 * 根据图片URL下载图片到本地
	 * 
	 * @param imgUrl
	 * @param imgName
	 *            完整图片路径+图片名称
	 * @throws Exception
	 */
	public static void downloadByURL(String imgUrl, String imgName)
			throws Exception {
		// 构造URL
		URL url = new URL(imgUrl);
		// 打开连接
		URLConnection con = url.openConnection();
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		OutputStream os = new FileOutputStream(imgName);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

	/**
	 * 截取一个图像的中央区域
	 * 
	 * @param image
	 *            图像File
	 * @param cuted
	 *            截取后的File
	 * 
	 * @throws Exception
	 */
	public static void cutImage(File image, File cuted) throws Exception {
		// 判断参数是否合法
		if (null == image) {
			new Exception("哎呀，截图出错！！！");
		}
		InputStream inputStream = new FileInputStream(image);
		// 用ImageIO读取字节流
		BufferedImage bufferedImage = ImageIO.read(inputStream);
		BufferedImage distin = null;

		// 返回源图片的宽度。
		int srcWidth = bufferedImage.getWidth();

		// 返回源图片的高度。
		int srcHeight = bufferedImage.getHeight();
		int x = 0, y = 0;
		int w = 0, h = 0;
		if (srcWidth > srcHeight) {
			w = srcHeight;
			h = srcHeight;
		} else if (srcWidth < srcHeight) {
			w = srcWidth;
			h = srcWidth;
		} else {
			w = srcWidth;
			h = srcWidth;
		}

		// 使截图区域居中
		x = srcWidth / 2 - w / 2;
		y = srcHeight / 2 - h / 2;
		srcWidth = srcWidth / 2 + w / 2;
		srcHeight = srcHeight / 2 + h / 2;
		// 生成图片
		distin = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = distin.getGraphics();
		g.drawImage(bufferedImage, 0, 0, w, h, x, y, srcWidth, srcHeight, null);
		ImageIO.write(distin, "jpg", cuted);
	}

	/**
	 * 验证图片类型
	 * 
	 * @param imageFile
	 * @throws IOException
	 */
	public static String checkImageType(File imageFile) throws IOException {
		// get image format in a file
		File file = imageFile;

		// create an image input stream from the specified file
		ImageInputStream iis = ImageIO.createImageInputStream(file);

		// get all currently registered readers that recognize the image format
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

		if (!iter.hasNext()) {
			throw new RuntimeException("No readers found!");
		}

		// get the first reader
		ImageReader reader = iter.next();

		String formatName = reader.getFormatName();

		// close stream
		iis.close();

		return formatName;
	}

	/**
	 * 获取网页下所有图片的链接
	 * 
	 * @param htmlLink
	 * @return
	 * @throws Exception
	 */
	public static List<String> getHtmlAllImgLink(String htmlLink) throws Exception {
		// 获得html文本内容
		String HTML = getHTML(htmlLink);
		// 获取图片标签
		List<String> imgUrl = getImageUrl(HTML);
		// 获取图片src地址
		List<String> imgSrc = getImageSrc(imgUrl);
		return imgSrc;
	}

	/***
	 * 获取HTML内容
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static String getHTML(String url) throws Exception {
		URL uri = new URL(url);
		URLConnection connection = uri.openConnection();
		InputStream in = connection.getInputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		System.out.println(length);
		StringBuffer sb = new StringBuffer();
		while ((length = in.read(buf, 0, buf.length)) > 0) {
			sb.append(new String(buf, ECODING));
		}
		in.close();
		return sb.toString();
	}

	/***
	 * 获取ImageUrl地址
	 * 
	 * @param HTML
	 * @return
	 */
	private static List<String> getImageUrl(String HTML) {
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);
		List<String> listImgUrl = new ArrayList<String>();
		while (matcher.find()) {
			listImgUrl.add(matcher.group());
		}
		return listImgUrl;
	}

	/***
	 * 获取ImageSrc地址
	 * 
	 * @param listImageUrl
	 * @return
	 */
	private static List<String> getImageSrc(List<String> listImageUrl) {
		List<String> listImgSrc = new ArrayList<String>();
		for (String image : listImageUrl) {
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
			while (matcher.find()) {
				listImgSrc.add(matcher.group().substring(0,
						matcher.group().length() - 1));
			}
		}
		return listImgSrc;
	}

}
