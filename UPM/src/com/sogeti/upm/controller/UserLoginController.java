package com.sogeti.upm.controller;

import static com.sogeti.upm.util.UPMConstantsUtil.FORM_GENERATE_OTP;
import static com.sogeti.upm.util.UPMConstantsUtil.FORM_REGISTER_USER;
import static com.sogeti.upm.util.UPMConstantsUtil.FORM_USER_LOGIN;
import static com.sogeti.upm.util.UPMConstantsUtil.STATES;
import static com.sogeti.upm.util.UPMConstantsUtil.USERS;
import static com.sogeti.upm.util.UPMConstantsUtil.VIEW_GENERATE_OTP;
import static com.sogeti.upm.util.UPMConstantsUtil.VIEW_REGISTER_USER;
import static com.sogeti.upm.util.UPMConstantsUtil.VIEW_USER_LOGIN;
import static com.sogeti.upm.util.UPMConstantsUtil.VIEW_USER_PROFILE;

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
import com.sogeti.upm.command.RegisterUserCommand;
import com.sogeti.upm.command.UserLoginCommand;
import com.sogeti.upm.data.User;
import com.sogeti.upm.data.UserOTP;
import com.sogeti.upm.service.UPMService;
import com.sogeti.upm.util.OTPGenerator;
import com.sogeti.upm.util.UPMConstantsUtil;
import com.sogeti.upm.util.UPMUtils;
import com.sogeti.upm.validator.UserLoginValidator;

/**
 * The Class UserLoginController.
 */
@Controller
@RequestMapping("/")
@SessionAttributes({ "users", "states" })
public class UserLoginController {

	private static Logger logger = LoggerFactory.getLogger(UserLoginController.class);
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
	@InitBinder(UPMConstantsUtil.FORM_USER_LOGIN)
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
		logger.debug("Welcome User");
		model.addAttribute(USERS, upmService.getAllUsers());
		model.addAttribute(STATES, upmService.getAllStates());
		model.addAttribute(UPMConstantsUtil.FORM_USER_LOGIN, new UserLoginCommand());
		return UPMConstantsUtil.VIEW_USER_LOGIN;
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
	public String userLogin(
			@Validated @ModelAttribute(UPMConstantsUtil.FORM_USER_LOGIN) UserLoginCommand userLoginCommand,
			BindingResult bindingResult, ModelMap model) {
		logger.debug("Login User");

		// if any attributes are empty , return back to login page
		if (bindingResult.hasErrors()) {
			model.addAttribute(FORM_USER_LOGIN, userLoginCommand);
			return VIEW_USER_LOGIN;
		}

		// Validate the authenticity of the user & OTP generation Tmstmp
		User user = getValidUser(userLoginCommand.getLoginId(), model);

		// if User is Authenticate go to the User Profile Page
		if (isUserAuthentic(user, userLoginCommand, bindingResult)) {
			// delete the old UserOTP
			logger.debug("User is Authentic.");
			upmService.deleteUserOTP(user);
			// set the attributes to redirect to UserProfile Page
			model.addAttribute("loginId", user.getLoginID());
			// updated Users Data
			model.addAttribute(USERS, upmService.getAllUsers());
			return "redirect:/" + VIEW_USER_PROFILE;
		}

		model.addAttribute(FORM_USER_LOGIN, userLoginCommand);

		return VIEW_USER_LOGIN;

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
			modelView.setViewName(VIEW_USER_LOGIN);
			modelView.addObject(FORM_USER_LOGIN, new UserLoginCommand());
			modelView.addObject("failureMsg", "Login ID is mandatory for OTP Generation");
			return modelView;
		}

		User user = getValidUser(loginId, model);

		if (user != null) {
			UserOTP userOtp = upmService.generateUserOTP(user);
			modelView.addObject("userOtp", userOtp);
			modelView.addObject(FORM_GENERATE_OTP, new GenerateOTPCommand(userOtp.getUserId(), userOtp.getOtp()));
			modelView.addObject(USERS, upmService.getAllUsers());
			modelView.setViewName(VIEW_GENERATE_OTP);

		} else {

			modelView.setViewName(UPMConstantsUtil.VIEW_USER_LOGIN);
			modelView.addObject(UPMConstantsUtil.FORM_USER_LOGIN, new UserLoginCommand());
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
		model.addAttribute(FORM_REGISTER_USER, new RegisterUserCommand());
		model.addAttribute(STATES, upmService.getAllStates());
		model.addAttribute("country", "IN");
		return VIEW_REGISTER_USER;
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
				} else if (isOTPExpired(user.getUserOTP().getGeneratedTmstmp().getTime())) {
					errors.rejectValue("otp", "otp.expired", "OTP has expired. Generate OTP again.");
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
	 * Checks if is OTP for this User is expired
	 *
	 * @param generation
	 *            timestamp
	 *
	 * @return true, if otp is expired
	 */
	private boolean isOTPExpired(long generatedTmstmp) {

		if ((System.currentTimeMillis() - generatedTmstmp) > TimeUnit.MINUTES.toMillis(1)) {
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
	private User getValidUser(String loginId, ModelMap model) {

		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) model.get(USERS);

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
