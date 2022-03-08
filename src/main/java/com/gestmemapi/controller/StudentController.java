package com.gestmemapi.controller;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestmemapi.model.Person;
import com.gestmemapi.service.PersonService;

@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600)
@RestController
@RequestMapping("/student/*")
public class StudentController {

	@Autowired
	private PersonService personService;	
	
	//controlleur pour la récupération de tous les utilisateurs
	@GetMapping(value = "/getStudents")
	public ResponseEntity<Collection<Person>> getAllUsers() {
		Collection<Person> persons = personService.getAllPersons();
		return new ResponseEntity<Collection<Person>>(persons, HttpStatus.FOUND);
	}

	//controlleur pour l'ajout d'un nouvel utilisateur
	@PostMapping(value = "/addStudent")
	@Transactional
	public ResponseEntity<Person> saveUser(@RequestBody Person person) {
		Person personSaved = personService.saveOrUpdatePerson(person);		
 		return new ResponseEntity<Person>(personSaved, HttpStatus.CREATED);
 	}

	// controller pour la mise à jour d'un utilisateur
	@PutMapping(value = "/updateStudent")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		Person personSaved = personService.saveOrUpdatePerson(person);		
 		return new ResponseEntity<Person>(personSaved, HttpStatus.CREATED);
 	}
}
