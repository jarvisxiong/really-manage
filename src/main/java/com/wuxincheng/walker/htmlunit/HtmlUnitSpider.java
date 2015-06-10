package com.wuxincheng.walker.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.wuxincheng.fetch.util.WeiXinFetchTool;
import com.wuxincheng.manage.model.News;

public class HtmlUnitSpider {

	private static Logger logger = LoggerFactory.getLogger(HtmlUnitSpider.class);

	/**
	 * 抓取微信公众号文章
	 * 
	 * @param openid
	 * @param encryData
	 * @param pager
	 * @return
	 */
	public static List<News> wechatParse(String openid, String encryData, int pager) {
		logger.info("HtmlUnit框架启动抓取");
		
		String fetchWechatUrl = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=" + openid
				+ "&eqs=" + encryData + "&ekv=4&page=" + pager + "&t=1433917797189";

		logger.info("抓取请求地址 fetchWechatUrl={}", fetchWechatUrl);
		
		// 新建一个WebClient对象，此对象相当于浏览器
		final WebClient webClient = new WebClient();
		// 构造一个URL,指向需要测试的URL，如http://www.javaeye.com
		URL url = null;
		try {
			url = new URL(fetchWechatUrl);
		} catch (MalformedURLException e) {
			logger.error("链接请求异常", e);
		}

		// 通过getPage()方法，返回相应的页面
		JavaScriptPage page = null;
		try {
			page = (JavaScriptPage) webClient.getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return WeiXinFetchTool.parseData(page.getContent());
	}

	public static void wechatParse() {
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
	    java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
	    
		// 新建一个WebClient对象，此对象相当于浏览器
		final WebClient webClient = new WebClient();
		// 构造一个URL,指向需要测试的URL，如http://www.javaeye.com
		URL url = null;
		try {
			url = new URL(sogouWechatUrl);
		} catch (MalformedURLException e) {
			logger.info("MalformedURLException: ", e.getMessage());
		}

		// 通过getPage()方法，返回相应的页面
		Page page = null;
		try {
			page = webClient.getPage(url);

		} catch (FailingHttpStatusCodeException e) {
			logger.info("FailingHttpStatusCodeException: ", e.getMessage());
		} catch (IOException e) {
			logger.info("IOException: ", e.getMessage());
		}

		System.out.println(page.getWebResponse().getContentType());
		System.out.println(page.getWebResponse().getStatusCode());
		System.out.println(page.getWebResponse().getStatusMessage());
		System.out.println(page.getWebResponse().getResponseBody());
	}

	public static void main(String[] args) {
		wechatParse();
	}

	static String sogouWechatUrl = "http://weixin.sogou.com/gzh?openid=oIWsFt26ylf0J6vXkvs7D-A5T4No";

	static String sogouUrl = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFt74G_meNYl2JeYX1wOyehz4"
			+ "&eqs=NcsUoD%2BgcpABoASFbpuW2ufyPxx1%2Bwok1nMWvPoo%2FGCbHJsK15hDZkuJf8tLE0RmWetF0&ekv=4&page=1&t=1433924521047";

}
