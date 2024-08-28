package com.terralogic.repository;

import com.terralogic.entity.Role;
import com.terralogic.model.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
