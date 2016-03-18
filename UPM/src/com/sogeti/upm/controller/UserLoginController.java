package com.sogeti.upm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sogeti.upm.service.UPMService;
import com.sogeti.upm.command.UserLoginCommand;

@Controller
@RequestMapping("/")
public class UserLoginController {

	@Autowired
	UPMService upmService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String welcomeUser(ModelMap model) {
		UserLoginCommand userLoginCommand = new UserLoginCommand();
		model.addAttribute("userLoginCommand", userLoginCommand);
	//	upmService.createUser();
		return "userLogin";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String userLogin(@ModelAttribute("userLoginCommand")UserLoginCommand userLoginCommand, ModelMap model) {
		model.addAttribute("userLoginCommand", userLoginCommand);
		model.addAttribute("loginId",userLoginCommand.getLoginId());
		model.addAttribute("otp",userLoginCommand.getOtp());
		return "userLogin";
	}

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String userRegister(ModelMap model) {

		return "userRegister";
	}

	@RequestMapping(value="/generateOTP", method = RequestMethod.POST)
	public String generateOTP(@ModelAttribute UserLoginCommand userLoginCommand, ModelMap model) {
		
		userLoginCommand.setOtp(upmService.generateUserOTP(userLoginCommand.getLoginId()));
		model.addAttribute("userLoginCommand", userLoginCommand);
	
		return "generateOTP";
	}

	

}
