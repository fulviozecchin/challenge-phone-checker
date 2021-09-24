package com.phonechecker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonechecker.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
	
	//Get all correct numbers
	Optional<List<PhoneNumber>> findByIsValidTrue();
	
	//Get all wrong numbers
	Optional<List<PhoneNumber>> findByIsValidFalse();
}
