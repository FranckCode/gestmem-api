package com.gestmemapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestmemapi.dao.SpecialityRepository;
import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.Speciality;

@Service
public class SpecialityService implements ISpecialityService {
	
	private SpecialityRepository specialityRepository;
	
	public SpecialityService() {
		super();
	}
	
	@Autowired
	public SpecialityService(SpecialityRepository specialityRepository) {
		super();
		this.specialityRepository = specialityRepository;
	}

    @Override
    public Long getSpecialityId(String specialityName) throws BusinessResourceException {
        Optional<Speciality> specialityFound = specialityRepository.findBySpecialityName(specialityName);

        if (Boolean.FALSE.equals(specialityFound.isPresent())){
			throw new BusinessResourceException("Speciality Not Found", "Aucune spécialité avec ce nom :");
		}

        return specialityFound.get().getId();
    }

    @Override
    public Iterable<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }
	
		
		
}