package com.gestmemapi.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.gestmemapi.model.Document;

public interface IDocumentService {
    
    Document saveOrUpdateDocument(Document document) throws IOException;

    Optional<Document> getDocument(Long id);

    Iterable<Document> getAllDocuments();
}
