package com.interlogicatest.phonechecker.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.interlogicatest.phonechecker.model.PhoneNumber;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {
	
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
