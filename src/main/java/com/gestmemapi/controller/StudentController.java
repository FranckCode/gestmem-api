package com.gestmemapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestmemapi.service.PersonService;

@CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/student/*")
public class StudentController {

	@Autowired
	private PersonService personService;	
	
}
