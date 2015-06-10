package com.wuxincheng.common.util;

import java.util.Random;

/**
 * 银行卡工具
 */
public class BankCardUtil {

	private Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		BankCardUtil bcd = new BankCardUtil();
		System.out.println(bcd.generateRandomCreditCardNumber("6214", 16));
		System.out.println(bcd.generateRandomCreditCardNumber("6227", 19));
	}

	String[] bankCardFirst = { "6225", "6214", "622848", "622700", "622202", "622188", "622262" };

	/**
	 * Generates a random valid credit card number. For more information about
	 * the credit card number generation algorithms and credit card numbers
	 * refer to <a href="http://www.merriampark.com/anatomycc.htm">Anatomy of
	 * Credit Card Numbers</a>, <a
	 * href="http://euro.ecom.cmu.edu/resources/elibrary/everycc.htm">Everything
	 * you ever wanted to know about CC's</a>, <a
	 * href="http://www.darkcoding.net/credit-card/">Graham King's blog</a>, and
	 * <a href=
	 * "http://codytaylor.org/2009/11/this-is-how-credit-card-numbers-are-generated.html"
	 * >This is How Credit Card Numbers Are Generated</a>
	 * 
	 * @param bin
	 *            The bank identification number, a set digits at the start of
	 *            the credit card number, used to identify the bank that is
	 *            issuing the credit card.
	 * @param length
	 *            The total length (i.e. including the BIN) of the credit card
	 *            number.
	 * @return A randomly generated, valid, credit card number.
	 */
	public String generateRandomCreditCardNumber(String bin, int length) {

		// The number of random digits that we need to generate is equal to the
		// total length of the card number minus the start digits given by the
		// user, minus the check digit at the end.
		int randomNumberLength = length - (bin.length() + 1);

		StringBuffer buffer = new StringBuffer(bin);
		for (int i = 0; i < randomNumberLength; i++) {
			int digit = this.random.nextInt(10);
			buffer.append(digit);
		}

		// Do the Luhn algorithm to generate the check digit.
		int checkDigit = this.getCheckDigit(buffer.toString());
		buffer.append(checkDigit);

		return buffer.toString();
	}

	/**
	 * Generates the check digit required to make the given credit card number
	 * valid (i.e. pass the Luhn check)
	 * 
	 * @param number
	 *            The credit card number for which to generate the check digit.
	 * @return The check digit required to make the given credit card number
	 *         valid.
	 */
	private int getCheckDigit(String number) {

		// Get the sum of all the digits, however we need to replace the value
		// of every other digit with the same digit multiplied by 2. If this
		// multiplication yields a number greater than 9, then add the two
		// digits together to get a single digit number.
		//
		// The digits we need to replace will be those in an even position for
		// card numbers whose length is an even number, or those is an odd
		// position for card numbers whose length is an odd number. This is
		// because the Luhn algorithm reverses the card number, and doubles
		// every other number starting from the second number from the last
		// position.
		int sum = 0;
		int remainder = (number.length() + 1) % 2;
		for (int i = 0; i < number.length(); i++) {

			// Get the digit at the current position.
			int digit = Integer.parseInt(number.substring(i, (i + 1)));

			if ((i % 2) == remainder) {
				digit = digit * 2;
				if (digit > 9) {
					digit = (digit / 10) + (digit % 10);
				}
			}
			sum += digit;
		}

		// The check digit is the number required to make the sum a multiple of
		// 10.
		int mod = sum % 10;
		int checkDigit = ((mod == 0) ? 0 : 10 - mod);

		return checkDigit;
	}

	/**
	 * 校验银行卡卡号
	 * 
	 * @param cardId
	 * @return
	 */
	public static boolean checkBankCard(String cardId) {
		char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
		return cardId.charAt(cardId.length() - 1) == bit;
	}

	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 * 
	 * @param nonCheckCodeCardId
	 * @return
	 */
	private static char getBankCardCheckCode(String nonCheckCodeCardId) {
		if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
				|| !nonCheckCodeCardId.matches("\\d+")) {
			throw new IllegalArgumentException("Bank card code must be number!");
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

}
