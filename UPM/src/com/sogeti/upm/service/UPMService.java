package com.sogeti.upm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.upm.dao.UPMDaoImpl;
import com.sogeti.upm.data.State;
import com.sogeti.upm.data.User;
import com.sogeti.upm.data.UserOTP;
import com.sogeti.upm.util.OTPGenerator;

/**
 * The Class UPMService.
 */
@Service
public class UPMService {

	/** The upm dao. */
	@Autowired
	private UPMDaoImpl upmDao;

	/** The otp generator. */
	@Autowired
	OTPGenerator otpGenerator;

	/**
	 * Checks if is user valid.
	 *
	 * @param loginId
	 *            the login id
	 * @return true, if is user valid
	 */
	public boolean isUserValid(String loginId) {

		if (upmDao.getUserById(loginId) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the user.
	 *
	 * @param loginId
	 *            the login id
	 * @return the user
	 */
	public User getUser(String loginId) {

		return upmDao.getUserById(loginId);
	}

	/**
	 * Gets the user otp by user id.
	 *
	 * @param id
	 *            the id
	 * @return the user otp by user id
	 */
	public UserOTP getUserOTPByUserId(long id) {
		return upmDao.getUserOTP(id);
	}

	/**
	 * Gets the user otp.
	 *
	 * @param id
	 *            the id
	 * @return the user otp
	 */
	public UserOTP getUserOTP(long id) {
		return upmDao.getUserOTP(id);
	}

	/**
	 * Generate user otp.
	 *
	 * @param user
	 *            the user
	 * @return the user otp
	 */
	public UserOTP generateUserOTP(User user) {

		String otp = otpGenerator.generateOTPString();

		UserOTP userOTP = new UserOTP();
		userOTP.setUser(user);
		userOTP.setOtp(otp);
		userOTP.setUserId(user.getLoginID());
		userOTP.setGeneratedTmstmp(new Date(System.currentTimeMillis()));
		// Persist the User OTP
		upmDao.createUserOTP(userOTP);
		return userOTP;

	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<User> getAllUsers() {
		return upmDao.getAllUsers();
	}

	/**
	 * Gets the all states.
	 *
	 * @return the all states
	 */
	public List<State> getAllStates() {

		return upmDao.getAllStates();
	}

	/**
	 * Register user.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	public User registerUser(User user) {

		return upmDao.createUserProfile(user);
	}

	/**
	 * Update user profile.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	public User updateUserProfile(User user) {

		return upmDao.updateUser(user);
	}

	/**
	 * Update user otp.
	 *
	 * @param userOtp
	 *            the user otp
	 * @return the user otp
	 */
	public UserOTP updateUserOTP(UserOTP userOtp) {
		return upmDao.updateUserOTP(userOtp);
	}

	/**
	 * Delete user otp.
	 *
	 * @param user
	 *            the user
	 */
	public void deleteUserOTP(User user) {

		UserOTP userOtp = user.getUserOTP();

		if (userOtp != null) {
			upmDao.deleteUserOTP(userOtp);
		}
	}

}
