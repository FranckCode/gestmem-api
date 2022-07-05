package com.gestmemapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
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
import com.gestmemapi.dao.DBFileRepository;
import com.gestmemapi.dao.DocumentRepository;
import com.gestmemapi.dao.PersonRepository;
import com.gestmemapi.dao.SpecialityRepository;
import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.DBFile;
import com.gestmemapi.model.Document;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.ResponseMessage;
import com.gestmemapi.model.Speciality;

@Service
public class DocumentService implements IDocumentService {

  private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

  @Autowired
  private DocumentRepository documentRepository;

  @Autowired
  private DBFileRepository dbFileRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private SpecialityRepository specialityRepository; 

  /*public DocumentService() {
		super();
	}
	
	@Autowired
	public DocumentService(DocumentRepository documentRepository, PersonRepository personRepository, SpecialityRepository specialityRepository) {
		super();
		this.documentRepository = documentRepository;
		this.personRepository = personRepository;
    this.specialityRepository = specialityRepository;
	}*/

  	@Override
	@Transactional(readOnly=false)
	public Document saveOrUpdateDocument(Document document) throws IOException{
		try{

			if(null == document.getId()) {
				//pas d'Id --> création d'un document
				addDocumentStudent(document);
				addDocumentSupervisor(document);
				addDocumentSpeciality(document);

				Long millis = System.currentTimeMillis();
				document.setAddedDate(new Date(millis));
				document.setUpdatedDate(new Date(millis));
				document.setIsPublished(0);
				document.setIsValidated(0);

			} else {
				//sinon, mise à jour d'un document
				Optional<Document> documentFromDB = getDocument(document.getId());

				Optional<DBFile> fileFromDb = dbFileRepository.findById(((DBFile) documentFromDB.get().getFile()).getId());
				Optional<Person> studentFromDb = personRepository.findById(((Person) documentFromDB.get().getStudent()).getId());
				Optional<Person> supervisorFromDb = personRepository.findById(((Person) documentFromDB.get().getSupervisor()).getId());
				Optional<Speciality> specialityFromDb = specialityRepository.findById(((Speciality) documentFromDB.get().getSpeciality()).getId());

				Long millis = System.currentTimeMillis();
				document.setUpdatedDate(new Date(millis));
				document.setFile(fileFromDb.get());
				document.setStudent(studentFromDb.get());
				document.setSupervisor(supervisorFromDb.get());
				document.setSpeciality(specialityFromDb.get());
			}

			Document result = documentRepository.save(document);
			return  result;

		} catch(DataIntegrityViolationException ex){
			logger.error("Utilisateur non existant", ex);
			throw new BusinessResourceException("DuplicateValueError", "Un document existe déjà avec le compte : "+document.getTitle(), HttpStatus.CONFLICT);
		} catch (BusinessResourceException e) {
			logger.error("Utilisateur non existant", e);
			throw new BusinessResourceException("DocumentNotFound", "Aucun document avec l'identifiant: "+document.getTitle(), HttpStatus.NOT_FOUND);
		} catch(Exception ex){
			logger.error("Erreur technique de création ou de mise à jour de l'utilisateur", ex);
			throw new BusinessResourceException("SaveOrUpdateDocumentError", "Erreur technique de création ou de mise à jour du document: "+document.getTitle(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

  /*@Override
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

  }*/  

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
	public Optional<Document> getDocument(Long id) {
		Optional<Document> document = documentRepository.findById(id);
		document.get().getFile().setData(null);
		return document;
	}
	

	@Override
	public Iterable<Document> getAllDocuments() {
		Iterable<Document> documents = documentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		for (Document document : documents) {
			document.getFile().setData(null);
		}
		return documents;
	}

	

	@Override
	public void publishDocument(Long id) {
		Document document = documentRepository.findById(id).get();
		document.setIsPublished(1);
		Document result = documentRepository.save(document);
	}
	

	@Override
	public void unPublishDocument(Long id) {
		Document document = documentRepository.findById(id).get();
		document.setIsPublished(0);
		Document result = documentRepository.save(document);
	}

	@Override
	public void unValidateDocument(Long id) {
		Document document = documentRepository.findById(id).get();
		document.setIsValidated(0);
		Document result = documentRepository.save(document);
	}

	@Override
	public void validateDocument(Long id) {
		Document document = documentRepository.findById(id).get();
		document.setIsValidated(1);
		Document result = documentRepository.save(document);
	}

	
	@Override
	public Iterable<Document> getAllPublishedDocuments() {
		Iterable<Document> documents = documentRepository.findAllByIsPublished(1, Sort.by(Sort.Direction.DESC, "id"));
		for (Document document : documents) {
			document.getFile().setData(null);
		}
		return documents;
	}
	
	@Override
	public Iterable<Document> getAllValidatedDocuments() {
		Iterable<Document> documents = documentRepository.findAllByIsValidated(1, Sort.by(Sort.Direction.DESC, "id"));
		for (Document document : documents) {
			document.getFile().setData(null);
		}
		return documents;
	}

	@Override
	public Iterable<Document> getAllStudentDocuments(Long id) {
		Optional<Person> student = personRepository.findById(id);
		Iterable<Document> documents = documentRepository.findAllByStudent(student.get(), Sort.by(Sort.Direction.DESC, "id"));
		for (Document document : documents) {
			document.getFile().setData(null);
		}
		return documents;
	}

	@Override
	public Iterable<Document> getAllSupervisorDocuments(Long id) {
		Optional<Person> supervisor = personRepository.findById(id);
		Iterable<Document> documents = documentRepository.findAllBySupervisor(supervisor.get(), Sort.by(Sort.Direction.DESC, "id"));
		for (Document document : documents) {
			document.getFile().setData(null);
		}
		return documents;
	}
	
}