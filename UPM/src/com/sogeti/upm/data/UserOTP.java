package com.sogeti.upm.data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "USER_OTP")
public class UserOTP implements Serializable {

	private static final long serialVersionUID = -6956602264690435890L;

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name="OTP_ID_SEQ", sequenceName="USEROTPIDSEQUENCE",initialValue=1, allocationSize=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OTP_ID_SEQ")
	private long otpId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "OTP")
	private String otp;

	@Column(name = "OTP_GENERATED_TMSTMP")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date generatedTmstmp;

	@OneToOne
	@JoinColumn(name = "ID")
	private User user;

	public UserOTP() {

	}

	public UserOTP(long otpId, String userId, String otp, Date generatedTmstmp) {

		this.otpId = otpId;
		this.userId = userId;
		this.otp = otp;
		this.generatedTmstmp = generatedTmstmp;
	}

	public long getOtpId() {
		return otpId;
	}

	public void setOtpId(long otpId) {
		this.otpId = otpId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getGeneratedTmstmp() {
		return generatedTmstmp;
	}

	public void setGeneratedTmstmp(Date generatedTmstmp) {
		this.generatedTmstmp = generatedTmstmp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((generatedTmstmp == null) ? 0 : generatedTmstmp.hashCode());
		result = prime * result + ((otp == null) ? 0 : otp.hashCode());
		result = prime * result + (int) (otpId ^ (otpId >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserOTP [otpId=" + otpId + ", userId=" + userId + ", otp=" + otp + ", generatedTmstmp="
				+ generatedTmstmp + "]";
	}

}
