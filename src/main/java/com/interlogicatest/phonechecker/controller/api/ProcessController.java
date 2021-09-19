package com.interlogicatest.phonechecker.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interlogicatest.phonechecker.service.ManageFileService;

@RestController
public class ProcessController {
	
	@Autowired
	ManageFileService manageFileService;
	
	@RequestMapping(value="/uploadExcelFile", method = RequestMethod.POST)
	private void readExcel(@RequestParam("file") MultipartFile file) {
		manageFileService.saveNumbersByFile(file);
		manageFileService.exportValidatedNumbers(true);
		manageFileService.exportValidatedNumbers(false);
	}
}
