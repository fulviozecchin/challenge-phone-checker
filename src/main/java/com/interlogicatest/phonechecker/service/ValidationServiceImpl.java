package com.interlogicatest.phonechecker.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.utils.TextUtilsEnum;
import com.interlogicatest.phonechecker.utils.ValidationUtils;

@Service
public class ValidationServiceImpl implements ValidationService {
	
	@Override
	public PhoneNumber validateNumber(String id, String number) {
		
		//se inizia con 27
		if(ValidationUtils.checkPrefix(number)) {
			
			//se == 11 e solo numeri è OK
			if(ValidationUtils.hasCorrectLengthAndOnlyDigits(number)) {
				PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
						.valid(true)
						.build();
				return phoneNumber;
			}
			
			//altrimenti prendi i primi 11
			else {
				//se è > 11
				if(ValidationUtils.checkIsBig(number)) {
					//prendi i primi 11 caratteri
					String cutNumber = number.substring(0, 11);
					//e se solo numeri è OK
					if(ValidationUtils.hasOnlyDigits(cutNumber)) {
						PhoneNumber phoneNumber = new PhoneNumber.Builder(id, cutNumber)
								.valid(true)
								.textCorrectionError(TextUtilsEnum.REMOVED_EXCESS.getText())
								.build();
						return phoneNumber;
					}
				}
			}
		}
		
		//altrimenti se == 9 e solo numeri
		else if(ValidationUtils.checkMissingPrefix(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//aggiungi 27 ed è OK
			String correctedNum = ValidationUtils.addPrefix(number);
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, correctedNum)
					.valid(true)
					.textCorrectionError(TextUtilsEnum.ADDED_PREFIX.getText())
					.build();
			return phoneNumber;
		}

		//altrimenti se < 9 e solo numeri
		else if(ValidationUtils.checkIsSmall(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//non è OK (troppo piccolo)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(TextUtilsEnum.FEW_DIGITS.getText())
					.build();
			return phoneNumber;
		}
		
		//altrimenti se == 11 e solo numeri
		else if(ValidationUtils.checkLength(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//non è OK (suffisso sbagliato)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(TextUtilsEnum.WRONG_SUFFIX.getText())
					.build();
			return phoneNumber;
		}
		
		//altrimenti se > 11 e solo numeri 
		else if(ValidationUtils.checkIsBig(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//non è OK (numero troppo lungo)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(TextUtilsEnum.TOO_MANY_DIGITS.getText())
					.build();
			return phoneNumber;
		}
		
		//ultimo tentativo
		else {
			//infine pulisco la stringa da tutto cio' che non e' una cifra e provo a validarla
			String clearNumber = ValidationUtils.clearString(number);
			
			//se la stringa che rimane inizia con 27 ed e' lunga 11 ok
			if(ValidationUtils.checkPrefix(clearNumber)
					&& ValidationUtils.checkLength(clearNumber)) {
				PhoneNumber phoneNumber = new PhoneNumber.Builder(id, clearNumber)
						.valid(true)
						.textCorrectionError(TextUtilsEnum.REMOVED_EXCESS.getText())
						.build();
				return phoneNumber;
			}
		}

		PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
				.valid(false)
				.textCorrectionError(TextUtilsEnum.GENERIC_ERROR.getText())
				.build();
		return phoneNumber;
	}
	
}
