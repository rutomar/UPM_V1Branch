package com.sogeti.upm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/generateOTP")
public class GenerateOTPController {

	//@RequestMapping(value="/" , method=RequestMethod.GET)
    public String generateOTP(ModelMap model) {
		//String loginId = (String)request.getAttribute("loginId");
		//System.out.println(userLoginCommand.getLoginId());
		//System.out.println(mloginId);
       // request.setAttribute("loginId", loginId);
        //request.setAttribute("otp", upmService.generateOTP(loginId));
        return "generateOTP";
    }
	/*@RequestMapping(value="/{loginId}" , method=RequestMethod.GET)
    public String generateOTP(@PathVariable String loginId) {
		//String loginId = (String)request.getAttribute("loginId");
		//System.out.println(userLoginCommand.getLoginId());
		System.out.println(loginId);
       // request.setAttribute("loginId", loginId);
        //request.setAttribute("otp", upmService.generateOTP(loginId));
        return "generateOTP";
    }
	
	@RequestMapping(value="/{loginId}" , method=RequestMethod.GET)
    public String generateOTP(@PathVariable String loginId) {
		//String loginId = (String)request.getAttribute("loginId");
		//System.out.println(userLoginCommand.getLoginId());
		System.out.println(loginId);
       // request.setAttribute("loginId", loginId);
        //request.setAttribute("otp", upmService.generateOTP(loginId));
        return "generateOTP";
    }*/
	
}
