package com.interlogicatest.phonechecker.controller.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.service.ManageDataService;
import com.interlogicatest.phonechecker.service.ManageFileService;
import com.interlogicatest.phonechecker.service.ValidationService;

@RestController
public class ProcessController {
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	ManageFileService exportExcelService;
	
	@Autowired
	ManageDataService manageDataService;
	
	@Autowired
	ManageFileService manageFileService;
	
	@RequestMapping("/api/massivechek")
	public ArrayList<PhoneNumber> massiveCheck() {
		
		return null;
	}
	
	@RequestMapping("/api/checknumber")
	public PhoneNumber checkSingleNumber(@RequestParam(value="number", required=true) String number) {
		return validationService.validateNumber("1", number);
	}
	
	@RequestMapping("/api/createResulFile")
	public void createCorrectNumbersFile(@RequestParam(value="correct", required=true) boolean correct) {
		manageFileService.exportValidatedNumbers(correct);
	}
	
	@RequestMapping(value="/uploadExcelFile", method = RequestMethod.POST)
	private void readExcel(@RequestParam("file") MultipartFile file) {
		manageFileService.saveNumbersByFile(file);
		manageFileService.exportValidatedNumbers(true);
		manageFileService.exportValidatedNumbers(false);
	}
}
