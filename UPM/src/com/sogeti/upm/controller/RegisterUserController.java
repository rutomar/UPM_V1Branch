package com.sogeti.upm.controller;

import static com.sogeti.upm.util.UPMUtils.COUNTRY;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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

import com.sogeti.upm.command.UserLoginCommand;
import com.sogeti.upm.command.UserProfileCommand;
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

	private static Logger LOGGER = LoggerFactory.getLogger(RegisterUserController.class);

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
	@InitBinder("userProfileCommand")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
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
			@Validated @ModelAttribute("userProfileCommand") UserProfileCommand userProfileCommand,
			BindingResult bindingResult, ModelMap model) {

		boolean exisitingUser = isExistingUser(userProfileCommand.getLoginId());

		if (bindingResult.hasErrors() || exisitingUser) {
			if (exisitingUser) {
				bindingResult.addError(new ObjectError("loginId", "User ID already exists."));
			}
			model.addAttribute("states", upmService.getAllStates());
			model.addAttribute("userProfileCommand", userProfileCommand);
			return "registerUser";
		}

		try {
			User user = convertVOtoBO(userProfileCommand);
			upmService.registerUser(user);
			model.addAttribute("users", upmService.getAllUsers());
			model.addAttribute("userLoginCommand", new UserLoginCommand());
			model.addAttribute("msg", "User Registered Successfully!");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			model.addAttribute("userLoginCommand", new UserLoginCommand());
			return "userLogin";
		}
		return "userLogin";

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
		ModelAndView model = new ModelAndView("registerUser");
		model.addObject("states", upmService.getAllStates());
		model.addObject("userProfileCommand", new UserProfileCommand());
		return model;
	}

	/**
	 * Convert v oto bo.
	 *
	 * @param userProfileCommand
	 *            the user profile command
	 * @return the user
	 */
	// helpers
	private User convertVOtoBO(UserProfileCommand userProfileCommand) {
		User user = new User();
		LOGGER.debug("Converting Command to User Object.");
		user.setLoginID(userProfileCommand.getLoginId());
		user.setPassword(userProfileCommand.getPassword());
		user.setUserName(userProfileCommand.getUserName());
		user.setEmailId(userProfileCommand.getEmailId());
		Address address = new Address(userProfileCommand.getLoginId(),
				Integer.parseInt(userProfileCommand.getHouseNo()), userProfileCommand.getStreet(),
				userProfileCommand.getCity(), userProfileCommand.getState(), COUNTRY);
		user.setAddress(address);
		user.setImage(userProfileCommand.getFile().getBytes());
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
