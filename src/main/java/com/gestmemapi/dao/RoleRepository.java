package com.gestmemapi.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestmemapi.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("select role from Role role")
    Stream<Role> getAllRolesStream();   // Java8 Stream : on place la liste des rôles dans un Stream
}