package com.interlogicatest.phonechecker.service;

import java.util.List;

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
		phoneNumberRepository.save(phoneNumber);
	}
	
	public List<PhoneNumber> getCorrectNumbers() {
		return phoneNumberRepository.findByIsValidTrue();
	}
	
	public List<PhoneNumber> getWrongNumbers() {
		return phoneNumberRepository.findByIsValidFalse();
	}
}
