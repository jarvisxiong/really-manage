package com.wuxincheng.manage.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * @author zhangjx
 * @date 2014年7月14日 上午12:30:57
 */
public class NewsParser {
	private static final String DEFAULT_CHARSET = "UTF-8";

	@Deprecated
	public static Map<String, String> parse(String url) {
		Map<String, String> data = new HashMap<String, String>();
		String charset = _getCharset(url);
		if(charset == null) {
			charset = DEFAULT_CHARSET;
		}
		Parser parser = new Parser();
		try {
			parser.setURL(url);
			parser.setEncoding(charset);
			NodeFilter filter = new TagNameFilter("title");
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			String title = null;
			for(int i = 0; i < nodes.size(); i++) {
				TitleTag titleNode = (TitleTag)nodes.elementAt(i);
				title = titleNode.getTitle();
				if(StringUtils.isNotBlank(title)) {
					break;
				}
			}
			title = title.trim();
			data.put("news_title", title);
			data.put("news_title_sub", title);
			data.put("news_image_link", title);
		} catch (ParserException e) {
			return null;
		}
		return data;
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		Map data = parse(url);
		System.out.println("fetch data: " + data);
	}
	
	private static String _getCharset(String url) {
		String charset = null;
		Parser parser = new Parser();
		try {
			parser.setURL(url);
			parser.setEncoding(DEFAULT_CHARSET);
			NodeFilter filter = new NodeClassFilter() {

				private static final long serialVersionUID = 1L;

				public boolean accept(Node node) {
					if(node instanceof MetaTag) {
						MetaTag mt = (MetaTag)node;
						if(mt.getAttribute("http-equiv") != null) {
							if(mt.getAttribute("content").contains("charset")) {
								return true;
							}
						} else if(mt.getAttribute("charset") != null) {
							return true;
						} else {
							return false;
						}
					}
					return false;
				}
			};
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			for(int i = 0; i < nodes.size(); i++) {
				MetaTag mt = (MetaTag)nodes.elementAt(i);
				if(mt.getAttribute("content") != null) {
					charset = mt.getAttribute("content").toLowerCase().split("charset=")[1];
				} else {
					charset = mt.getAttribute("charset");
				}
			}
		} catch (ParserException e) {
		}
		return charset;
	}
}
