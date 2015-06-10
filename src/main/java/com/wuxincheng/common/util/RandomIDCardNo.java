package com.wuxincheng.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 自动生成身份证号
 */
public class RandomIDCardNo {
	
	// 18位身份证号码各位的含义:
	// 1-2位省、自治区、直辖市代码；
	// 3-4位地级市、盟、自治州代码；
	// 5-6位县、县级市、区代码；
	// 7-14位出生年月日，比如19670401代表1967年4月1日；
	// 15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数；
	// 前17位的构成： 2(省)+2(市)+2(县)+4(年)+2(月)+2(日)+2(序号)+1(性别)
	// 第十八位数字的计算方法为：
	// 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
	// 2.将这17位数字和系数相乘的结果相加。
	// 3.用加出来和除以11，看余数是多少？
	// 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。

	public static String generate() {
		String valCodeArr[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
		
		String[] wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5",
                "8", "4", "2" };
		
		// 随机生成省、自治区、直辖市代码 1-2
		String provinces[] = { "11", "12", "13", "14", "15", "21", "22", "23",
				"31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
				"44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
				"63", "64", "65", "71", "81", "82" };
		String province = provinces[new Random().nextInt(provinces.length - 1)];
		// 随机生成地级市、盟、自治州代码 3-4
		String citys[] = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "21", "22", "23", "24", "25", "26", "27", "28" };
		String city = citys[new Random().nextInt(citys.length - 1)];
		// 随机生成县、县级市、区代码 5-6
		String countys[] = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31", "32", "33", "34", "35", "36", "37", "38" };
		String county = countys[new Random().nextInt(countys.length - 1)];
		// 随机生成出生年月 7-14
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE,
				date.get(Calendar.DATE) - new Random().nextInt(365 * 100));
		String birth = dft.format(date.getTime());
		// 随机生成顺序号 15-17
		String no = new Random().nextInt(999) + "";
		
		String ai = province + city + county + birth + no;
		int totalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            totalmulAiWi = totalmulAiWi + Integer.parseInt(String.valueOf(ai.charAt(i)))
                    * Integer.parseInt(wi[i]);
        }
        int modValue = totalmulAiWi % 11;
        String strVerifyCode = valCodeArr[modValue];
        ai = ai + strVerifyCode;

		return ai;
	}

}
