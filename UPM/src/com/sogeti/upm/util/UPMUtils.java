package com.sogeti.upm.util;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class UPMUtils {

	  private static SecureRandom random = new SecureRandom();

	  public static String generateUserOTP() {
		    return new BigInteger(130, random).toString(32);
	  }

}
