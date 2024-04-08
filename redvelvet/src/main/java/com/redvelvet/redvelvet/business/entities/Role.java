package com.redvelvet.redvelvet.business.entities;

import java.util.Set;

import com.redvelvet.redvelvet.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    private RoleType name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    // Constructors, getters, setters

    public Role(RoleType name) {
        this.name = name;
    }

    public Role(){
        
    }

    public RoleType getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public void addUser(User user){
        users.add(user);
    }

    // Getters and setters
    // Omitted for brevity


}
