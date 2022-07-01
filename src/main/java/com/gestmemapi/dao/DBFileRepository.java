package com.gestmemapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.gestmemapi.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long> {

    Optional<DBFile> findById(Long id);

    Optional<DBFile> findByName(String name);

}