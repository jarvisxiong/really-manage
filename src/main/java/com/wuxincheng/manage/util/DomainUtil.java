package com.wuxincheng.manage.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 域名工具类
 */
public class DomainUtil {

	/**
	 * 获取链接的域名
	 * 
	 * @param url
	 *            链接
	 * @return 域名 (www.blog.csdn.com.cn)
	 */
	public static String getSubDomainName(String url) {
		String simpleDomainName = "";
		try {
			Pattern p = Pattern
					.compile(
							"(?<=http\\://)(?:[^.\\s]*?\\.)+(com|cn|net|org|biz|info|cc|tv)",
							Pattern.CASE_INSENSITIVE);
			Matcher matcher = p.matcher(url);
			matcher.find();
			simpleDomainName = matcher.group();
		} catch (Exception e) {
		}

		return simpleDomainName.replaceFirst("www.", "");
	}

	/**
	 * 获取链接的域名
	 * 
	 * @param url
	 *            链接
	 * @return 域名
	 */
	public static String getSimpleDomainName(String url) {
		String simpleDomainName = "";
		try {
			Pattern p = Pattern.compile(
					"(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = p.matcher(url);
			matcher.find();
			simpleDomainName = matcher.group();
		} catch (Exception e) {
		}

		return simpleDomainName;
	}

}
