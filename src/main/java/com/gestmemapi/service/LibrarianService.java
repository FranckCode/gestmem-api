package com.gestmemapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestmemapi.dao.PersonRepository;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.Role;

@Service
public class LibrarianService implements ILibrarianService {
	
	private PersonRepository personRepository;

    private RoleService roleService;
	
	public LibrarianService() {
		super();
	}
	
	@Autowired
	public LibrarianService(PersonRepository personRepository, RoleService roleService) {
		super();
		this.personRepository = personRepository;
        this.roleService = roleService;
	}
	
	@Override
	public Iterable<Person> getAllLibrarians() {
		return personRepository.findAllByRole(new Role(roleService.getRoleId("librarian")));
	}	
		
}