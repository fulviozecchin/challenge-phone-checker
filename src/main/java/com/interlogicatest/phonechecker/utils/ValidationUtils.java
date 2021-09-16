package com.interlogicatest.phonechecker.utils;

/**
 * This is a utils class which contains the methods that practically perform the number validation.
 * 
 * @author Fulvio Zecchin
 *
 */
public class ValidationUtils {
	
	//This is a regex which means "is not a number"
	private final static String REGEX_FOR_NUMBER = "[^\\d+]";
	
	//Remove all not digit chars from number
	public static String clearString(String number) {
		return number.replaceAll(REGEX_FOR_NUMBER, "");
	}
	
	//Checking correct length (11)
	public static boolean checkLength(String number) {
		return ( !(number.isBlank()) && (number.length() == 11) ) ? true : false;
	}
	
	//Checking correct suffix (27)
	public static boolean checkSuffix(String number) {
		return ( !(number.isBlank()) && (number.startsWith("27")) ) ? true : false;
	}
	
	//Checking if missing only suffix
	public static boolean checkMissingSuffix(String number) {
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
}
