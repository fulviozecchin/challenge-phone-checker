package com.interlogicatest.phonechecker.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.utils.DescriptionText;
import com.interlogicatest.phonechecker.utils.ValidationUtils;

@RestController
public class ProcessController {
	
	@RequestMapping("/api/massivechek")
	public ArrayList<PhoneNumber> massiveCheck() {
		
		return null;
	}

	@RequestMapping("/uploadExcelFile")
	private void readExcel2() {
		
		System.out.println("Inizio processo readExcel");
		
		try {
            FileInputStream file = new FileInputStream(new File("src/main/resources/numeri.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            //Evaluator object
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            
            //List which will contains all correct number
            ArrayList<PhoneNumber> correctNumber = new ArrayList<PhoneNumber>();
            
            //List which will contains all wrong number (if present)
            ArrayList<PhoneNumber> wrongNumber = new ArrayList<PhoneNumber>();
 
            for(Row row : sheet) {
            	
            	Cell idValue = row.getCell(0);
            	Cell numValue = row.getCell(1);
            	
            	//Convert cells value into String
            	String idValueString = "" + idValue;
            	String numValueString = "" + numValue;
            	
            	//Validate number
            	if(idValue != null) {
            		
            		if(numValue != null) {
            			
            			PhoneNumber result = validateNumber(idValueString, numValueString);
            			
            			if(result.isValid()) correctNumber.add(result);
            			else wrongNumber.add(result);
            		}
            	}
            }
            	
        	//creating file with correct phone numbers
        	if(correctNumber.size() > 0) {
        		
        		System.out.println("Stiamo creando un file per i numeri corretti");
        		
        		XSSFWorkbook workbookCorrectNum = new XSSFWorkbook();
            	XSSFSheet sheetCorrectNum = workbookCorrectNum.createSheet("Checked Numbers");
            	
            	int rowCount = 0;
            	
            	for(PhoneNumber p : correctNumber) {
            		Row r = sheetCorrectNum.createRow(++rowCount);
            		writeNumber(p, r);
            	}
            	
            	try (FileOutputStream outputStream = new FileOutputStream("FileCorrectNumbers.xlsx")) {
            		workbookCorrectNum.write(outputStream);
                }
        	}
            	
        	//creating file with wrong phone numbers
        	if(wrongNumber.size() > 0) {
        		
        		System.out.println("Stiamo creando un file per i numeri NON corretti");
        		
        		XSSFWorkbook workbookWrongNum = new XSSFWorkbook();
            	XSSFSheet sheetWrongNum = workbookWrongNum.createSheet("Checked Numbers");
            	
            	int rowCount = 0;
            	for(PhoneNumber p : wrongNumber) {
            		Row r = sheetWrongNum.createRow(++rowCount);
            		writeNumber(p, r);
            	}
            	
            	try (FileOutputStream outputStream = new FileOutputStream("FileWrongNumbers.xlsx")) {
            		workbookWrongNum.write(outputStream);
                }
        	}
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Utils method to write a number in excel row
	 * @param number is phone number object to write
	 * @param row is the row in which we want to write it
	 * 
	 */
	private void writeNumber(PhoneNumber number, Row row) {
		
		Cell cell = row.createCell(1);
	    cell.setCellValue(number.getId());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(number.getNumber());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(number.getCorrectionOrErrorString());
	}
	
	/**
	 * It's a util method with business logic to validate the phone number.
	 * @param id is the id of the row
	 * @param number is the phone number
	 * @return PhoneNumber is a model (correct or wrong)
	 * 
	 */
	private PhoneNumber validateNumber(String id, String number) {
		
		PhoneNumber phoneNumber = new PhoneNumber.Builder(id, number).build();
		
		String clearNumber = ValidationUtils.clearString(number);
			
		//if number length is correct
		if(ValidationUtils.checkLength(clearNumber)) {
			
			//if number suffix is correct
			if(ValidationUtils.checkSuffix(clearNumber)) {
				phoneNumber.setId(id);
				phoneNumber.setNumber(number);
				phoneNumber.setValid(true);
			} else { 												 //case in which the number length is correct, but doesn't start with 27 
				phoneNumber.setValid(false);
				phoneNumber.setCorrectionOrErrorString(DescriptionText.WRONG_SUFFIX.getText());
			}

		} else if(ValidationUtils.checkMissingSuffix(clearNumber)) { //if we miss only suffix
			String correctedNumber = "27" + clearNumber;
			phoneNumber.setId(id);
			phoneNumber.setNumber(correctedNumber);
			phoneNumber.setValid(true);
			phoneNumber.setCorrectionOrErrorString(DescriptionText.ADDED_SUFFIX.getText());

		} else if(ValidationUtils.checkIsSmall(clearNumber)) {      //if number is so small
			phoneNumber.setValid(false);
			phoneNumber.setCorrectionOrErrorString(DescriptionText.FEW_DIGITS.getText());

		} else if(ValidationUtils.checkIsBig(clearNumber)) { 		//if number is so big
			phoneNumber.setValid(false); 
			phoneNumber.setCorrectionOrErrorString(DescriptionText.TOO_MANY_DIGITS.getText());

		} else {													//default generic error
			phoneNumber.setValid(false); 
			phoneNumber.setCorrectionOrErrorString(DescriptionText.GENERIC_ERROR.getText());
		}
		
		return phoneNumber;
		
	}
	
}
