package com.gestmemapi.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.ResponseMessage;
import com.gestmemapi.service.PersonService;

// @CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/*")
public class PersonController {

	@Autowired
	private PersonService personService;	

	// controlleur pour l'authentification d'une personne
	@PostMapping(value = "/login")
	public ResponseEntity<Long> findPersonByEmailAndPassword(@RequestBody Person person) {
		
		Optional<Person> personFound = personService.findByEmailAndPassword(person.getEmail(), person.getPassword());
		return new ResponseEntity<Long>(personFound.get().getId(), HttpStatus.FOUND);
	}

	//controlleur pour l'ajout d'un nouvel utilisateur
	@PostMapping(value = "/person")
	@Transactional
	public ResponseEntity<Person> saveUser(@RequestBody Person person) {
		Person personSaved = personService.saveOrUpdatePerson(person);		
 		return new ResponseEntity<Person>(personSaved, HttpStatus.CREATED);
 	}

	// controller pour la mise à jour d'un utilisateur
	@PutMapping(value = "/person")
	public ResponseEntity<Person> updateUser(@RequestBody Person person) {
		Person personSaved = personService.saveOrUpdatePerson(person);		
		return new ResponseEntity<Person>(personSaved, HttpStatus.CREATED);
	}
	  
	//controlleur pour la récupération de tous les utilisateurs
	@GetMapping(value = "/persons")
	public ResponseEntity<Iterable<Person>> getAllUsers() {
		Iterable<Person> persons = personService.getAllPersons();
		return new ResponseEntity<Iterable<Person>>(persons, HttpStatus.OK);
	}
	  
	//controlleur pour la récupération de tous les utilisateurs
	@GetMapping(value = "/person")
	public ResponseEntity<Optional<Person>> getUserById(@RequestParam int id) {
		Optional<Person> myPerson = personService.getPersonById(Long.valueOf(id));
		return new ResponseEntity<Optional<Person>>(myPerson, HttpStatus.FOUND);
	}

	//controlleur pour la récupération de tous les utilisateurs
	@DeleteMapping(value = "/person")
	public ResponseEntity<ResponseMessage> deleteUserById(@RequestParam int id) {
		try {

            personService.deletePerson(Long.valueOf(id));

			String message = "L'utilisateur a été supprimé avec succèss";

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

          } catch (BusinessResourceException e) {

            String message = "Nous n'avons pas réussi à supprimer l'utilisateur";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));

          }
	}

}
