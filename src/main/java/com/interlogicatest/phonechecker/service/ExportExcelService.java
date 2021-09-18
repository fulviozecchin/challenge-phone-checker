package com.interlogicatest.phonechecker.service;

import org.apache.poi.ss.usermodel.Row;

import com.interlogicatest.phonechecker.model.PhoneNumber;

/**
 * This interface contains methods which regarding the excel file creation.
 * 
 * @author Fulvio Zecchin
 *
 */
public interface ExportExcelService {
	
	/**
	 * Utils method to write a number in excel row
	 * @param number is phone number object to write
	 * @param row is the row in which we want to write it
	 * 
	 */
	public abstract void writeNumber(PhoneNumber number, Row row);

}
