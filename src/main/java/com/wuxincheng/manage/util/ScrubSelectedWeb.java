package com.wuxincheng.manage.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基本能实现网页抓取，不过要手动输入URL 将整个html内容保存到指定文件
 */
public class ScrubSelectedWeb {

	private final static String CRLF = System.getProperty("line.separator");

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String urlInfo = "http://weixin.sogou.com/gzh?openid=oIWsFt5HJEgGlbxXAB2hBcmwjQho";
		getInfoFromUrl(urlInfo, null);
	}

	public static String getInfoFromUrl(String urlInfo, String encoding)
			throws IOException {
		String method = "GET";
		int timeout = 10000;
		URL url = new URL(urlInfo);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setConnectTimeout(timeout);
		conn.setDoOutput(true);
		InputStream urlStream = conn.getInputStream();
		String encoding2 = encoding;
		if (encoding2 == null) {
			String contentType = conn.getContentType();
			System.out.println(("contentType:" + contentType));
			encoding2 = getEncoding(contentType);
			if (encoding2 == null) {
				String encodingRegex = "<meta \\s*http-equiv=\"?content-type\"? |<meta'>\\s*content=\"?(.*?)\"?\\s*/?>|<meta\\s*content=\"?(.*?)\"? |<meta'>\\s*http-equiv=\"?content-type\"?\\s*/?>|<meta\\s*charset=\"?(.*?)\"?\\s*/?>";
				encoding2 = getEncoding(getInfo(encodingRegex,
						getInfoFromUrl(urlInfo, "UTF-8"), 3));
				if (encoding2 == null) {
					encoding2 = "GBK";
				}
			}
		}
		System.out.println("encoding2:" + encoding2);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlStream, encoding2));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(CRLF);
		}
		if (urlStream != null) {
			urlStream.close();
		}
		conn.disconnect();
		System.out.println(buffer.toString());
		return buffer.toString();
	}

	private static String getInfo(String regex, String html, int group) {
		if (regex == null || html == null) {
			return null;
		}
		Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
				.matcher(html);
		StringBuffer encodingInfo = new StringBuffer("");
		boolean found = false;
		while (matcher.find()) {
			for (int i = 1; i <= group; i++) {
				String str = matcher.group(i);
				if (str != null) {
					encodingInfo.append(str);
				}
			}
			encodingInfo.append("   ");
			found = true;
		}
		if (found) {
			System.out.println("encodingInfo:" + encodingInfo.toString());
			return encodingInfo.toString();
		} else {
			return null;
		}
	}

	private static String getEncoding(String encodingInfo) {
		String encoding = null;
		if (encodingInfo != null) {
			String temp = encodingInfo.toLowerCase();
			if (temp.contains("gb2312") || temp.contains("gbk")) {
				encoding = "GBK";
			} else if (temp.contains("utf-8")) {
				encoding = "UTF-8";
			} else if (temp.contains("iso-8859-1")) {
				encoding = "ISO-8859-1";
			}
		}
		return encoding;
	}
}
