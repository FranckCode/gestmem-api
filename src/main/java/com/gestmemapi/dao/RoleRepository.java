package com.gestmemapi.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.gestmemapi.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findById(Long id);

    // @Query(value="SELECT * FROM role WHERE role_id=:id", nativeQuery = true)
    // Optional<Role> getRoleById(@Param("cout") Integer id);   // Java8 Stream : on place la liste des rôles dans un Stream
}