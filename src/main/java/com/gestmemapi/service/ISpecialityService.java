package com.gestmemapi.service;

import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.Speciality;

public interface ISpecialityService {
    
    Iterable<Speciality> getAllSpecialities();
    
    Long getSpecialityId(String specialityName) throws BusinessResourceException;
}
