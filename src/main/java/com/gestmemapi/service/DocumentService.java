package com.gestmemapi.service;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gestmemapi.controller.DocumentController;
import com.gestmemapi.dao.DocumentRepository;
import com.gestmemapi.dao.PersonRepository;
import com.gestmemapi.dao.SpecialityRepository;
import com.gestmemapi.model.Document;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.ResponseMessage;
import com.gestmemapi.model.Speciality;

@Service
public class DocumentService implements IDocumentService {

  @Autowired
  private DocumentRepository documentRepository;

  private PersonRepository personRepository;

  private SpecialityRepository specialityRepository; 

  public DocumentService() {
		super();
	}
	
	@Autowired
	public DocumentService(DocumentRepository documentRepository, PersonRepository personRepository) {
		super();
		this.documentRepository = documentRepository;
		this.personRepository = personRepository;
	}

  @Override
  public Document store(MultipartFile file, Document document) throws IOException {

    System.out.println("Get the name of the document");
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    System.out.println("Get the student of the document");
    //Optional<Person student = personRepository.findById(document.getStudent().getId());

    System.out.println("Get the supervisor of the document");
    //Optional<Person> supervisor = personRepository.findById(document.getSupervisor().getId());

    System.out.println("Get the specialty of the document");
    //<Speciality> speciality = specialityRepository.findById(document.getSpeciality().getId());

    

    System.out.println("Create document object to be stored");
    Document storedDocument = new Document(document.getTitle(), document.getSummary(), file.getOriginalFilename(), file.getBytes(), file.getContentType(), document.getStudent(), document.getSupervisor(), document.getSpeciality());

    System.out.println("Document object created");

    Long millis = System.currentTimeMillis();
    storedDocument.setAddedDate(new Date(millis));
    storedDocument.setUpdatedDate(new Date(millis));
    storedDocument.setIsPublished(0);
    storedDocument.setIsValidated(0);

    System.out.println(storedDocument);

    System.out.println("Store document to database");
    return documentRepository.save(storedDocument);

  }  

  @Override
  public Document getDocument(Long id) {
    return documentRepository.findById(id).get();
  }

  @Override
  public Stream<Document> getAllDocuments() {
    return documentRepository.findAll().stream();
  }

}
