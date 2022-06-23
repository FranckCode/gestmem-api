package com.gestmemapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestmemapi.model.Person;
import com.gestmemapi.service.LibrarianService;
import com.gestmemapi.service.StudentService;
import com.gestmemapi.service.SupervisorService;

//@CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/*")
public class SupervisorController {

	@Autowired
	private SupervisorService supervisorService;	
	
	//controlleur pour la récupération de tous les utilisateurs
	@GetMapping(value = "/supervisors")
	public ResponseEntity<Iterable<Person>> getAllUsers() {
		Iterable<Person> supervisors = supervisorService.getAllSupervisors();
		return new ResponseEntity<Iterable<Person>>(supervisors, HttpStatus.FOUND);
	}
	
}
