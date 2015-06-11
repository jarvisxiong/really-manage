package com.wuxincheng.walker.htmlunit;

import org.junit.Test;

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

}
