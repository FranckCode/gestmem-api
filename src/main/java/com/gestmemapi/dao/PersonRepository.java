package com.gestmemapi.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.gestmemapi.model.Person;
import com.gestmemapi.model.Role;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    Iterable<Person> findAllById(Long id);

    Iterable<Person> findAll(Sort sort);

    Iterable<Person> findAllByRole(Role role);

}
