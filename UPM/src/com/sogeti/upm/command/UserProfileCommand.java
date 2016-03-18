package com.sogeti.upm.command;

public class UserProfileCommand {

	private String loginId;
	private String otp;
	private String password;

	public UserProfileCommand() {

	}

	public UserProfileCommand(String loginId, String otp, String password) {

		this.loginId = loginId;
		this.otp = otp;
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
