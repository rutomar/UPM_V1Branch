package com.sogeti.upm.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sogeti.upm.data.User;

/**
 * The Class UPMUtils.
 */
@Component
public class UPMUtils {

	public static final String COUNTRY = "IN";

	private UPMUtils() {
		// defualt contructor
	}

	/**
	 * Checks if is empty.
	 *
	 * @param string
	 *            the string
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String string) {

		if (string == null) {
			return true;
		} else if (string.length() == 0) {
			return true;
		}

		return false;
	}

	/**
	 * Gets the valid user.
	 *
	 * @param loginId
	 *            the login id
	 * @param model
	 *            the model
	 * @return the valid user
	 */
	public static User isExistingUser(String loginId, List<User> users) {

		if (users != null) {
			for (User user : users) {
				if (loginId.equals(user.getLoginID())) {
					return user;
				}
			}
		}
		return null;

	}
}
