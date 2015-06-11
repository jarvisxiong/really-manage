package com.wuxincheng.walker.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * HtmlUnit框架测试
 */
public class HtmlUnitTest {

	static String sogouWechatUrl = "http://weixin.sogou.com/gzh?openid=oIWsFt26ylf0J6vXkvs7D-A5T4No";

	static String sogouUrl = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFt74G_meNYl2JeYX1wOyehz4"
			+ "&eqs=NcsUoD%2BgcpABoASFbpuW2ufyPxx1%2Bwok1nMWvPoo%2FGCbHJsK15hDZkuJf8tLE0RmWetF0&ekv=4&page=1&t=1433924521047";

	@Test
	public void testWechatParse() {
		HtmlUnitSpider.wechatParse(sogouWechatUrl);
	}

	@Test
	public void testGoogle() throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		WebClient webClient = new WebClient();

		webClient.setThrowExceptionOnScriptError(false);

		HtmlPage currentPage = (HtmlPage) webClient.getPage("http://wuxincheng.com.cn");
		System.out.println(currentPage.getTitleText());

		String textSource = currentPage.asText();
		System.out.println(textSource);

		String xmlSource = currentPage.asXml();
		System.out.println(xmlSource);

		// Response status
		currentPage.getWebResponse().getStatusCode();
		currentPage.getWebResponse().getStatusMessage();

		System.out.println("");

		// Response headers
		System.out.println("============ headers: ");
		@SuppressWarnings("unchecked")
		List<NameValuePair> headers = currentPage.getWebResponse().getResponseHeaders();
		for (NameValuePair header : headers) {
			System.out.println(header.getName() + " = " + header.getValue());
		}

		System.out.println("");

	}

}
