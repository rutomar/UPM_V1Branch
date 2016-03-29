package com.sogeti.upm.command;

/**
 * The Class GenerateOTPCommand.
 */
public class GenerateOTPCommand {

	/** The login id. */
	private String loginId;

	/** The otp. */
	private String otp;

	/**
	 * Instantiates a new generate otp command.
	 */
	public GenerateOTPCommand() {
		// default constructor
	}

	/**
	 * Instantiates a new generate otp command.
	 *
	 * @param loginId
	 *            the login id
	 * @param otp
	 *            the otp
	 */
	public GenerateOTPCommand(String loginId, String otp) {
		this.loginId = loginId;
		this.otp = otp;
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

}
