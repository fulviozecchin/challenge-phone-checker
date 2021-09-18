package com.interlogicatest.phonechecker.service;

import java.util.List;
import java.util.Optional;

import com.interlogicatest.phonechecker.model.PhoneNumber;

/**
 * This interface contains methods which regarding the database management.
 * 
 * @author Fulvio Zecchin
 *
 */
public interface ManageDataService {

	public void insertNumber(PhoneNumber phoneNumber);
	
	public Optional<List<PhoneNumber>> getCorrectNumbers();
	
	public Optional<List<PhoneNumber>> getWrongNumbers();
}
