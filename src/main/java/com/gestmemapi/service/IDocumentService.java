package com.gestmemapi.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.gestmemapi.model.Document;

public interface IDocumentService {
    
    Document store(MultipartFile file, Document document) throws IOException;

    Document getDocument(Long id);

    Iterable<Document> getAllDocuments();
}
