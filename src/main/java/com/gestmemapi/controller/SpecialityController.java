package com.gestmemapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestmemapi.model.Person;
import com.gestmemapi.model.Role;
import com.gestmemapi.model.Speciality;
import com.gestmemapi.service.RoleService;
import com.gestmemapi.service.SpecialityService;
import com.gestmemapi.service.StudentService;

//@CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/*")
public class SpecialityController {

	@Autowired
	private SpecialityService specialityService;	
	
	//controlleur pour la récupération de tous les utilisateurs
	@GetMapping(value = "/specialities")
	public ResponseEntity<Iterable<Speciality>> getAllSpecialities() {
		Iterable<Speciality> specialities = specialityService.getAllSpecialities();
		return new ResponseEntity<Iterable<Speciality>>(specialities, HttpStatus.FOUND);
	}
	
}
