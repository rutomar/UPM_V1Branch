package com.sogeti.upm.controller;

import static com.sogeti.upm.util.UPMConstantsUtil.FORM_REGISTER_USER;
import static com.sogeti.upm.util.UPMConstantsUtil.FORM_USER_LOGIN;
import static com.sogeti.upm.util.UPMConstantsUtil.VIEW_REGISTER_USER;
import static com.sogeti.upm.util.UPMConstantsUtil.VIEW_USER_LOGIN;
import static com.sogeti.upm.util.UPMUtils.COUNTRY;

import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.sogeti.upm.command.RegisterUserCommand;
import com.sogeti.upm.command.UserLoginCommand;
import com.sogeti.upm.data.Address;
import com.sogeti.upm.data.User;
import com.sogeti.upm.service.UPMService;
import com.sogeti.upm.validator.RegisterUserValidator;

/**
 * The Class UserRegisterController.
 */
@Controller
@SessionAttributes({ "users", "states" })
public class RegisterUserController {

	private static Logger logger = LoggerFactory.getLogger(RegisterUserController.class);

	/** The upm service. */
	@Autowired
	UPMService upmService;

	/** The validator. */
	@Autowired
	private RegisterUserValidator validator;

	/**
	 * Inits the binder.
	 *
	 * @param binder
	 *            the binder
	 */
	@InitBinder(FORM_REGISTER_USER)
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) throws ServletException {
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

	}

	/**
	 * Creates the user profile.
	 *
	 * @param userProfileCommand
	 *            the user profile command
	 * @param bindingResult
	 *            the binding result
	 * @param model
	 *            the model
	 * @return the string
	 * @throws Exception
	 */
	@RequestMapping(value = "/processUserProfile", params = "createUser", method = RequestMethod.POST)
	public String createUserProfile(
			@Validated @ModelAttribute(FORM_REGISTER_USER) RegisterUserCommand userProfileCommand,
			BindingResult bindingResult, ModelMap model) {

		boolean exisitingUser = isExistingUser(userProfileCommand.getLoginId());

		if (bindingResult.hasErrors() || exisitingUser) {
			if (exisitingUser) {
				bindingResult.addError(new ObjectError("loginId", "User ID already exists."));
			}
			model.addAttribute("states", upmService.getAllStates());
			model.addAttribute(FORM_REGISTER_USER, userProfileCommand);
			return VIEW_REGISTER_USER;
		}

		User user = convertVOtoBO(userProfileCommand);
		upmService.registerUser(user);
		model.addAttribute("users", upmService.getAllUsers());
		model.addAttribute(FORM_USER_LOGIN, new UserLoginCommand());
		model.addAttribute("msg", "User Registered Successfully!");
		return VIEW_USER_LOGIN;

	}

	/**
	 * Clear user profile.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/processUserProfile", params = "clear", method = RequestMethod.POST)
	public ModelAndView clearUserProfile() {
		ModelAndView model = new ModelAndView(VIEW_REGISTER_USER);
		model.addObject("states", upmService.getAllStates());
		model.addObject(FORM_REGISTER_USER, new RegisterUserCommand());
		return model;
	}

	/**
	 * Convert v oto bo.
	 *
	 * @param registerUserCommand
	 *            the user profile command
	 * @return the user
	 */
	// helpers
	private User convertVOtoBO(RegisterUserCommand registerUserCommand) {
		User user = new User();
		logger.debug("Converting Command to User Object.");
		user.setLoginID(registerUserCommand.getLoginId());
		user.setPassword(registerUserCommand.getPassword());
		user.setUserName(registerUserCommand.getUserName());
		user.setEmailId(registerUserCommand.getEmailId());
		Address address = new Address(registerUserCommand.getLoginId(),
				Integer.parseInt(registerUserCommand.getHouseNo()), registerUserCommand.getStreet(),
				registerUserCommand.getCity(), registerUserCommand.getState(), COUNTRY);
		user.setAddress(address);
		user.setImage(registerUserCommand.getFile().getBytes());
		return user;
	}

	/**
	 * check if login Id already exists
	 *
	 * @param loginId
	 *            the user login Id
	 * @return flag true if user exists else false
	 */
	private boolean isExistingUser(String loginId) {

		List<User> users = upmService.getAllUsers();

		if (users != null) {
			for (User user : users) {
				if (loginId.equals(user.getLoginID())) {
					return true;
				}
			}
		}
		return false;

	}
}
