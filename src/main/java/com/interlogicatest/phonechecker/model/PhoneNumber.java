package com.interlogicatest.phonechecker.model;

import javax.persistence.*;

/**
 * This class represent the Entity of phone number.
 * It's contains the builder pattern to create specific object.
 * 
 * @author Fulvio Zecchin
 *
 */
@Entity
@Table(name = "phonenumbers")
public class PhoneNumber {
	
	/**
	 * The row ID
	 * 
	 */
	@Id
	private String id;
	
	/**
	 * The phone number
	 * 
	 */
	@Column(name = "number")
	private String number;
	
	/**
	 * Is the boolean that identify the validity of number.
	 * It is true if a number is valid, false otherwise
	 * 
	 */
	@Column(name ="isvalid")
	private boolean isValid;
	
	/**
	 * This String contains a correction description (if we can correct a number)
	 * or the reason why a number is incorrect.
	 * 
	 */
	@Column(name = "correctionorerror")
	private String correctionOrError;
	
	//protected constructor to create the entity
	protected PhoneNumber() {};
	
	/**
	 * The private object constructor.
	 * Is mandatory to use the Builder to create an object.
	 * @param id is the id
	 * @param number is the phone number
	 * @param isValid is the boolean if phone number is correct
	 * @param correctionOrErrorString is the String with text with correction or with errors
	 */
	private PhoneNumber(String id, String number, boolean isValid, String correctionOrErrorString) {
		this.id = id;
		this.number = number;
		this.isValid = isValid;
		this.correctionOrError = correctionOrErrorString;
	}
	
	//Builder design pattern implementation
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
		return correctionOrError;
	}
	public void setCorrectionOrErrorString(String correctionOrErrorString) {
		this.correctionOrError = correctionOrErrorString;
	}
}
