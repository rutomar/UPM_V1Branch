package com.sogeti.upm.service;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.upm.dao.UPMDaoImpl;
import com.sogeti.upm.data.User;
import com.sogeti.upm.data.UserOTP;
import com.sogeti.upm.util.UPMUtils;

@Service
public class UPMService {

	@Autowired
	UPMDaoImpl upmDao;
	@Autowired
	UPMUtils upmUtils;

	public boolean isUserValid(String loginId) {

		if (upmDao.getUserById(loginId) != null) {
			return true;
		}
		return false;
	}
	
	public User getUser(String loginId) {

		return upmDao.getUserById(loginId);
	}
	
	public UserOTP getUserOTP(long id) {
		return upmDao.getUserOTP(id);
	}
	
	public String generateUserOTP(String loginId) {
		
		UserOTP userOTP = new UserOTP();
			
		if (isUserValid(loginId)){
			 
			 userOTP.setOtp(UPMUtils.generateUserOTP());
			 userOTP.setUserId(loginId);
			 userOTP.setGeneratedTmstmp(new Date(System.currentTimeMillis()));
			 upmDao.createUserOTP(userOTP);
			 return userOTP.getOtp();
		}
		
		return new String("Invalid User");
	}

	
	
}
