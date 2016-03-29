package com.sogeti.upm.controller;

import static com.sogeti.upm.util.UPMUtils.COUNTRY;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;

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
import com.sogeti.upm.command.UserProfileCommand;
import com.sogeti.upm.data.Address;
import com.sogeti.upm.data.User;
import com.sogeti.upm.service.UPMService;
import com.sogeti.upm.validator.UpdateUserProfileValidator;;

/**
 * The Class UpdateUserProfileController.
 */
@Controller
@RequestMapping("/")
@SessionAttributes({ "users", "states" })
public class UpdateUserProfileController {

	private static Logger LOGGER = LoggerFactory.getLogger(UpdateUserProfileController.class);

	/** The upm service. */
	@Autowired
	UPMService upmService;

	/** The profile validator. */
	@Autowired
	private UpdateUserProfileValidator profileValidator;

	/*
	 * @Autowired FileSystemResource uploadDirResource;
	 */

	/**
	 * Inits the binder.
	 *
	 * @param binder
	 *            the binder
	 */
	@InitBinder("updateUserProfileCommand")
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
	public ModelAndView showUserProfile(@RequestParam String loginId) {

		LOGGER.debug("Display User Profile Screen");
		ModelAndView modelView = new ModelAndView("userProfile");
		try {
			User user = upmService.getUser(loginId);
			modelView.addObject("states", upmService.getAllStates());
			UpdateUserProfileCommand updateUserProfileCommand = convertBOtoVO(user);
			modelView.addObject("updateUserProfileCommand", updateUserProfileCommand);
			modelView.addObject("imageSrc", updateUserProfileCommand.getImageSrc());
		} catch (Exception e) {
			modelView.addObject("updateUserProfileCommand", new UserProfileCommand());
			LOGGER.error(e.getMessage());
		}
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
		LOGGER.debug("Updating user data");
		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("updateUserProfileCommand", updateUserProfileCommand);
				model.addAttribute("loginId", updateUserProfileCommand.getLoginId());
				model.addAttribute("failureMsg", "Enter the details correctly.");
				return "userProfile";
			}

			upmService.updateUserProfile(convertVOtoBO(updateUserProfileCommand));
			model.addAttribute("updateUserProfileCommand", updateUserProfileCommand);
			model.addAttribute("loginId", updateUserProfileCommand.getLoginId());
			model.addAttribute("msg", "User profile updated successfully!");

		} catch (Exception e) {
			model.addAttribute("updateUserProfileCommand", updateUserProfileCommand);
			LOGGER.error(e.getMessage());

		}
		return "userProfile";
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
		LOGGER.debug("Logging out user.");
		ModelAndView modelView = new ModelAndView("userLogin");
		modelView.addObject("userLoginCommand", new UserLoginCommand());
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
	 */
	// helpers
	private UpdateUserProfileCommand convertBOtoVO(User user) {
		UpdateUserProfileCommand command = new UpdateUserProfileCommand();
		LOGGER.debug("Converting User to FormCommand Object");
		try {

			command.setLoginId(user.getLoginID());
			command.setUserName(user.getUserName());
			command.setEmailId(user.getEmailId());

			command.setHouseNo(String.valueOf(user.getAddress().getHouseNo()));
			command.setStreet(user.getAddress().getStreet());
			command.setCity(user.getAddress().getCity());
			command.setState(user.getAddress().getStateId());
			command.setCountry(COUNTRY);

			// Converting ByteArray to Image for user
			String src = /* uploadDirResource.getPath() + */ "/snap.gif";
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(user.getImage()));
			ImageIO.write(image, "gif", new File(src));
			command.setImageSrc(src);

			LOGGER.debug("Converted Command object" + command);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
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
		LOGGER.debug("Converting Command to User object");
		User user = upmService.getUser(command.getLoginId());
		try {
			user.setUserName(command.getUserName());
			user.setEmailId(command.getEmailId());
			Address address = new Address(command.getLoginId(), Integer.parseInt(command.getHouseNo()),
					command.getStreet(), command.getCity(), command.getState(), COUNTRY);
			user.setAddress(address);
			user.setImage(command.getFile().getBytes());
			LOGGER.debug("Converted User object" + user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return user;
	}

}
