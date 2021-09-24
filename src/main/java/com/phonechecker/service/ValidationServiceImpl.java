package com.phonechecker.service;

import org.springframework.stereotype.Service;

import com.phonechecker.model.PhoneNumber;
import com.phonechecker.utils.ValidationUtils;

@Service
public class ValidationServiceImpl implements ValidationService {
	
	@Override
	public PhoneNumber validateNumber(String id, String number) {
		return ValidationUtils.validateNumber(id, number);
	}
}
