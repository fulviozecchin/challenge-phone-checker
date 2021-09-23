package com.interlogicatest.phonechecker.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.repository.PhoneNumberRepository;

@Service
public class ManageDataServiceImpl implements ManageDataService {
	
	//Logger
	private static final Logger log = LoggerFactory.getLogger(ManageDataServiceImpl.class);
	
	@Autowired
	PhoneNumberRepository phoneNumberRepository;
	
	@Override
	public void insertNumber(PhoneNumber phoneNumber) {
		
		log.debug("Try to insert on database number {}", phoneNumber);
		
		try {
			phoneNumberRepository.save(phoneNumber);
		} catch(Exception e) {
			log.error("Error inserting number {}", phoneNumber);
			throw new RuntimeException("Database Exception trying to insert phone number!", e);
		}
	}
	
	@Override
	public List<PhoneNumber> getCorrectNumbers() {
		List<PhoneNumber> result = phoneNumberRepository.findByIsValidTrue().orElse(new ArrayList<PhoneNumber>());
		log.debug("Total correct numbers from db: {}", result.size());
		return result;
	}
	
	@Override
	public List<PhoneNumber> getWrongNumbers() {
		List<PhoneNumber> result = phoneNumberRepository.findByIsValidFalse().orElse(new ArrayList<PhoneNumber>());
		log.debug("Total wrong numbers from db: {}", result);
		return result;
	}
}
