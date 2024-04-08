package com.redvelvet.redvelvet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redvelvet.redvelvet.business.entities.Role;
import com.redvelvet.redvelvet.business.repositories.RoleRepository;
import com.redvelvet.redvelvet.enums.RoleType;

@Service
public class RoleInitializationService {
    
    @Autowired
    private RoleRepository roleRepository;

    public void initRoles() {
        if (roleRepository.count() == 0) {
            Role roleUser = new Role(RoleType.ROLE_USER);
            roleRepository.save(roleUser);

            Role roleAdmin = new Role(RoleType.ROLE_ADMIN);
            roleRepository.save(roleAdmin);

            // Add more roles as needed
        }
    }
}
