package com.wuxincheng.manage.util;

import java.util.Random;

/**
 * 自动生成手机号 
 */
public class RandomMobilePhoneNo {
	
	private static final String[] tel_head = new String[] { "13", "18", "15" };

	public static String generate() {
		Random random = new Random();
		StringBuilder uName = new StringBuilder();
		uName.append(
				tel_head[random.nextInt(Integer.MAX_VALUE) % tel_head.length])
				.append(random.nextInt(Integer.MAX_VALUE) % 10)
				.append(random.nextInt(Integer.MAX_VALUE) % 10)
				.append(random.nextInt(Integer.MAX_VALUE) % 10)
				.append(random.nextInt(Integer.MAX_VALUE) % 10)
				.append(random.nextInt(Integer.MAX_VALUE) % 10)
				.append(random.nextInt(Integer.MAX_VALUE) % 10)
				.append(random.nextInt(Integer.MAX_VALUE) % 10)
				.append(random.nextInt(Integer.MAX_VALUE) % 10)
				.append(random.nextInt(Integer.MAX_VALUE) % 10);
		return uName.toString();
	}
	
}
