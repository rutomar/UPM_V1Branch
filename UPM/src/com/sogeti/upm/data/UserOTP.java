package com.sogeti.upm.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class UserOTP.
 */
@Entity
@Table(name = "USER_OTP")
public class UserOTP implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6956602264690435890L;

	/** The otp id. */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "OTP_ID_SEQ", sequenceName = "USEROTPIDSEQUENCE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OTP_ID_SEQ")
	private long otpId;

	/** The user id. */
	@Column(name = "USER_ID")
	private String userId;

	/** The otp. */
	@Column(name = "OTP")
	private String otp;

	/** The generated tmstmp. */
	@Column(name = "OTP_GENERATED_TMSTMP")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date generatedTmstmp;

	/** The user. */
	@OneToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private User user;

	/**
	 * Instantiates a new user otp.
	 */
	public UserOTP() {
		// default constructor
	}

	/**
	 * Instantiates a new user otp.
	 *
	 * @param otpId
	 *            the otp id
	 * @param userId
	 *            the user id
	 * @param otp
	 *            the otp
	 * @param generatedTmstmp
	 *            the generated tmstmp
	 */
	public UserOTP(long otpId, String userId, String otp, Date generatedTmstmp) {

		this.otpId = otpId;
		this.userId = userId;
		this.otp = otp;
		this.generatedTmstmp = generatedTmstmp;
	}

	/**
	 * Gets the otp id.
	 *
	 * @return the otp id
	 */
	public long getOtpId() {
		return otpId;
	}

	/**
	 * Sets the otp id.
	 *
	 * @param otpId
	 *            the new otp id
	 */
	public void setOtpId(long otpId) {
		this.otpId = otpId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * Gets the generated tmstmp.
	 *
	 * @return the generated tmstmp
	 */
	public Date getGeneratedTmstmp() {
		return generatedTmstmp;
	}

	/**
	 * Sets the generated tmstmp.
	 *
	 * @param generatedTmstmp
	 *            the new generated tmstmp
	 */
	public void setGeneratedTmstmp(Date generatedTmstmp) {
		this.generatedTmstmp = generatedTmstmp;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
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
		result = prime * result + ((generatedTmstmp == null) ? 0 : generatedTmstmp.hashCode());
		result = prime * result + ((otp == null) ? 0 : otp.hashCode());
		result = prime * result + (int) (otpId ^ (otpId >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserOTP other = (UserOTP) obj;
		if (generatedTmstmp == null) {
			if (other.generatedTmstmp != null)
				return false;
		} else if (!generatedTmstmp.equals(other.generatedTmstmp))
			return false;
		if (otp == null) {
			if (other.otp != null)
				return false;
		} else if (!otp.equals(other.otp))
			return false;
		if (otpId != other.otpId)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
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
		return "UserOTP [otpId=" + otpId + ", userId=" + userId + ", otp=" + otp + ", generatedTmstmp="
				+ generatedTmstmp + "]";
	}

}
