package com.gestmemapi.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.gestmemapi.model.Document;

public interface IDocumentService {
    
    Document store(MultipartFile file);

    Optional<Document> getDocument(Long id);

    Iterable<Document> getAllDocuments();
}
