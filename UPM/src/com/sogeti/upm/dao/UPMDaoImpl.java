package com.sogeti.upm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.upm.data.State;
import com.sogeti.upm.data.User;
import com.sogeti.upm.data.UserOTP;

/**
 * The Class UPMDaoImpl.
 */
@Repository
@Transactional
public class UPMDaoImpl {

	/** The em. */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Gets the user by id.
	 *
	 * @param loginId
	 *            the login id
	 * @return the user by id
	 */
	public User getUserById(String loginId) {
		return (User) em.find(User.class, loginId);
	}

	/**
	 * Gets the user otp.
	 *
	 * @param id
	 *            the id
	 * @return the user otp
	 */
	public UserOTP getUserOTP(long id) {
		return (UserOTP) em.find(UserOTP.class, id);
	}

	/**
	 * Creates the user otp.
	 *
	 * @param userOTP
	 *            the user otp
	 */
	public void createUserOTP(UserOTP userOTP) {
		em.persist(userOTP);
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<User> getAllUsers() {

		return em.createQuery("Select a From User a", User.class).getResultList();
	}

	/**
	 * Gets the all states.
	 *
	 * @return the all states
	 */
	public List<State> getAllStates() {

		return em.createQuery("Select a From State a", State.class).getResultList();

	}

	/**
	 * Creates the user profile.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	public User createUserProfile(User user) {
		em.persist(user);
		return user;
	}

	/**
	 * Update user.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	public User updateUser(User user) {
		return em.merge(user);
	}

	/**
	 * Delete user otp.
	 *
	 * @param userOTP
	 *            the user otp
	 */
	public void deleteUserOTP(UserOTP userOTP) {
		UserOTP tempUserOTP = em.find(UserOTP.class, userOTP.getOtpId());
		em.remove(tempUserOTP);
	}

	/**
	 * Update user otp.
	 *
	 * @param userOtp
	 *            the user otp
	 * @return the user otp
	 */
	public UserOTP updateUserOTP(UserOTP userOtp) {
		return em.merge(userOtp);
	}

}
