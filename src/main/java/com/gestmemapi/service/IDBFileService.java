package com.gestmemapi.service;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.gestmemapi.model.DBFile;

public interface IDBFileService {
    
    DBFile store(MultipartFile file) throws IOException;

    Optional<DBFile> getFile(Long id);

    Iterable<DBFile> getAllFiles();
}
