package com.redvelvet.redvelvet.business.services;

import com.redvelvet.redvelvet.business.dtos.UserDTO;
import com.redvelvet.redvelvet.business.entities.User;

public interface UserService {
    
    public User registerUser(UserDTO userDTO);

    public User findUserCredentialsByUsername (String username);

    public User findUserCredentialsByEmail (String email);

}
