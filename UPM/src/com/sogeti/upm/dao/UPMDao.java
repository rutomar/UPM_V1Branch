package com.sogeti.upm.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sogeti.upm.data.State;
import com.sogeti.upm.data.User;
import com.sogeti.upm.data.UserOTP;

/**
 * The Interface UPMDao.
 */
@Component
public interface UPMDao {

	/**
	 * Gets the user by id.
	 *
	 * @param loginId
	 *            the login id
	 * @return the user by id
	 */
	public User getUserById(String loginId);

	/**
	 * Gets the user otp.
	 *
	 * @param id
	 *            the id
	 * @return the user otp
	 */
	public UserOTP getUserOTP(long id);

	/**
	 * Creates the user otp.
	 *
	 * @param userOTP
	 *            the user otp
	 */
	public void createUserOTP(UserOTP userOTP);

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<User> getAllUsers();

	/**
	 * Gets the all states.
	 *
	 * @return the all states
	 */
	public List<State> getAllStates();

	/**
	 * Creates the user profile.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	public User createUserProfile(User user);

	/**
	 * Update user.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	public User updateUser(User user);

	/**
	 * Delete user otp.
	 *
	 * @param userOTP
	 *            the user otp
	 */
	public void deleteUserOTP(UserOTP userOTP);

	/**
	 * Update user otp.
	 *
	 * @param userOtp
	 *            the user otp
	 * @return the user otp
	 */
	public UserOTP updateUserOTP(UserOTP userOtp);
}
