package com.interlogicatest.phonechecker.controller.api;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.utils.DescriptionText;
import com.interlogicatest.phonechecker.utils.ValidationUtils;

@RestController
public class ProcessController {
	
	@RequestMapping("/api/massivechek")
	public ArrayList<PhoneNumber> massiveCheck() {
		
		return null;
	}
	
	@RequestMapping("/api/checknumber/{number}")
	public PhoneNumber checkSingleNumber(@PathVariable String number) {
		
		return validateNumber("1", number);
	}

	@RequestMapping(value="/uploadExcelFile", method = RequestMethod.POST)
	private void readExcel(@RequestParam("file") MultipartFile file) {
		
		System.out.println("Inizio processo readExcel");
		
		try {
			InputStream is = new BufferedInputStream(file.getInputStream());
			
//            FileInputStream file = new FileInputStream(new File("src/main/resources/numeri.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(is);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            //Evaluator object
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            
            //Data Formatte
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
		
		Cell cell = row.createCell(0);
	    cell.setCellValue(number.getId());
	 
	    cell = row.createCell(1);
	    cell.setCellValue(number.getNumber());
	 
	    cell = row.createCell(2);
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
	
}
