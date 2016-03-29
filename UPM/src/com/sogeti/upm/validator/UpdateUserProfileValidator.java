package com.sogeti.upm.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sogeti.upm.command.UpdateUserProfileCommand;

/**
 * The Class UpdateUserProfileValidator.
 */
@Component
public class UpdateUserProfileValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return UpdateUserProfileCommand.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object command, Errors errors) {

		UpdateUserProfileCommand userProfilecommand = (UpdateUserProfileCommand) command;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required", "UserName is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "emailId.required", "Email Id is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNo", "houseNo.required", "House No. is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "street.required", "Street is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.required", "City is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "street.required", "State is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "state.required", "State is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "file.required", "Image is Mandatory");

		if (userProfilecommand.getFile() != null && userProfilecommand.getFile().getSize() > 153600) {
			errors.rejectValue("file", "file.size.big", "File should be less than 150 Kb.");
		}

		if (userProfilecommand.getFile() != null
				&& !userProfilecommand.getFile().getOriginalFilename().contains(".gif")) {
			errors.rejectValue("file", "file.type.gif", "Upload only gif format.");
		}

		try {
			Integer.parseInt(userProfilecommand.getHouseNo());
		} catch (NumberFormatException e) {

			errors.rejectValue("houseNo", "houseNo.number", "House No. must be a number.");
		}

	}
}