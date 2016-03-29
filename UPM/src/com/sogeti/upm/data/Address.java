package com.sogeti.upm.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class Address.
 */
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1831751731624427170L;

	/** The address id. */
	@Id
	@Column(name = "ADDRESS_ID")
	private String addressId;

	/** The house no. */
	@Column(name = "HOUSE_NO")
	private int houseNo;

	/** The street. */
	@Column(name = "STREET")
	private String street;

	/** The city. */
	@Column(name = "CITY")
	private String city;

	/** The state id. */
	@Column(name = "STATE_ID")
	private int stateId;

	/** The country. */
	@Column(name = "COUNTRY")
	private String country;

	/** The user. */
	@OneToOne
	@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
	private User user;

	/**
	 * Instantiates a new address.
	 */
	public Address() {
		// default constructor
	}

	/**
	 * Instantiates a new address.
	 *
	 * @param addressId
	 *            the address id
	 * @param houseNo
	 *            the house no
	 * @param street
	 *            the street
	 * @param city
	 *            the city
	 * @param stateId
	 *            the state id
	 * @param country
	 *            the country
	 */
	public Address(String addressId, int houseNo, String street, String city, int stateId, String country) {
		this.addressId = addressId;
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.stateId = stateId;
		this.country = country;
	}

	/**
	 * Gets the address id.
	 *
	 * @return the address id
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * Sets the address id.
	 *
	 * @param addressId
	 *            the new address id
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * Gets the house no.
	 *
	 * @return the house no
	 */
	public int getHouseNo() {
		return houseNo;
	}

	/**
	 * Sets the house no.
	 *
	 * @param houseNo
	 *            the new house no
	 */
	public void setHouseNo(int houseNo) {
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
	 * Gets the state id.
	 *
	 * @return the state id
	 */
	public int getStateId() {
		return stateId;
	}

	/**
	 * Sets the state id.
	 *
	 * @param stateId
	 *            the new state id
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
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
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + houseNo;
		result = prime * result + stateId;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (houseNo != other.houseNo)
			return false;
		if (stateId != other.stateId)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
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
		return "Address [addressId=" + addressId + ", houseNo=" + houseNo + ", street=" + street + ", city=" + city
				+ ", stateId=" + stateId + ", country=" + country + "]";
	}
}
