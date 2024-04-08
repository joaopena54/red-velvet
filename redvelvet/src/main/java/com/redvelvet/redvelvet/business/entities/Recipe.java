package com.redvelvet.redvelvet.business.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long recipeId;

    private String name;
    
    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<Ingredient> ingredients;

    private String description;

    private List<String> procedure;

    private boolean personal;

    private String mainImagePath;

    private String imagesPath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy ="recipes")
    private Set<RecipeCategory> categories;

    @ManyToMany(mappedBy ="recipes")
    private List<RecipeList> lists;

    public Recipe(String name , List<Ingredient> ingredients , String description , List<String> procedure , Set<RecipeCategory> categories, User creator){

        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.procedure = procedure;
        this.user = creator;
        this.categories = categories;
        this.lists = new ArrayList<>();
        this.personal=true;
    }

    public Recipe(){
        
    }

    public Long getId(){
        return this.recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getProcedure() {
        return procedure;
    }

    public void setProcedure(List<String> procedure) {
        this.procedure = procedure;
    }

    public User getCreator() {
        return user;
    }

    public void setCreator(User creator) {
        this.user = creator;
    }

    public Set<RecipeCategory> getCategory() {
        return categories;
    }

    public void setCategory(Set<RecipeCategory> categories) {
        this.categories = categories;
    }

    public List<RecipeList> getLists() {
        return lists;
    }

    public void removeCategory(Long categoryId) {
        Iterator<RecipeCategory> iterator = categories.iterator();
        while (iterator.hasNext()) {
            RecipeCategory category = iterator.next();
            if (category.getCategoryId().equals(categoryId)) {
                iterator.remove();
                break; // Exit loop after removing the recipe
            }
        }

    }

    public void removeList(Long listId) {
        Iterator<RecipeList> iterator = lists.iterator();
        while (iterator.hasNext()) {
            RecipeList list = iterator.next();
            if (list.getListId().equals(listId)) {
                iterator.remove();
                break; // Exit loop after removing the recipe
            }
        }

    }

    public void setPersonal(boolean personal){
        this.personal = personal;
    }

    public boolean isPersonal(){
        return personal;
    }

    public void setMainImage(String path){
        this.mainImagePath = path;
    }

    public String getMainImage(){
        return mainImagePath;
    }
    
    public String getImagePath(){
        return imagesPath;
    }

    public void setImagesPath(String path){
        this.imagesPath = path;
    }

}
