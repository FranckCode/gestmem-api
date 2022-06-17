package com.gestmemapi.service;

import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.Role;

public interface IRoleService {
    
    Iterable<Role> getAllRoles();
    
    Long getRoleId(String roleName) throws BusinessResourceException;
}
