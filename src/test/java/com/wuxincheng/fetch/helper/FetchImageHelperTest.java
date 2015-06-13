package com.wuxincheng.fetch.helper;

import org.junit.Test;

public class FetchImageHelperTest {

	@Test
	public void testManyImgs() {
		// 下载图片异常
		String url = "http://mp.weixin.qq.com/s?__biz=MjM5MjAxNDM4MA==&mid=217443766&idx=2"
				+ "&sn=678ab77c4d3cb2448774770fb513d930&3rd=MzA3MDU4NTYzMw==&scene=6#rd";
		FetchImageHelper.processFetchIndexImg(url);
	}
	
	@Test
	public void testOneImg() {
		FetchImageHelper.processFetchImage("http://mmbiz.qpic.cn/mmbiz/xrFYciaHL08B6PibjjIB7cJzL1RWYGGWqF3D"
				+ "Pp1UvwuMAOUBpz0flY1zIqicAqyYODWD6M5QYxukyhdK0ZUBfgt5w/0?wx_fmt=jpeg");
	}
	
}
