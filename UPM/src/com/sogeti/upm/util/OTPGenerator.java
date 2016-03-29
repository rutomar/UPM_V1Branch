package com.sogeti.upm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The Class OTPGenerator.
 */
@Component
public class OTPGenerator {

	private static Logger LOGGER = LoggerFactory.getLogger(OTPGenerator.class);

	/** The Constant ELIG_ALPHA_SET. */
	private static final String ELIG_ALPHA_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/** The Constant ELIG_NUM_SET. */
	private static final String ELIG_NUM_SET = "1234567890";

	/** The Constant ELIG_CHAR_SET. */
	private static final String ELIG_CHAR_SET = "#?!@$%^&*-";

	/** The Constant OTP_LENGTH. */
	private static final int OTP_LENGTH = 8;

	/**
	 * Generate otp string.
	 *
	 * @return the string
	 */
	public String generateOTPString() {

		StringBuilder randStr = new StringBuilder();

		for (int i = 0; i < OTP_LENGTH; i++) {

			if (i == 1) {
				int number = getSecureRandomNumber(ELIG_NUM_SET);
				char ch = ELIG_NUM_SET.charAt(number);
				randStr.append(ch);
			} else if (i == 4) {
				int number = getSecureRandomNumber(ELIG_CHAR_SET);
				char ch = ELIG_CHAR_SET.charAt(number);
				randStr.append(ch);
			} else {
				int number = getSecureRandomNumber(ELIG_ALPHA_SET);
				char ch = ELIG_ALPHA_SET.charAt(number);
				randStr.append(ch);
			}
		}

		return shuffle(randStr.toString());

	}

	/**
	 * Gets the secure random number.
	 *
	 * @param charSet
	 *            the char set
	 * @return the secure random number
	 */
	private int getSecureRandomNumber(String charSet) {

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(charSet.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	/**
	 * Shuffle.
	 *
	 * @param input
	 *            the input
	 * @return the string
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String shuffle(String input) {

		List<Character> characters = new ArrayList();

		for (char c : input.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());

		while (!characters.isEmpty()) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		LOGGER.info("Generated OTP :" + output.toString());
		return output.toString();
	}

}