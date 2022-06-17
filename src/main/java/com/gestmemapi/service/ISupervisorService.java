package com.gestmemapi.service;

import com.gestmemapi.model.Person;

public interface ISupervisorService {
    
    Iterable<Person> getAllSupervisors();

}
