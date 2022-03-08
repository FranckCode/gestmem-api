package com.gestmemapi.service;


import java.util.Collection;
import java.util.Optional;

import com.gestmemapi.exception.*;
import com.gestmemapi.model.Person;

public interface IPersonService {

	Collection<Person> getAllPersons();
	
	Optional<Person> getPersonById(Long id) throws BusinessResourceException;
	
	Optional<Person> findPersonByEmail(String email) throws BusinessResourceException;
	
	Person saveOrUpdatePerson(Person person) throws BusinessResourceException;
	
	// void deletePerson(Long id) throws BusinessResourceException;

	Optional<Person> findByEmailAndPassword(String login, String password) throws BusinessResourceException;

}
