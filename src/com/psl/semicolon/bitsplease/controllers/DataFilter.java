package com.psl.semicolon.bitsplease.controllers;

import java.io.Serializable;

public class DataFilter implements Serializable, Comparable<DataFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8768529432598102779L;

	private String message;
	private Long timeOfRecieve;

	public DataFilter(String message) {
		this.message = message;
		this.timeOfRecieve = System.currentTimeMillis();
	}

	public String geMessage() {
		return this.message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		DataFilter other = (DataFilter) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (timeOfRecieve == null) {
			if (other.timeOfRecieve != null)
				return false;
		} else if (timeOfRecieve - other.timeOfRecieve > 30000) {
			return false;
		}

		return true;
	}

	@Override
	public int compareTo(DataFilter o) {
		int result = this.message.compareTo(o.geMessage());
		if (result == 0) {
			if (this.timeOfRecieve - o.timeOfRecieve > 30000) {
				return this.timeOfRecieve.compareTo(o.timeOfRecieve);
			}
		}
		return result;
	}

}
