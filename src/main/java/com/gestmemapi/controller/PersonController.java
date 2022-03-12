package com.gestmemapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestmemapi.model.Person;
import com.gestmemapi.service.PersonService;

@CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/person/*")
public class PersonController {

	@Autowired
	private PersonService personService;	

	// controlleur pour l'authentification d'une personne
	@PostMapping(value = "/login")
	public ResponseEntity<Long> findPersonByEmailAndPassword(@RequestBody Person person) {
		
		Optional<Person> personFound = personService.findByEmailAndPassword(person.getEmail(), person.getPassword());
		return new ResponseEntity<Long>(personFound.get().getId(), HttpStatus.FOUND);
	}
}
