package com.sogeti.upm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sogeti.upm.command.UserLoginCommand;
import com.sogeti.upm.validator.OTPValidator;

/**
 * The Class GenerateOTPController.
 */
@Controller
@SessionAttributes("users")
@RequestMapping("/")
public class GenerateOTPController {

	private static Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

	/** The otp validator. */
	@Autowired
	OTPValidator otpValidator;

	/**
	 * Inits the binder.
	 *
	 * @param binder
	 *            the binder
	 */
	@InitBinder("otpValidator")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(otpValidator);
	}

	/**
	 * Show user login.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String showUserLogin(ModelMap model) {
		LOGGER.debug("Showing User Login Screen.");
		model.addAttribute("userLoginCommand", new UserLoginCommand());
		return "userLogin";
	}

}
