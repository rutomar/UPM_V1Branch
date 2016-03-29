package com.sogeti.upm.command;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * The Class UpdateUserProfileCommand.
 */
public class UpdateUserProfileCommand {

	/** The login id. */
	private String loginId;

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

	/** The image. */
	private String imageSrc;

	/**
	 * Instantiates a new update user profile command.
	 */
	public UpdateUserProfileCommand() {
		// default constructor
	}

	/**
	 * Instantiates a new update user profile command.
	 *
	 * @param loginId
	 *            the login id
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
	 * @param image
	 *            the image
	 */
	public UpdateUserProfileCommand(String loginId, String userName, String emailId, String houseNo, String street,
			String city, int state, String country, CommonsMultipartFile file, String imageSrc) {

		this.loginId = loginId;
		this.userName = userName;
		this.emailId = emailId;
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.file = file;
		this.imageSrc = imageSrc;
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

	/**
	 * Gets the image src.
	 *
	 * @return the image src
	 */
	public String getImageSrc() {
		return imageSrc;
	}

	/**
	 * Sets the image src.
	 *
	 * @param imageSrc
	 *            the new image src
	 */
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	@Override
	public String toString() {
		return "UpdateUserProfileCommand [loginId=" + loginId + ", userName=" + userName + ", emailId=" + emailId
				+ ", houseNo=" + houseNo + ", street=" + street + ", city=" + city + ", state=" + state + ", country="
				+ country + ", file=" + file + ", imageSrc=" + imageSrc + "]";
	}

}
