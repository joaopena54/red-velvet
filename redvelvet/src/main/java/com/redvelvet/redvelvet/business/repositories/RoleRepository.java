package com.redvelvet.redvelvet.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redvelvet.redvelvet.business.entities.Role;
import java.util.Optional;

import com.redvelvet.redvelvet.enums.RoleType;


public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleType name);
    
}
