package com.phonechecker.utils;

/**
 * This Enumeration represents the possible prefixes valid for a South African telephone number.
 * 
 * @author Fulvio Zecchin
 *
 */
public enum PhonePrefixesEnum {
	
	SOUTH_AFRICA("27"),
	SOUTH_AFRICA_2("+27"),
	SOUTH_AFRICA_3("0027"),
	;

	//This string is the phone suffix
	private String value;
	
	PhonePrefixesEnum(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
