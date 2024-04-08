package com.redvelvet.redvelvet.business.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redvelvet.redvelvet.business.entities.User;



public interface UserCredRepository extends JpaRepository<User,Long> {
     
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
