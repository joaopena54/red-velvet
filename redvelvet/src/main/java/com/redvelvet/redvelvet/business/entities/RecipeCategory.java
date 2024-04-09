package com.redvelvet.redvelvet.business.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipecategories")
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(unique = true, nullable = false )
    private String name;

    @ManyToMany
    @JoinTable(
    name = "category_recipe",
    joinColumns = @JoinColumn(name = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<Recipe> recipes;

    public RecipeCategory(String name){

        this.name = name;
        this.recipes = new ArrayList<Recipe>();

    }

    public RecipeCategory(){
        
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public void addRecipe(Recipe recipe){
        
        this.recipes.add(recipe);

    }

    public void removeRecipe(Long recipeId){
        Iterator<Recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getId().equals(recipeId)) {
                iterator.remove();
                break; // Exit loop after removing the recipe
            }
        }
    }

    public boolean containsRecipe(Long recipeId){
        for(Recipe recipe : recipes){
            if(recipeId == recipe.getId()){
                return true;
            }
        }
        
        return false;

    }
    

}
