package com.gestmemapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import com.gestmemapi.model.Document;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.Speciality;

import net.bytebuddy.TypeCache.Sort;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    Optional<Document> findById(Long id);

    Optional<Document> findByTitle(String title);

    Iterable<Document> findAll(Sort sort);

    Iterable<Document> findAllBySupervisor(Person Supervisor, Sort sort);

    Iterable<Document> findAllByIsPublished(Integer isPublished, Sort sort);

    Iterable<Document> findAllByIsValidated(Integer isValidated, Sort sort);

    Iterable<Document> findAllByStudent(Person student, Sort sort);

    Iterable<Document> findAllBySpeciality(Speciality speciality, Sort sort);

}