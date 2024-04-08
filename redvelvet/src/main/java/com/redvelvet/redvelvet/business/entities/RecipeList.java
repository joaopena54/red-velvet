package com.redvelvet.redvelvet.business.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipelists")    
public class RecipeList {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long listId;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
    name = "list_recipe",
    joinColumns = @JoinColumn(name = "list_id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<Recipe> recipes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RecipeList(String name, User user){

        this.name = name ;
        this.recipes = new ArrayList<Recipe>();
        this.user = user;

    }

    public RecipeList(){
        
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public void removeRecipe(Long id) {
        Iterator<Recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getId().equals(id)) {
                iterator.remove();
                break; // Exit loop after removing the recipe
            }
        }
    }

    public User getUser(){
        return this.user;
    }

}
