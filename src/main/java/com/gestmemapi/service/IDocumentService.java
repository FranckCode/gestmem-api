package com.gestmemapi.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.gestmemapi.model.Document;

public interface IDocumentService {
    
    Document saveOrUpdateDocument(Document document) throws IOException;

    Optional<Document> getDocument(Long id);

    Iterable<Document> getAllDocuments();

    Iterable<Document> getAllSupervisorDocuments(Long id);

    Iterable<Document> getAllStudentDocuments(Long id);

    Iterable<Document> getAllValidatedDocuments();

    Iterable<Document> getAllPublishedDocuments();

    void publishDocument(Long id);

    void validateDocument(Long id);

    void unPublishDocument(Long id);

    void unValidateDocument(Long id);

}
