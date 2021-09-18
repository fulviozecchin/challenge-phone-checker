package com.interlogicatest.phonechecker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interlogicatest.phonechecker.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
	
	//Get all correct numbers
	List<PhoneNumber> findByIsValidTrue();
	
	//Get all wrong numbers
	List<PhoneNumber> findByIsValidFalse();
}
