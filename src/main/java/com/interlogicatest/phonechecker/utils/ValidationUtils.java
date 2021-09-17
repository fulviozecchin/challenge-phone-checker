package com.interlogicatest.phonechecker.utils;

import com.interlogicatest.phonechecker.model.PhoneNumber;

/**
 * This is a utils class which contains the methods that practically perform the number validation.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ValidationUtils {
	
	/**
	 * It's a util method with business logic to validate the phone number.
	 * @param id is the id of the row
	 * @param number is the phone number
	 * @return PhoneNumber is a model (correct or wrong)
	 * 
	 */
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
								.textCorrectionError(DescriptionText.REMOVED_EXCESS.getText())
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
					.textCorrectionError(DescriptionText.ADDED_PREFIX.getText())
					.build();
			return phoneNumber;
		}

		//altrimenti se < 9 e solo numeri
		else if(ValidationUtils.checkIsSmall(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//non è OK (troppo piccolo)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(DescriptionText.FEW_DIGITS.getText())
					.build();
			return phoneNumber;
		}
		
		//altrimenti se == 11 e solo numeri
		else if(ValidationUtils.checkLength(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//non è OK (suffisso sbagliato)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(DescriptionText.WRONG_SUFFIX.getText())
					.build();
			return phoneNumber;
		}
		
		//altrimenti se > 11 e solo numeri 
		else if(ValidationUtils.checkIsBig(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//non è OK (numero troppo lungo)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(DescriptionText.TOO_MANY_DIGITS.getText())
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
						.textCorrectionError(DescriptionText.REMOVED_EXCESS.getText())
						.build();
				return phoneNumber;
			}
		}

		PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
				.valid(false)
				.textCorrectionError(DescriptionText.GENERIC_ERROR.getText())
				.build();
		return phoneNumber;
	}
	
	//This is a regex which means "is not a number"
	private final static String REGEX_FOR_REMOVE_NOT_NUMBER = "[^\\d+]";
	
	//This is a regex which meas "is a number"
	private final static String REGEX_FOR_IDENTIFY_NUMBER = "[0-9]+";
	
	//Remove all not digit chars from number
	public static String clearString(String number) {
		return number.replaceAll(REGEX_FOR_REMOVE_NOT_NUMBER, "");
	}
	
	//Se uguale a 11 e solo numeri
	public static boolean hasCorrectLengthAndOnlyDigits(String number) {
		return ( (number.length() == 11) && hasOnlyDigits(number) );
	}
	
	//Se contiene solo numeri
	public static boolean hasOnlyDigits(String number) {
		return number.matches(REGEX_FOR_IDENTIFY_NUMBER);
	}
	
	//Checking correct length (11)
	public static boolean checkLength(String number) {
		return ( !(number.isBlank()) && (number.length() == 11) ) ? true : false;
	}
	
	//Checking correct prefix (27)
	public static boolean checkPrefix(String number) {
		return ( !(number.isBlank()) && 
				(
					(number.startsWith(PhonePrefixesEnum.SOUTH_AFRICA.getValue())) ||
					(number.startsWith(PhonePrefixesEnum.SOUTH_AFRICA_2.getValue())) ||
					(number.startsWith(PhonePrefixesEnum.SOUTH_AFRICA_3.getValue()))
				) 
			   ) ? true : false;
	}
	
	//Checking if missing only suffix
	public static boolean checkMissingPrefix(String number) {
		return ( !(number.isBlank()) && (number.length() == 9) ) ? true : false;
	}
	
	//Checking if is smaller than 9 
	public static boolean checkIsSmall(String number) {
		return ( !(number.isBlank()) && (number.length() < 9)  ) ? true : false;
	}
	
	//Checking if is bigger than 11 
	public static boolean checkIsBig(String number) {
		return ( !(number.isBlank()) && (number.length() > 11)  ) ? true : false;
	}
	
	//Correct the phone number adding relative suffix
	public static String addPrefix(String number) {
		return PhonePrefixesEnum.SOUTH_AFRICA.getValue() + number;
	}
}
