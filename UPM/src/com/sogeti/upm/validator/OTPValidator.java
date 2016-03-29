package com.sogeti.upm.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sogeti.upm.command.GenerateOTPCommand;

/**
 * The Class OTPValidator.
 */
@Component
public class OTPValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return GenerateOTPCommand.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object command, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "loginId.required", "Login Id is mandatory");

	}

}
