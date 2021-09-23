package com.interlogicatest.phonechecker.service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.utils.ValidationUtils;

@Service
public class ManageFileServiceImpl implements ManageFileService {
	
	private static final Logger log = LoggerFactory.getLogger(ManageFileServiceImpl.class);
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	ManageDataService manageDataService;
	
	@Override
	public void saveNumbersByFile(MultipartFile file) {
		
		try {
			
			log.info("Start reading excel to store");
			
			InputStream is = new BufferedInputStream(file.getInputStream());
			
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(is);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            //Data Formatter
            DataFormatter formatter = new DataFormatter();
            
            for(Row row : sheet) {
            	
            	Cell idValue = row.getCell(0);
            	Cell numValue = row.getCell(1);
            	
            	//Convert cells value into String
            	String idValueString = "" + formatter.formatCellValue(idValue);
            	String numValueString = "" + formatter.formatCellValue(numValue);
            	
            	//Validate number
            	if(idValue != null) {
            		
            		if(numValue != null) {
            			
            			//validation number
            			PhoneNumber result = validationService.validateNumber(idValueString, numValueString);
            			
            			//persist object in db
            			manageDataService.insertNumber(result);
            		}
            	}
            }
         } catch (Exception e) {
            e.printStackTrace();
            log.error("Error trying to persist data from file.");
        }
	}
	
	@Override
	public void writeNumber(PhoneNumber number, Row row) {
		
		Cell cell = row.createCell(0);
	    cell.setCellValue(number.getId());
	 
	    cell = row.createCell(1);
	    cell.setCellValue(number.getNumber());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(number.getCorrectionOrError());
	}
	
	@Override
	public void exportValidatedNumbers(boolean correct) {
		
		try {
			if(correct) {
				ArrayList<PhoneNumber> correctNumbers = new ArrayList<PhoneNumber> (manageDataService.getCorrectNumbers());
				
				if(correctNumbers.size() > 0) {
					
					log.info("Starting creating correct numbers file");
					
					XSSFWorkbook workbookCorrectNum = new XSSFWorkbook();
					XSSFSheet sheetCorrectNum = workbookCorrectNum.createSheet("Checked Numbers - Correct");
					
					int rowCount = 1;
					ValidationUtils.createCorrectFileHeader(sheetCorrectNum, correct);
					
					for(PhoneNumber p : correctNumbers) {
			    		Row r = sheetCorrectNum.createRow(++rowCount);
			    		writeNumber(p, r);
			    	}
			    	
					FileOutputStream outputStream = new FileOutputStream("FileCorrectNumbers.xlsx");
					
			    	try {
			    		
						workbookCorrectNum.write(outputStream);
						outputStream.close();
						
					} catch (Exception e) {
						log.error("Error trying to create correct numbers file");
						e.printStackTrace();
						
					} finally {outputStream.close();}
				}
				
			} else {
				ArrayList<PhoneNumber> wrongNumbers = new ArrayList<PhoneNumber> (manageDataService.getWrongNumbers());
				
				if(wrongNumbers.size() > 0) {
					
					log.info("Starting creating wrong numbers file");
					
					XSSFWorkbook workbookWrongNum = new XSSFWorkbook();
					XSSFSheet sheetWrongNum = workbookWrongNum.createSheet("Checked Numbers - Wrong");
					
					int rowCount = 1;
					ValidationUtils.createCorrectFileHeader(sheetWrongNum, correct);
					
					for(PhoneNumber p : wrongNumbers) {
			    		Row r = sheetWrongNum.createRow(++rowCount);
			    		writeNumber(p, r);
			    	}
					
					FileOutputStream outputStream = new FileOutputStream("FileWrongNumbers.xlsx");
			    	
					try {
						if(outputStream != null) workbookWrongNum.write(outputStream);
			    	} catch (Exception e) {
			    		log.error("Error trying to create wrong numbers file");
						e.printStackTrace();
					} finally {outputStream.close();}
				}
			}
		} catch (IOException e) {
			log.error("Error trying to export data to generate result file");
			e.printStackTrace();
		}
	}
}
