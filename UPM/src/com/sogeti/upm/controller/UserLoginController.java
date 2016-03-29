package com.sogeti.upm.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sogeti.upm.command.GenerateOTPCommand;
import com.sogeti.upm.command.UserLoginCommand;
import com.sogeti.upm.command.UserProfileCommand;
import com.sogeti.upm.data.User;
import com.sogeti.upm.data.UserOTP;
import com.sogeti.upm.service.UPMService;
import com.sogeti.upm.util.OTPGenerator;
import com.sogeti.upm.util.UPMUtils;
import com.sogeti.upm.validator.UserLoginValidator;

/**
 * The Class UserLoginController.
 */
@Controller
@RequestMapping("/")
@SessionAttributes({ "users", "states" })
public class UserLoginController {

	private static Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
	/** The upm service. */
	@Autowired
	UPMService upmService;

	/** The upm utils. */
	@Autowired
	UPMUtils upmUtils;

	/** The otp generator. */
	@Autowired
	OTPGenerator otpGenerator;

	/** The login validator. */
	@Autowired
	UserLoginValidator loginValidator;

	/**
	 * Inits the binder.
	 *
	 * @param binder
	 *            the binder
	 */
	@InitBinder("userLoginCommand")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}

	/**
	 * Welcome user.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomeUser(ModelMap model) {
		LOGGER.debug("Welcome User");
		model.addAttribute("users", upmService.getAllUsers());
		model.addAttribute("states", upmService.getAllStates());
		model.addAttribute("userLoginCommand", new UserLoginCommand());
		return "userLogin";
	}

	/**
	 * User login.
	 *
	 * @param userLoginCommand
	 *            the user login command
	 * @param bindingResult
	 *            the binding result
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/processForm", params = "login", method = RequestMethod.POST)
	public String userLogin(@Validated @ModelAttribute("userLoginCommand") UserLoginCommand userLoginCommand,
			BindingResult bindingResult, ModelMap model) {
		LOGGER.debug("Login User");
		try {
			// if any attributes are empty , return back to login page
			if (bindingResult.hasErrors()) {
				model.addAttribute("userLoginCommand", userLoginCommand);
				return "userLogin";
			}

			// Validate the authenticity of the user & OTP generation Tmstmp
			User user = getValidUser(userLoginCommand.getLoginId(), model);

			// if User is Authenticate go to the User Profile Page
			if (isUserAuthentic(user, userLoginCommand, bindingResult)) {
				// delete the old UserOTP
				LOGGER.debug("User is Authentic.");
				upmService.deleteUserOTP(user);
				// set the attributes to redirect to UserProfile Page
				model.addAttribute("loginId", user.getLoginID());
				// updated Users Data
				model.addAttribute("users", upmService.getAllUsers());
				return "redirect:/userProfile";
			}

			model.addAttribute("userLoginCommand", userLoginCommand);
		} catch (Exception e) {
			model.addAttribute("userLoginCommand", userLoginCommand);
			LOGGER.error(e.getMessage());
		}

		return "userLogin";

	}

	/**
	 * Generate otp.
	 *
	 * @param loginId
	 *            the login id
	 * @param model
	 *            the model
	 * @return the model and view
	 */
	@RequestMapping(value = "/generateOTP", method = RequestMethod.GET)
	public ModelAndView generateOTP(@RequestParam String loginId, ModelMap model) {

		ModelAndView modelView = new ModelAndView();

		if (loginId == null || loginId.length() == 0) {
			modelView.setViewName("userLogin");
			modelView.addObject("userLoginCommand", new UserLoginCommand());
			modelView.addObject("failureMsg", "Login ID is mandatory for OTP Generation");
			return modelView;
		}

		User user = getValidUser(loginId, model);

		if (user != null) {
			// Update if User OTP Already exists
			UserOTP userOtp = new UserOTP();
			if (user.getUserOTP() != null) {

				userOtp = user.getUserOTP();
				userOtp = upmService.getUserOTP(userOtp.getOtpId());
				userOtp.setGeneratedTmstmp(new Date(System.currentTimeMillis()));
				userOtp.setOtp(otpGenerator.generateOTPString());
				upmService.updateUserOTP(userOtp);

			} else {

				// Generate the OTP
				userOtp = upmService.generateUserOTP(user);
			}

			modelView.addObject("userOtp", userOtp);
			modelView.addObject("otpCommand", new GenerateOTPCommand(userOtp.getUserId(), userOtp.getOtp()));
			modelView.addObject("users", upmService.getAllUsers());
			modelView.setViewName("generateOTP");

		} else {

			modelView.setViewName("userLogin");
			modelView.addObject("userLoginCommand", new UserLoginCommand());
			modelView.addObject("failureMsg", "Invalid User. Please Register.");
		}

		return modelView;
	}

	/**
	 * Register user.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/processForm", params = "registerUser")
	public String registerUser(ModelMap model) {
		model.addAttribute("userProfileCommand", new UserProfileCommand());
		model.addAttribute("states", upmService.getAllStates());
		model.addAttribute("country", "IN");
		return "registerUser";
	}
	// helper methods

	/**
	 * Checks if is user authentic.
	 *
	 * @param user
	 *            the user
	 * @param formUser
	 *            the form user
	 * @param errors
	 *            the errors
	 * @return true, if is user authentic
	 */
	private boolean isUserAuthentic(User user, UserLoginCommand formUser, BindingResult errors) {

		if (user == null) {
			errors.rejectValue("loginId", "loginId.register", "Invalid User. Please Register.");
		} else {
			if (!formUser.getPassword().equals(user.getPassword())) {
				errors.rejectValue("password", "password.incorrect", "Enter the correct password");
			}

			if (user.getUserOTP() != null) {
				if (!user.getUserOTP().getOtp().equals(formUser.getOtp())) {
					errors.rejectValue("otp", "otp.incorrect", "Enter the correct OTP");
				} else {

					if ((System.currentTimeMillis()
							- user.getUserOTP().getGeneratedTmstmp().getTime()) > TimeUnit.MINUTES.toMillis(1)) {
						errors.rejectValue("otp", "otp.expired", "OTP has expired. Generate OTP again.");
					}
				}

			} else
				errors.rejectValue("otp", "otp.generate", "Invalid OTP. Generate OTP first");
		}

		if (errors.hasErrors()) {
			return false;
		}
		return true;
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
	private User getValidUser(String loginId, ModelMap model) {

		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) model.get("users");

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
