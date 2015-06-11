package com.wuxincheng.walker.htmlunit;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;

public class HtmlUnitHelper {

	public static void main(String[] args) {
		WebClient webClient = new WebClient();
		String content = null;
		try {
			Page page = webClient.getPage("http://weixin.sogou.com/gzh?openid=oIWsFt-IP2110PQUFXh_f71Gd5KQ");
			WebResponse response = page.getWebResponse();
			content = response.getContentAsString();
			System.out.println(response.getContentType());
			response.getResponseHeaderValue("");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(content);
		}
	}
}
