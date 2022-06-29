package com.gestmemapi.service;

import java.util.Optional;

import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.Role;

public interface IRoleService {
    
    Iterable<Role> getAllRoles();

    Optional<Role> getRole(String roleName);
    
    Long getRoleId(String roleName) throws BusinessResourceException;
}
