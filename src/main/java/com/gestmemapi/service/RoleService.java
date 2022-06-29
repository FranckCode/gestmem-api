package com.gestmemapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestmemapi.dao.RoleRepository;
import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.Role;

@Service
public class RoleService implements IRoleService {
	
	private RoleRepository roleRepository;
	
	public RoleService() {
		super();
	}
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

    @Override
    public Long getRoleId(String roleName) throws BusinessResourceException {
        Optional<Role> roleFound = roleRepository.findByRoleName(roleName);

        if (Boolean.FALSE.equals(roleFound.isPresent())){
			throw new BusinessResourceException("Role Not Found", "Aucun role avec ce nom :" + roleName);
		}

        return roleFound.get().getId();
    }

    @Override
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

	@Override
	public Optional<Role> getRole(String roleName) {
		// TODO Auto-generated method stub
		System.out.println("Role Name is: " + roleName);
		return roleRepository.findById(getRoleId(roleName));
	}
	
		
		
}