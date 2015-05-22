package com.wuxincheng.fetch.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class FetchImageUtil {

	public static void main(String[] args) {
		try {
			downloadByURL("http://avatar.csdn.net/F/8/4/1_itbiyu.jpg", "/test.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void downloadByURL(String imgUrl, String imgName) throws Exception {
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

}
