package com.redvelvet.redvelvet.presentation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

import com.redvelvet.redvelvet.business.dtos.UserDTO;
import com.redvelvet.redvelvet.business.entities.User;
import com.redvelvet.redvelvet.business.entities.UserPrincipal;
import com.redvelvet.redvelvet.business.payload.responsePayload.JwtResponse;
import com.redvelvet.redvelvet.business.payload.responsePayload.MessageResponse;
import com.redvelvet.redvelvet.business.services.UserService;
import com.redvelvet.redvelvet.security.jwt.JwtUtils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> postUserCred(@RequestBody UserDTO userDTO) {

        User existingUsername = userService.findUserCredentialsByUsername(userDTO.getUsername());

        User existingUser = userService.findUserCredentialsByEmail(userDTO.getEmail());


        if(existingUsername != null) {

            return  ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));

        }

        if(existingUser != null) {

            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));

        }

        userService.registerUser(userDTO);

    //         Set<String> strRoles = signUpRequest.getRole();
    // Set<Role> roles = new HashSet<>();

    // if (strRoles == null) {
    //   Role userRole = roleRepository.findByName(ERole.ROLE_USER)
    //       .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    //   roles.add(userRole);
    // } else {
    //   strRoles.forEach(role -> {
    //     switch (role) {
    //     case "admin":
    //       Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
    //           .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    //       roles.add(adminRole);

    //       break;
    //     case "mod":
    //       Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
    //           .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    //       roles.add(modRole);

    //       break;
    //     default:
    //       Role userRole = roleRepository.findByName(ERole.ROLE_USER)
    //           .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    //       roles.add(userRole);
    //     }
    //   });

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        
        
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        
        return ResponseEntity.ok(new JwtResponse(jwt, userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getEmail(), roles));
    }
    
}
