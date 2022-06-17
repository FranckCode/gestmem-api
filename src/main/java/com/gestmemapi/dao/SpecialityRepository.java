package com.gestmemapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.gestmemapi.model.Speciality;

@Repository
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {

    Optional<Speciality> findById(Long id);

    Optional<Speciality> findBySpecialityName(String SpecialityName);

}