package com.interlogicatest.phonechecker.service;

import com.interlogicatest.phonechecker.model.PhoneNumber;

/**
 * This interface contains methods which regarding the database management.
 * 
 * @author Fulvio Zecchin
 *
 */
public interface ManageDataService {

	public void insertNumber(PhoneNumber phoneNumber);
}
