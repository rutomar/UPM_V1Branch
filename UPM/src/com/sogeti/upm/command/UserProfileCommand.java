package com.sogeti.upm.command;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * The Class UserProfileCommand.
 */
public class UserProfileCommand {

	/** The login id. */
	private String loginId;

	/** The password. */
	private String password;

	/** The user name. */
	private String userName;

	/** The email id. */
	private String emailId;

	/** The house no. */
	private String houseNo;

	/** The street. */
	private String street;

	/** The city. */
	private String city;

	/** The state. */
	private int state;

	/** The country. */
	private String country;

	/** The file. */
	private CommonsMultipartFile file;

	/**
	 * Instantiates a new user profile command.
	 */
	public UserProfileCommand() {
		// default constructor
	}

	/**
	 * Instantiates a new user profile command.
	 *
	 * @param loginId
	 *            the login id
	 * @param password
	 *            the password
	 * @param userName
	 *            the user name
	 * @param emailId
	 *            the email id
	 * @param houseNo
	 *            the house no
	 * @param street
	 *            the street
	 * @param city
	 *            the city
	 * @param state
	 *            the state
	 * @param country
	 *            the country
	 * @param file
	 *            the file
	 */
	public UserProfileCommand(String loginId, String password, String userName, String emailId, String houseNo,
			String street, String city, int state, String country, CommonsMultipartFile file) {

		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.emailId = emailId;
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.file = file;

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

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId
	 *            the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the house no.
	 *
	 * @return the house no
	 */
	public String getHouseNo() {
		return houseNo;
	}

	/**
	 * Sets the house no.
	 *
	 * @param houseNo
	 *            the new house no
	 */
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street
	 *            the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public CommonsMultipartFile getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file
	 *            the new file
	 */
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

}
