package com.gestmemapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.gestmemapi.model.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    Optional<Document> findById(Long id);

    Optional<Document> findByTitle(String title);

}