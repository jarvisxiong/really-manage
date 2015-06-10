package com.wuxincheng.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HTML相关的正则表达式工具类, 包括过滤HTML标记, 转换HTML标记, 替换特定HTML标记
 */
public class HtmlRegexpUtil {

	public static void main(String[] args) {
		System.out.println(html2Text("你是<img src=/>&nbsp;我也是"));
	}

	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;

		Pattern p_html1;
		Matcher m_html1;

		try {
			// 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; 
			// 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; 
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		
		textStr = textStr.replaceAll("&nbsp;", "");
		return textStr; // 返回文本字符串
	}
}
