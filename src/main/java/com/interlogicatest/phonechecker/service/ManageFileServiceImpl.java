package com.interlogicatest.phonechecker.service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.utils.ValidationUtils;

@Service
public class ManageFileServiceImpl implements ManageFileService {
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	ManageDataService manageDataService;
	
	@Override
	public void saveNumbersByFile(MultipartFile file) {
		
		try {
			
			InputStream is = new BufferedInputStream(file.getInputStream());
			
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(is);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            //Data Formatter
            DataFormatter formatter = new DataFormatter();
            
            //List which will contains all correct number
            ArrayList<PhoneNumber> correctNumber = new ArrayList<PhoneNumber>();
            
            //List which will contains all wrong number (if present)
            ArrayList<PhoneNumber> wrongNumber = new ArrayList<PhoneNumber>();
 
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
            			
//            			if(result.isValid()) correctNumber.add(result);
//            			else wrongNumber.add(result);
            		}
            	}
            }
            	
        	//creating file with correct phone numbers
//        	if(correctNumber.size() > 0) {
//        		
//        		System.out.println("Stiamo creando un file per i numeri corretti");
//        		
//        		XSSFWorkbook workbookCorrectNum = new XSSFWorkbook();
//            	XSSFSheet sheetCorrectNum = workbookCorrectNum.createSheet("Checked Numbers");
//            	
//            	int rowCount = 1;
//            	
//            	//Create new file sheet header
//            	ValidationUtils.createCorrectFileHEader(sheetCorrectNum, true);
//            	
//            	for(PhoneNumber p : correctNumber) {
//            		Row r = sheetCorrectNum.createRow(++rowCount);
//            		exportExcelService.writeNumber(p, r);
//            	}
//            	
//            	try (FileOutputStream outputStream = new FileOutputStream("FileCorrectNumbers.xlsx")) {
//            		workbookCorrectNum.write(outputStream);
//                }
//        	}
            	
        	//creating file with wrong phone numbers
//        	if(wrongNumber.size() > 0) {
//        		
//        		System.out.println("Stiamo creando un file per i numeri NON corretti");
//        		
//        		XSSFWorkbook workbookWrongNum = new XSSFWorkbook();
//            	XSSFSheet sheetWrongNum = workbookWrongNum.createSheet("Checked Numbers");
//            	
//            	int rowCount = 1;
//            	
//            	//Create new file sheet header
//            	ValidationUtils.createCorrectFileHEader(sheetWrongNum, false);
//            	
//            	for(PhoneNumber p : wrongNumber) {
//            		
//            		Row r = sheetWrongNum.createRow(++rowCount);
//            		exportExcelService.writeNumber(p, r);
//            	}
//            	
//            	try (FileOutputStream outputStream = new FileOutputStream("FileWrongNumbers.xlsx")) {
//            		workbookWrongNum.write(outputStream);
//              }
//        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void writeNumber(PhoneNumber number, Row row) {
		
		Cell cell = row.createCell(0);
	    cell.setCellValue(number.getId());
	 
	    cell = row.createCell(1);
	    cell.setCellValue(number.getNumber());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(number.getCorrectionOrErrorString());
	}
}
