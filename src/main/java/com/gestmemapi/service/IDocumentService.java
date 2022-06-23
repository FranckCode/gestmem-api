package com.gestmemapi.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.gestmemapi.model.Document;

public interface IDocumentService {
    
    Document store(Document document) throws IOException;

    Document getDocument(Long id);

    Stream<Document> getAllDocuments();
}
