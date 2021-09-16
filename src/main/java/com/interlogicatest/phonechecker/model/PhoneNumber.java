package com.interlogicatest.phonechecker.model;

/**
 * This class represent the row of excel to parse.
 * It has the row ID and relative number
 * 
 * @author Fulvio Zecchin
 *
 */
public class PhoneNumber {
	
	/**
	 * The row ID
	 * 
	 */
	private String id;
	
	/**
	 * The phone number
	 * 
	 */
	private String number;
	
	/**
	 * Is the boolean that identify the validity of number.
	 * It is true if a number is valid, false otherwise
	 * 
	 */
	private boolean isValid;
	
	/**
	 * This String contains a correction description (if we can correct a number)
	 * or the reason why a number is incorrect.
	 * 
	 */
	private String correctionOrErrorString;
	
	/**
	 * The public object constructor
	 * @param id is the id
	 * @param number is the phone number
	 * @param isValid is the boolean if phone number is correct
	 * @param correctionOrErrorString is the String with text with correction or with errors
	 */
	public PhoneNumber(String id, String number, boolean isValid, String correctionOrErrorString) {
		this.id = id;
		this.number = number;
		this.isValid = isValid;
		this.correctionOrErrorString = correctionOrErrorString;
	}
	
	public static class Builder {
		
		private String id;
		private String number;
		private boolean isValid;
		private String correctionOrErrorString;
		
		public Builder(String id, String number) {
			this.id = id;
			this.number = number;
		}
		
		public Builder valid(boolean isValid) {
			this.isValid = isValid;
			return this;
		}
		
		public Builder textCorrectionError(String text) {
			this.correctionOrErrorString = text;
			return this;
		}
		
		public PhoneNumber build() {
			return new PhoneNumber(id, number, isValid, correctionOrErrorString);
		}
	}

	/*** Getters & Setters ***/
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getCorrectionOrErrorString() {
		return correctionOrErrorString;
	}

	public void setCorrectionOrErrorString(String correctionOrErrorString) {
		this.correctionOrErrorString = correctionOrErrorString;
	}

}
