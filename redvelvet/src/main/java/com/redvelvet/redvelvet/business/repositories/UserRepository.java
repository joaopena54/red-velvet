package com.redvelvet.redvelvet.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redvelvet.redvelvet.business.entities.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    
    Optional<User> findByUsername(String username);

}
