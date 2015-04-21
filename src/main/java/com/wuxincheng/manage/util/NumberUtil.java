package com.wuxincheng.manage.util;

import java.util.Random;

public class NumberUtil {

	public static Integer getRandom() {
		// 生成0－2之间的随机数，包括2
		Random rand = new Random();
		// int randNum = rand.nextInt(3);
		// 生成5－26之间的随机数，包括26
		return (rand.nextInt(2500) + 10);
	}
	
	public static void main(String[] args) {
		while(true){
			System.out.println(getRandom());
		}
	}

}
