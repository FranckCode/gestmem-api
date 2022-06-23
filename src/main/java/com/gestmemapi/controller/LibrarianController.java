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

//@CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/*")
public class LibrarianController {

	@Autowired
	private LibrarianService librarianService;	
	
	//controlleur pour la récupération de tous les utilisateurs
	@GetMapping(value = "/librarians")
	public ResponseEntity<Iterable<Person>> getAllUsers() {
		Iterable<Person> librarians = librarianService.getAllLibrarians();
		return new ResponseEntity<Iterable<Person>>(librarians, HttpStatus.OK);
	}
	
}
