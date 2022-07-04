package com.gestmemapi.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestmemapi.dao.PersonRepository;
import com.gestmemapi.dao.RoleRepository;
import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.Role;
import com.gestmemapi.model.Person;

@Service
public class PersonService implements IPersonService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
	private PersonRepository personRepository;
	private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public PersonService() {
		super();
	}
	
	@Autowired
	public PersonService(PersonRepository personRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.personRepository = personRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public Iterable<Person> getAllPersons() {
		return personRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}	
	

	@Override
	@Transactional(readOnly=false)
	public Person saveOrUpdatePerson(Person person) throws BusinessResourceException{
		try{
			if(null == person.getId()) {
				//pas d'Id --> création d'une Person
				addPersonRole(person);	//Ajout d'un rôle par défaut
				person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
			} else {
				//sinon, mise à jour d'une Person
				Optional<Person> PersonFromDB = getPersonById(person.getId());
				if(! bCryptPasswordEncoder.matches(person.getPassword(), PersonFromDB.get().getPassword())) {
					person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));//MAJ du mot de passe s'il a été modifié
				} else {
					person.setPassword(PersonFromDB.get().getPassword());//Sinon, on remet le password déjà haché
				}
				Optional<Role> roleFromBD = roleRepository.findById(((Role) PersonFromDB.get().getRole()).getId());
				Role role = roleFromBD.get();
				person.setRole(role);
			}
			Person result = personRepository.save(person);
			return  result;
		} catch(DataIntegrityViolationException ex){
			logger.error("Utilisateur non existant", ex);
			throw new BusinessResourceException("DuplicateValueError", "Un utilisateur existe déjà avec le compte : "+person.getEmail(), HttpStatus.CONFLICT);
		} catch (BusinessResourceException e) {
			logger.error("Utilisateur non existant", e);
			throw new BusinessResourceException("PersonNotFound", "Aucun utilisateur avec l'identifiant: "+person.getEmail(), HttpStatus.NOT_FOUND);
		} catch(Exception ex){
			logger.error("Erreur technique de création ou de mise à jour de l'utilisateur", ex);
			throw new BusinessResourceException("SaveOrUpdatePersonError", "Erreur technique de création ou de mise à jour de l'utilisateur: "+person.getEmail(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//fonction pour l'attribution du role student à l'utilisateur de type student
	private void addPersonRole(Person person) {
		Optional<Role> role = roleRepository.findById(Long.valueOf(person.getRole().getId()));
		if (role.isPresent()){
			person.setRole(role.get());
			person.setPersonActive(1);
		}
		else{
			throw new BusinessResourceException("SearchRoleError", "Ce Role n'existe pas",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//fonction qui renvoie une personne en fonction de son id
	@Override
	public Optional<Person> getPersonById(Long id) throws BusinessResourceException {
		Optional<Person> PersonFound = personRepository.findById(id);
		if (Boolean.FALSE.equals(PersonFound.isPresent())){
			throw new BusinessResourceException("Person Not Found", "Aucun utilisateur avec ce identifiant :");
		}
				return PersonFound;
	}

	@Override
	public Optional<Person> findByEmailAndPassword(String email, String password) throws BusinessResourceException{
		try {
			Optional<Person> PersonFound = this.findPersonByEmail(email);
			if(bCryptPasswordEncoder.matches(password, PersonFound.get().getPassword())) {
				return PersonFound;
			} else {
				throw new BusinessResourceException("PersonNotFound", "Mot de passe incorrect", HttpStatus.NOT_FOUND);
			}
		} catch (BusinessResourceException ex) {
			logger.error("Login ou mot de passe incorrect", ex);
			throw new BusinessResourceException("PersonNotFound", "Login ou mot de passe incorrect", HttpStatus.NOT_FOUND);
		}catch (Exception ex) {
			logger.error("Une erreur technique est survenue", ex);
			throw new BusinessResourceException("TechnicalError", "Une erreur technique est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Optional<Person> findPersonByEmail(String email) throws BusinessResourceException {
		
		Optional<Person> PersonFound = personRepository.findByEmail(email);
        if (Boolean.FALSE.equals(PersonFound.isPresent())) {
            throw new BusinessResourceException("Person Not Found", "L'utilisateur avec ce login n'existe pas :" + email);
        }
		return PersonFound;
	}

	@Override
	public void deletePerson(Long id) throws BusinessResourceException {
		// TODO Auto-generated method stub
		
	}	
}
