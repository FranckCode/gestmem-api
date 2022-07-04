package com.gestmemapi.controller;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.util.StringUtils;

import com.gestmemapi.dao.DocumentRepository;
import com.gestmemapi.dao.PersonRepository;
import com.gestmemapi.dao.SpecialityRepository;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.ResponseFile;
import com.gestmemapi.model.Speciality;

import com.gestmemapi.model.Document;
import com.gestmemapi.model.ResponseMessage;
import com.gestmemapi.service.DocumentService;

//@CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/*")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

    @Autowired
	private DocumentRepository documentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SpecialityRepository specialityRepository;
	
	
	//controlleur pour la récupération de tous les utilisateurs
	//@PostMapping(value="/documents", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    @PostMapping(value = "/document", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    @Transactional
    public ResponseEntity<ResponseMessage> saveDocument(@RequestBody Document document){

        System.out.println("upload file controller");
        
        String message = "";
        
        try {

            /*byte[] b = new byte[20];
            new Random().nextBytes(b);

            System.out.println("b: ");
            System.out.println(b);

            document.setData(b);
            System.out.println("document.getData: ");
            System.out.println(document.getData());

            Document myDocument = new Document(document);
            */
            System.out.println("My document in the controller");
            System.out.println(document);
            

            Document uploadedDocument = documentService.saveOrUpdateDocument(document);

            message = "Uploaded the document successfully: " + document.getTitle();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

          } catch (IOException e) {

            message = "Could not upload the document: " + document.getTitle() + "!" + e.toString();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

          }

    }

    /*
    @GetMapping("/documents")
    public ResponseEntity<List<Document>> getListFiles() {
        List<Document> documents = documentService.getAllDocuments().map(dbFile -> {
        String fileDownloadUri = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/documents/")
            .path(dbFile.getId().toString())
            .toUriString();

        return new Document(dbFile.getTitle(), dbFile.getSummary(), dbFile.getName(), dbFile.getData(), dbFile.getType(), fileDownloadUri, dbFile.getStudent(), dbFile.getSupervisor(), dbFile.getSpeciality());
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @GetMapping("/documents")
    public ResponseEntity<Iterable<Document>> getListFiles() {
        Iterable<Document> documents = documentService.getAllDocuments();
        return new ResponseEntity<Iterable<Document>>(documents, HttpStatus.FOUND);
    }

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> getListDocuments() {
        List<Document> documents = documentService.getAllDocuments().map(dbFile -> extracted(dbFile)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    private Document extracted(Document dbFile) {
        String fileDownloadUri = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/documents")
            .path(dbFile.getId().toString())
            .toUriString();
        return new Document(dbFile.getTitle(), dbFile.getSummary(), dbFile.getName(), dbFile.getData(), dbFile.getType(), fileDownloadUri, dbFile.getStudent(), dbFile.getSupervisor(), dbFile.getSpeciality());
    }*/

    /*@GetMapping("/documents")
    public ResponseEntity<Document> getDocument(@RequestParam int id) {
        Document document = documentService.getDocument(Long.valueOf(id));

        String fileDownloadUri = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/documents/")
            .path(document.getId().toString())
            .toUriString();

        document.setUrl(fileDownloadUri);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
            .body(document);
    }*/


    @GetMapping("/documents")
    public ResponseEntity<Iterable<Document>> getListDocuments() {
        Iterable<Document> documents = documentService.getAllDocuments();
        return ResponseEntity.status(HttpStatus.OK).body(documents);

    }


    @GetMapping("/document")
    public ResponseEntity<Document> getDocumentById(@RequestParam int id) {
        Document document = documentService.getDocument(Long.valueOf(id)).get();
        return new ResponseEntity<Document>(document, HttpStatus.OK);
    }
        
}
