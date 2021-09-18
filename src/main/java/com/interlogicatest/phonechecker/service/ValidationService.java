package com.interlogicatest.phonechecker.service;

import com.interlogicatest.phonechecker.model.PhoneNumber;

/**
 * This interface contains methods which regarding the phone number validation.
 * 
 * @author Fulvio Zecchin
 *
 */
public interface ValidationService {
	
	/**
	 * It's a  method with business logic to validate the phone number.
	 * @param id is the id of the row
	 * @param number is the phone number
	 * @return PhoneNumber is a model (correct or wrong)
	 * 
	 */
	public abstract PhoneNumber validateNumber(String id, String number);
}
