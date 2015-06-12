package com.wuxincheng.walker.htmlunit;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;

import sun.misc.BASE64Encoder;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class HtmlUnitImage {

	public static void main (String args[]) throws IOException {
	    WebClient client = new WebClient(BrowserVersion.FIREFOX_2);
	    Page imagePage = client.getPage("http://i.stack.imgur.com/9DdHc.jpg");
	    BASE64Encoder encoder = new BASE64Encoder();
	    String base64data = encoder.encode(inputStreamToByteArray(imagePage.getWebResponse().getContentAsStream()));
	    System.out.println("<img src=\"data:image/png;base64,"+base64data.replaceAll("\r?\n","")+"\" />");
	}

	private static byte[] inputStreamToByteArray(InputStream is) throws IOException {
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    System.out.println(buffer);
	    int nRead;
	    byte[] data = new byte[16384];
	    while ((nRead = is.read(data, 0, data.length)) != -1) {
	        buffer.write(data, 0, nRead);
	    }
	    buffer.flush();
	    return buffer.toByteArray();
	}
	
}
