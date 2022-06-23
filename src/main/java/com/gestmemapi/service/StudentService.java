package com.gestmemapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestmemapi.dao.PersonRepository;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.Role;

@Service
public class StudentService implements IStudentService {
	
	private PersonRepository personRepository;

    private RoleService roleService;
	
	public StudentService() {
		super();
	}
	
	@Autowired
	public StudentService(PersonRepository personRepository, RoleService roleService) {
		super();
		this.personRepository = personRepository;
        this.roleService = roleService;
	}
	
	@Override
	public Iterable<Person> getAllStudents() {
		return personRepository.findAllByRole(new Role(roleService.getRoleId("student")));
	}	
		
}