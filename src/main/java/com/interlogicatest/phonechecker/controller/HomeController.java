package com.interlogicatest.phonechecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interlogicatest.phonechecker.model.PhoneNumber;
import com.interlogicatest.phonechecker.service.ValidationService;

@Controller
public class HomeController {
	
	@Autowired
	ValidationService validationService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
	@RequestMapping("/checknumber")
	public String checkSingleNumber(@RequestParam(value="number", required=true) String number,
			Model model) {
		PhoneNumber phoneNumber = validationService.validateNumber("1", number);
		
		model.addAttribute("phoneNumber", phoneNumber);
		
		return "singleresult";
	}
}
