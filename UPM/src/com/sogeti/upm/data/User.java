package com.sogeti.upm.data;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="USER")
public class User implements Serializable {

	private static final long serialVersionUID = -5600565532536174919L;

	@Id
	@Column(name="ID")
	private String loginID;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Lob
	@Column(name="IMAGE", length=100000)
	private byte[] image;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = UserOTP.class)
	private UserOTP userOTP;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Address.class)
	private Address address;
	
	public User(){
		
	}
	
	public User(String loginID, String userName, String emailId, String password, byte[] image) {
		
		this.loginID = loginID;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.image = image;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public UserOTP getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(UserOTP userOTP) {
		this.userOTP = userOTP;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((loginID == null) ? 0 : loginID.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (loginID == null) {
			if (other.loginID != null)
				return false;
		} else if (!loginID.equals(other.loginID))
			return false;
		if (!Arrays.equals(image, other.image))
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
		return true;
	}

	@Override
	public String toString() {
		return "User [loginID=" + loginID + ", userName=" + userName + ", emailId=" + emailId + ", password=" + password
				+ ", image=" + Arrays.toString(image) + "]";
	}
	
	
	
}
