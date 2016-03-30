package com.sogeti.upm.controller;

import static com.sogeti.upm.util.UPMConstantsUtil.FORM_USER_LOGIN;
import static com.sogeti.upm.util.UPMConstantsUtil.FORM_USER_PROFILE;
import static com.sogeti.upm.util.UPMConstantsUtil.VIEW_USER_LOGIN;
import static com.sogeti.upm.util.UPMConstantsUtil.VIEW_USER_PROFILE;
import static com.sogeti.upm.util.UPMUtils.COUNTRY;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
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

import com.sogeti.upm.command.UpdateUserProfileCommand;
import com.sogeti.upm.command.UserLoginCommand;
import com.sogeti.upm.data.Address;
import com.sogeti.upm.data.User;
import com.sogeti.upm.service.UPMService;
import com.sogeti.upm.util.UPMConstantsUtil;
import com.sogeti.upm.validator.UpdateUserProfileValidator;

/**
 * The Class UpdateUserProfileController.
 */
@Controller
@RequestMapping("/")
@SessionAttributes({ "users", "states" })
public class UpdateUserProfileController {

	private static Logger logger = LoggerFactory.getLogger(UpdateUserProfileController.class);

	/** The upm service. */
	@Autowired
	UPMService upmService;

	/** The profile validator. */
	@Autowired
	private UpdateUserProfileValidator profileValidator;

	/**
	 * Inits the binder.
	 *
	 * @param binder
	 *            the binder
	 */
	@InitBinder(FORM_USER_PROFILE)
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(profileValidator);
	}

	/**
	 * Show user profile.
	 *
	 * @param loginId
	 *            the login id
	 * @param model
	 *            the model
	 * @return the model and view
	 */
	@RequestMapping(value = "/userProfile", method = RequestMethod.GET)
	public ModelAndView showUserProfile(@RequestParam String loginId) throws UnsupportedEncodingException {

		logger.debug("Display User Profile Screen");
		ModelAndView modelView = new ModelAndView(VIEW_USER_PROFILE);
		User user = upmService.getUser(loginId);
		modelView.addObject("states", upmService.getAllStates());
		UpdateUserProfileCommand updateUserProfileCommand = convertBOtoVO(user);
		modelView.addObject(FORM_USER_PROFILE, updateUserProfileCommand);
		modelView.addObject("imageSrc", updateUserProfileCommand.getImageSrc());

		return modelView;
	}

	/**
	 * Update user profile.
	 *
	 * @param updateUserProfileCommand
	 *            the update user profile command
	 * @param bindingResult
	 *            the binding result
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/updateUserProfile", params = "update", method = RequestMethod.POST)
	public String updateUserProfile(@Validated @ModelAttribute UpdateUserProfileCommand updateUserProfileCommand,
			BindingResult bindingResult, ModelMap model) {
		logger.debug("Updating user data");

		if (bindingResult.hasErrors()) {
			model.addAttribute(FORM_USER_PROFILE, updateUserProfileCommand);
			model.addAttribute("loginId", updateUserProfileCommand.getLoginId());
			model.addAttribute("imageSrc", updateUserProfileCommand.getImageSrc());
			model.addAttribute("failureMsg", "Enter the details correctly.");
			return VIEW_USER_PROFILE;
		}

		upmService.updateUserProfile(convertVOtoBO(updateUserProfileCommand));
		model.addAttribute(UPMConstantsUtil.FORM_USER_PROFILE, updateUserProfileCommand);
		model.addAttribute("loginId", updateUserProfileCommand.getLoginId());
		model.addAttribute("msg", "User profile updated successfully!");

		return VIEW_USER_PROFILE;
	}

	/**
	 * Logout.
	 *
	 * @param model
	 *            the model
	 * @return the model and view
	 */
	@RequestMapping(value = "/updateUserProfile", params = "logout")
	public ModelAndView logout() {
		logger.debug("Logging out user.");
		ModelAndView modelView = new ModelAndView(VIEW_USER_LOGIN);
		modelView.addObject(FORM_USER_LOGIN, new UserLoginCommand());
		return modelView;
	}

	/**
	 * Convert bo to vo.
	 *
	 * @param user
	 *            the user
	 * @param request
	 * @param response
	 * @return the update user profile command
	 * @throws Exception
	 */
	// helpers
	private UpdateUserProfileCommand convertBOtoVO(User user) throws UnsupportedEncodingException {
		UpdateUserProfileCommand command = new UpdateUserProfileCommand();
		logger.debug("Converting User to FormCommand Object");

		command.setLoginId(user.getLoginID());
		command.setUserName(user.getUserName());
		command.setEmailId(user.getEmailId());

		command.setHouseNo(String.valueOf(user.getAddress().getHouseNo()));
		command.setStreet(user.getAddress().getStreet());
		command.setCity(user.getAddress().getCity());
		command.setState(user.getAddress().getStateId());
		command.setCountry(COUNTRY);
		command.setImageSrc(getImageSrc(user));

		logger.debug("Converted Command object" + command);

		return command;
	}

	/**
	 * Convert vo to bo.
	 *
	 * @param command
	 *            the command
	 * @return the user
	 */
	private User convertVOtoBO(UpdateUserProfileCommand command) {
		logger.debug("Converting Command to User object");
		User user = upmService.getUser(command.getLoginId());
		user.setUserName(command.getUserName());
		user.setEmailId(command.getEmailId());
		Address address = new Address(command.getLoginId(), Integer.parseInt(command.getHouseNo()), command.getStreet(),
				command.getCity(), command.getState(), COUNTRY);
		user.setAddress(address);
		user.setImage(command.getFile().getBytes());
		logger.debug("Converted User object" + user);

		return user;
	}

	/**
	 * generate the image src in base64 encoded format for display
	 *
	 * @param user
	 *            the user object
	 * @return the image source
	 */
	private String getImageSrc(User user) throws UnsupportedEncodingException {

		String imageSrc = "";
		try {

			byte[] encodeBase64 = Base64.encodeBase64(user.getImage());
			imageSrc = new String(encodeBase64, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			logger.error(e.getMessage());
			throw e;

		}
		return imageSrc;
	}

}
