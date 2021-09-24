package com.phonechecker.service;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import com.phonechecker.model.PhoneNumber;

/**
 * This interface contains methods which regarding the excel file creation.
 * 
 * @author Fulvio Zecchin
 *
 */
public interface ManageFileService {
	
	/**
	 * Method to export in a file (.xlsx) all correct or wrong numbers.
	 */
	public abstract void exportValidatedNumbers(boolean correct);

	
	/**
	 * Method to parse all rows in file and store data.
	 * 
	 * @param file is file to parse
	 */
	public abstract void saveNumbersByFile(MultipartFile file);
	
	/**
	 * Method to write a number in excel row
	 * @param number is phone number object to write
	 * @param row is the row in which we want to write it
	 * 
	 */
	public abstract void writeNumber(PhoneNumber number, Row row);

}
