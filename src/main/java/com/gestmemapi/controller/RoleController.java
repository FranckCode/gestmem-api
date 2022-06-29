package com.gestmemapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestmemapi.model.Person;
import com.gestmemapi.model.Role;
import com.gestmemapi.service.RoleService;
import com.gestmemapi.service.StudentService;

//@CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/*")
public class RoleController {

	@Autowired
	private RoleService roleService;	
	
	//controlleur pour la récupération de tous les roles
	@GetMapping(value = "/roles")
	public ResponseEntity<Iterable<Role>> getAllRoles() {
		Iterable<Role> roles = roleService.getAllRoles();
		return new ResponseEntity<Iterable<Role>>(roles, HttpStatus.OK);
	}

	//controlleur pour la récupération du role "student"
	@GetMapping(value = "/role")
	public ResponseEntity<Role> getApiStudentRole(@RequestParam String name) {
		System.out.println("Role name in the controller is: "+ name);
		Role role = roleService.getRole(name).get();
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	
}
