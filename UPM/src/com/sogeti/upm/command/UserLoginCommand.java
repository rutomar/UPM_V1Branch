package com.sogeti.upm.command;

/**
 * The Class UserLoginCommand.
 */
public class UserLoginCommand {

	/** The login id. */
	private String loginId;

	/** The otp. */
	private String otp;

	/** The password. */
	private String password;

	/**
	 * Instantiates a new user login command.
	 */
	public UserLoginCommand() {
		// default constructor
	}

	/**
	 * Instantiates a new user login command.
	 *
	 * @param loginId
	 *            the login id
	 * @param otp
	 *            the otp
	 * @param password
	 *            the password
	 */
	public UserLoginCommand(String loginId, String otp, String password) {

		this.loginId = loginId;
		this.otp = otp;
		this.password = password;
	}

	/**
	 * Gets the login id.
	 *
	 * @return the login id
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Sets the login id.
	 *
	 * @param loginId
	 *            the new login id
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets the otp.
	 *
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * Sets the otp.
	 *
	 * @param otp
	 *            the new otp
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
