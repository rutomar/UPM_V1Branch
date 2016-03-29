package com.sogeti.upm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sogeti.upm.command.UserLoginCommand;
import com.sogeti.upm.service.UPMService;

/**
 * The Class UserLoginValidator.
 */
@Component
public class UserLoginValidator implements Validator {

	/** The upm service. */
	@Autowired
	UPMService upmService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return UserLoginCommand.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object command, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "loginId.required", "Login id is Mandatory.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Password is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "otp", "otp.required", "OTP is Mandatory");

	}

}
