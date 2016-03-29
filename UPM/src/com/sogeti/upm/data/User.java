package com.sogeti.upm.data;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class User.
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5600565532536174919L;

	/** The login id. */
	@Id
	@Column(name = "ID")
	private String loginID;

	/** The user name. */
	@Column(name = "USER_NAME")
	private String userName;

	/** The email id. */
	@Column(name = "EMAIL_ID")
	private String emailId;

	/** The password. */
	@Column(name = "PASSWORD")
	private String password;

	/** The image. */
	@Lob
	@Column(name = "IMAGE")
	private byte[] image;

	/** The address. */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Address.class)
	private Address address;

	/** The user otp. */
	@OneToOne(mappedBy = "user", targetEntity = UserOTP.class)
	private UserOTP userOTP;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		// default constructor
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param loginID
	 *            the login id
	 * @param userName
	 *            the user name
	 * @param emailId
	 *            the email id
	 * @param password
	 *            the password
	 * @param image
	 *            the image
	 */
	public User(String loginID, String userName, String emailId, String password, byte[] image) {

		this.loginID = loginID;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.image = image;
	}

	/**
	 * Gets the login id.
	 *
	 * @return the login id
	 */
	public String getLoginID() {
		return loginID;
	}

	/**
	 * Sets the login id.
	 *
	 * @param loginID
	 *            the new login id
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
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
	 * Gets the image.
	 *
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image
	 *            the new image
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets the user otp.
	 *
	 * @return the user otp
	 */
	public UserOTP getUserOTP() {
		return userOTP;
	}

	/**
	 * Sets the user otp.
	 *
	 * @param userOTP
	 *            the new user otp
	 */
	public void setUserOTP(UserOTP userOTP) {
		this.userOTP = userOTP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((loginID == null) ? 0 : loginID.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userOTP == null) ? 0 : userOTP.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (loginID == null) {
			if (other.loginID != null)
				return false;
		} else if (!loginID.equals(other.loginID))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userOTP == null) {
			if (other.userOTP != null)
				return false;
		} else if (!userOTP.equals(other.userOTP))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [loginID=" + loginID + ", userName=" + userName + ", emailId=" + emailId + ", password=" + password
				+ ", image=" + Arrays.toString(image) + ", address=" + address + ", userOTP=" + userOTP + "]";
	}

}
