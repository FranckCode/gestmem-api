package com.gestmemapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.gestmemapi.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByEmail(String email);
}
