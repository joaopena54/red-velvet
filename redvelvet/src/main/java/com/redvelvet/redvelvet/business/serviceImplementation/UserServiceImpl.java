package com.redvelvet.redvelvet.business.serviceImplementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.redvelvet.redvelvet.business.dtos.UserDTO;
import com.redvelvet.redvelvet.business.entities.Role;
import com.redvelvet.redvelvet.business.entities.User;
import com.redvelvet.redvelvet.business.repositories.RoleRepository;
import com.redvelvet.redvelvet.business.repositories.UserCredRepository;
import com.redvelvet.redvelvet.business.services.UserService;
import com.redvelvet.redvelvet.enums.RoleType;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCredRepository userCredRepository;

    @Override
    public User registerUser(UserDTO userDTO) {

        Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.findByName(RoleType.ROLE_USER).get());
        
        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail(), roles);

        return userCredRepository.save(user);

    }

    @Override
    public User findUserCredentialsByUsername(String username) {
        
        return userCredRepository.findByUsername(username).orElse(null);

    }

    @Override
    public User findUserCredentialsByEmail(String email){

        return userCredRepository.findByEmail(email).orElse(null);

    }
    
}
