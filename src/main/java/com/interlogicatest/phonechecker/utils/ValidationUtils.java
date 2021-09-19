package com.interlogicatest.phonechecker.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.interlogicatest.phonechecker.model.PhoneNumber;

/**
 * This is a utils class which contains the methods that practically perform the number validation.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ValidationUtils {
	
	//This is a regex which means "is not a number"
	private final static String REGEX_FOR_REMOVE_NOT_NUMBER = "[^\\d+]";
	
	//This is a regex which meas "is a number"
	private final static String REGEX_FOR_IDENTIFY_NUMBER = "[0-9]+";
	
	/**
	 * It's a util method with business logic to validate the phone number.
	 * @param id is the id of the row
	 * @param number is the phone number
	 * @return PhoneNumber is a model (correct or wrong)
	 * 
	 */
	public static PhoneNumber validateNumber(String id, String number) {
		
		//If starts with 27
		if(ValidationUtils.checkPrefix(number)) {
			
			//if == 11 and contains only digits is OK
			if(ValidationUtils.hasCorrectLengthAndOnlyDigits(number)) {
				PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
						.valid(true)
						.build();
				return phoneNumber;
			}
			
			//else take first 11 chars
			else {
				//if is > 11
				if(ValidationUtils.checkIsBig(number)) {
					//take first 11 chars
					String cutNumber = number.substring(0, 11);
					//and if are only digits is OK
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
		
		//else if == 9 and contains only digits
		else if(ValidationUtils.checkMissingPrefix(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//add prefix 27 and is OK
			String correctedNum = ValidationUtils.addPrefix(number);
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, correctedNum)
					.valid(true)
					.textCorrectionError(TextUtilsEnum.ADDED_PREFIX.getText())
					.build();
			return phoneNumber;
		}

		//else if < 9 and contains only digits
		else if(ValidationUtils.checkIsSmall(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//is NOT OK (too small)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(TextUtilsEnum.FEW_DIGITS.getText())
					.build();
			return phoneNumber;
		}
		
		//else if == 11 and contains only digits
		else if(ValidationUtils.checkLength(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//is NOT OK (wrong prefix)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(TextUtilsEnum.WRONG_SUFFIX.getText())
					.build();
			return phoneNumber;
		}
		
		//else if > 11 and contains only digits
		else if(ValidationUtils.checkIsBig(number)
				&& ValidationUtils.hasOnlyDigits(number)) {
			//is NOT OK (too long)
			PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number)
					.valid(false)
					.textCorrectionError(TextUtilsEnum.TOO_MANY_DIGITS.getText())
					.build();
			return phoneNumber;
		}
		
		//last chance
		else {
			//clean the string from everything that is not a digit and try to validate it
			String clearNumber = ValidationUtils.clearString(number);
			
			//if remains string strarts with 27 and is exactly 11 chars it's OK
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
	
	//Remove all not digit chars from number
	public static String clearString(String number) {
		return number.replaceAll(REGEX_FOR_REMOVE_NOT_NUMBER, "");
	}
	
	//If length is 11 and contains only digits
	public static boolean hasCorrectLengthAndOnlyDigits(String number) {
		return ( (number.length() == 11) && hasOnlyDigits(number) );
	}
	
	//If contains only digits
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
	
	//Create the file headers
	public static void createCorrectFileHeader(XSSFSheet sheet, boolean isCorrectFile) {
		if(sheet != null) {
			//Header Row
			Row row = sheet.createRow(0);
			
			//Column for ID 
			Cell cellID = row.createCell(0);
			cellID.setCellValue(TextUtilsEnum.ROW_ID_HEADER.getText());
			
			//Column for Number
			Cell cellNumber = row.createCell(1);
			cellNumber.setCellValue(TextUtilsEnum.NUMBER_HEADER.getText());
			
			//Column for correction/error
			Cell cellDescrError = row.createCell(2);
			if(isCorrectFile) {
				cellDescrError.setCellValue(TextUtilsEnum.CORRECTION_HEADER.getText());
			} else cellDescrError.setCellValue(TextUtilsEnum.ERROR_HEADER.getText());
		}
		
	}
}
