package com.interlogicatest.phonechecker.service;

import java.util.List;

import com.interlogicatest.phonechecker.model.PhoneNumber;

/**
 * This interface contains methods which regarding the database management.
 * 
 * @author Fulvio Zecchin
 *
 */
public interface ManageDataService {

	public void insertNumber(PhoneNumber phoneNumber);
	
	public List<PhoneNumber> getCorrectNumbers();
	
	public List<PhoneNumber> getWrongNumbers();
}
