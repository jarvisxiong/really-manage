package com.wuxincheng.manage.util;

public class CharacterUtil {

	public static String replaceQuoteCode(String input) {
		if (input != null && !"".equals(input)) {
			return input.replaceAll("u201c", "“").replaceAll("u201d", "”")
					.replaceAll("u2014", "").replaceAll("u2026", "")
					.replaceAll("u2022", "•").replaceAll("u20221", "");
		}
		return "";
	}

	public static String replaceQuote(String input) {
		StringBuilder output = new StringBuilder(input);
		char c;

		int idxS = 0, idxE = input.length() - 1;
		while (idxS < idxE) {
			c = output.charAt(idxS);
			if (c == '\'') {
				output.setCharAt(idxS, '\u2018');
				while (true) {
					c = output.charAt(idxE);
					if (c == '\'') {
						output.setCharAt(idxE, '\u2019');
						break;
					}
					idxE--;
				}
			}
			idxS++;
		}

		idxS = 0;
		idxE = input.length() - 1;
		while (idxS < idxE) {
			c = output.charAt(idxS);
			if (c == '\"') {
				output.setCharAt(idxS, '\u201c');
				while (true) {
					c = output.charAt(idxE);
					if (c == '\"') {
						output.setCharAt(idxE, '\u201d');
						break;
					}
					idxE--;
				}
			}
			idxS++;
		}

		return output.toString();
	}

}
