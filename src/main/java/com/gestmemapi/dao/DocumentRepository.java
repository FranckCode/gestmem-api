package com.gestmemapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.gestmemapi.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findById(Long id);

    Optional<Document> findByName(String Name);

}