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
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class FetchImageUtil {

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
	
	public static void checkImageType(File imageFile) throws IOException {
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

        System.out.println("Format: " + reader.getFormatName());

        // close stream
        iis.close();
    }
	
}
