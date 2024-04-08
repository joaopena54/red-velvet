package com.redvelvet.redvelvet.business.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RecipeList> recipeLists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recipe> userRecipes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User (String username , String password, String email, Set<Role> roles){
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.recipeLists = new ArrayList<>();
        this.userRecipes = new ArrayList<>();
    }

    public User(){
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId(){
        return this.id;
    }
    
    public Set<Role> getRoles(){
        return roles;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void addRecipe(Recipe recipe){
        this.userRecipes.add(recipe);
    }

    public void removeRecipe(Long id) {
        Iterator<Recipe> iterator = userRecipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getId().equals(id)) {
                iterator.remove(); // Safely remove the current element
            }
        }

    }

    public void removeList(Long id) {
        Iterator<RecipeList> iterator = recipeLists.iterator();
        while (iterator.hasNext()) {
            RecipeList list = iterator.next();
            if (list.getListId().equals(id)) {
                iterator.remove(); // Safely remove the current element
            }
        }

    }

    public void addList(RecipeList list){
        this.recipeLists.add(list);
    }

}

