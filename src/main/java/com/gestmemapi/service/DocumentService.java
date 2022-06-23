package com.gestmemapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gestmemapi.controller.DocumentController;
import com.gestmemapi.dao.DocumentRepository;
import com.gestmemapi.dao.PersonRepository;
import com.gestmemapi.dao.SpecialityRepository;
import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.Document;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.ResponseMessage;
import com.gestmemapi.model.Speciality;

@Service
public class DocumentService implements IDocumentService {

  private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

  @Autowired
  private DocumentRepository documentRepository;

  private PersonRepository personRepository;

  private SpecialityRepository specialityRepository; 

  public DocumentService() {
		super();
	}
	
	@Autowired
	public DocumentService(DocumentRepository documentRepository, PersonRepository personRepository, SpecialityRepository specialityRepository) {
		super();
		this.documentRepository = documentRepository;
		this.personRepository = personRepository;
    this.specialityRepository = specialityRepository;
	}

  @Override
  @Transactional(readOnly = false)
  public Document store(Document document) throws IOException {

    try {
      
      addDocumentStudent(document);
      addDocumentSupervisor(document);
      addDocumentSpeciality(document);

      System.out.println("Document object created");

      Long millis = System.currentTimeMillis();
      document.setAddedDate(new Date(millis));
      document.setUpdatedDate(new Date(millis));
      document.setIsPublished(0);
      document.setIsValidated(0);
      document.setSize(Long.valueOf(document.getData().length));

      System.out.println("My document in the service");
      System.out.println(document);

      System.out.println("Store document to database");
      return documentRepository.save(document);
    }
    catch (BusinessResourceException e){
      logger.error("Erreur technique de création du document", e);
			throw new BusinessResourceException("StoreDocummentError", "Erreur technique de création du document: "+document.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }  

  //fonction pour l'attribution du role student à l'utilisateur de type student
	private void addDocumentSpeciality(Document document) {
    System.out.println("Speciality id: " + document.getSpeciality().getId().toString());
		Optional<Speciality> speciality = specialityRepository.findById(document.getSpeciality().getId());
		if (speciality.isPresent()){
			document.setSpeciality(speciality.get());
		}
		else{
			throw new BusinessResourceException("SearchSpecialityError", "Cette filiere n'existe pas",HttpStatus.NOT_FOUND);
		}
	}

  //fonction pour l'attribution du role student à l'utilisateur de type student
	private void addDocumentSupervisor(Document document) {
		Optional<Person> supervisor = personRepository.findById(document.getSupervisor().getId());
		if (supervisor.isPresent()){
			document.setSupervisor(supervisor.get());
		}
		else{
			throw new BusinessResourceException("SearchSupervisorError", "Cet encadreur n'existe pas",HttpStatus.NOT_FOUND);
		}
	}

  //fonction pour l'attribution du role student à l'utilisateur de type student
	private void addDocumentStudent(Document document) {
		Optional<Person> student = personRepository.findById(document.getStudent().getId());
		if (student.isPresent()){
			document.setStudent(student.get());
		}
		else{
			throw new BusinessResourceException("SearchStudentError", "Cet élève n'existe pas",HttpStatus.NOT_FOUND);
		}
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
