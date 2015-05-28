package com.wuxincheng.manage.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 保存网页到本地，相当于浏览器中的另存为 
 */
public class HtmlSaveParser {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFt0x7a3NaZl4pICKnoXFlLYg&eqs=Qqs/oBWg9wh5ojqWUbyGbuXTjxsA8u/VGOfHISQd8eHygCSizxIGK3hl3pfjSTOAo42HT&ekv=4&page=1&t=1432778392495&repp=1");
			URLConnection urlConn = url.openConnection();
			System.out.println("Host: " + url.getHost());
			System.out.println("Port: " + url.getDefaultPort());
			System.out.println("ContentType: " + urlConn.getContentType());
			System.out.println("ContentLength: " + urlConn.getContentLength());
			InputStream is = urlConn.getInputStream();
			FileOutputStream fos = new FileOutputStream("d:/weixincon.html");
			int data;
			while ((data = is.read()) != -1) {
				fos.write(data);
			}
			is.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
