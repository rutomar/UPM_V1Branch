package com.sogeti.upm.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sogeti.upm.command.RegisterUserCommand;

/**
 * The Class RegisterUserValidator.
 */
@Component
public class RegisterUserValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return RegisterUserCommand.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object command, Errors errors) {

		RegisterUserCommand userProfileCommand = (RegisterUserCommand) command;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "loginId.required", "Login id is Mandatory.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Password is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required", "UserName is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "emailId.required", "Email Id is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNo", "houseNo.required", "House No. is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "street.required", "Street is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.required", "City is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "street.required", "State is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "state.required", "State is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "file.required", "Image is Mandatory");

		if (!userProfileCommand.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,10}$")) {
			errors.rejectValue("password", "password.characters",
					"Password should contain atleast 1 Number, 1 Uppercase Letter & 1 Lowercase Letter.");
		}

		if (userProfileCommand.getFile() != null && userProfileCommand.getFile().getSize() > 153600) {
			errors.rejectValue("file", "file.size.big", "File should be less than 150 Kb.");
		}

		if (userProfileCommand.getFile() != null
				&& !userProfileCommand.getFile().getOriginalFilename().contains(".gif")) {
			errors.rejectValue("file", "file.type.gif", "Upload only gif format.");
		}

		if (userProfileCommand.getState() == 0) {
			errors.rejectValue("state", "state.select", "State is mandatory.");
		}

		try {
			Integer.parseInt(userProfileCommand.getHouseNo());
		} catch (NumberFormatException e) {

			errors.rejectValue("houseNo", "houseNo.number", "House No. must be a number.");
		}

	}
}