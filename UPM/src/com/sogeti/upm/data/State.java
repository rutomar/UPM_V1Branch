package com.sogeti.upm.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class State.
 */
@Entity
@Table(name = "STATE")
public class State implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6052730506883830186L;

	/** The state id. */
	@Id
	@Column(name = "STATE_ID")
	private int stateId;

	/** The state name. */
	@Column(name = "STATE_NAME")
	private String stateName;

	/**
	 * Instantiates a new state.
	 */
	public State() {
		// default constructor
	}

	/**
	 * Instantiates a new state.
	 *
	 * @param stateId
	 *            the state id
	 * @param stateName
	 *            the state name
	 */
	public State(int stateId, String stateName) {
		this.stateId = stateId;
		this.stateName = stateName;

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
	 * Gets the state name.
	 *
	 * @return the state name
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * Sets the state name.
	 *
	 * @param stateName
	 *            the new state name
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
		result = prime * result + stateId;
		result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
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
		State other = (State) obj;
		if (stateId != other.stateId)
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
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
		return "State [stateId=" + stateId + ", stateName=" + stateName + "]";
	}

}
