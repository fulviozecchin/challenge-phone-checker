package com.interlogicatest.phonechecker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.repository.PhoneNumberRepository;

@Service
public class ManageDataServiceImpl implements ManageDataService {
	
	@Autowired
	PhoneNumberRepository phoneNumberRepository;
	
	@Override
	public void insertNumber(PhoneNumber phoneNumber) {
		try {
			phoneNumberRepository.save(phoneNumber);
		} catch(Exception e) {
			throw new RuntimeException("Database Exception trying to insert phone number!", e);
		}
	}
	
	@Override
	public Optional<List<PhoneNumber>> getCorrectNumbers() {
		return phoneNumberRepository.findByIsValidTrue();
	}
	
	@Override
	public Optional<List<PhoneNumber>> getWrongNumbers() {
		return phoneNumberRepository.findByIsValidFalse();
	}
}
