package com.sogeti.upm.util;

import org.springframework.stereotype.Component;

@Component
public class UPMConstantsUtil {

	// views
	public static final String VIEW_USER_LOGIN = "userLogin";
	public static final String VIEW_USER_PROFILE = "userProfile";
	public static final String VIEW_REGISTER_USER = "registerUser";
	public static final String VIEW_GENERATE_OTP = "generateOTP";

	// forms
	public static final String FORM_USER_LOGIN = "userLoginCommand";
	public static final String FORM_USER_PROFILE = "updateUserProfileCommand";
	public static final String FORM_REGISTER_USER = "registerUserCommand";
	public static final String FORM_GENERATE_OTP = "otpCommand";

	// Global Attributes

	public static final String USERS = "users";
	public static final String STATES = "states";
}
