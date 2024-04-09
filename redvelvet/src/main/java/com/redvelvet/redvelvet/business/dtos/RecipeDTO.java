package com.redvelvet.redvelvet.business.dtos;

import java.util.List;

import com.redvelvet.redvelvet.business.entities.Ingredient;

public class RecipeDTO {

    private Long id;
    private String name;
    private String description;
    private List<String> procedure;
    private List<Ingredient> ingredients;
    private List<String> categories;
    private List<String> imagesUrls;
    private String mainImage;
    private Boolean isPersonal;

    public RecipeDTO(){

        
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public List<String> getProcedure(){
        return this.procedure;
    }

    public List<Ingredient> getIngredients(){
        return this.ingredients;
    }

    public List<String> getCategories(){
        return this.categories;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setProcedure(List<String> procedure){
        this.procedure = procedure;
    }

    public void addIngredients(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void setCategories(List<String> categories){
        this.categories = categories;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setIngredients(List<Ingredient> ingredients2) {
        this.ingredients = ingredients2;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagesUrls(List<String> imagesUrls){
        this.imagesUrls = imagesUrls;
    }

    public List<String> getImagesUrls(){
        return imagesUrls;
    }

    public void setMainImage(String image){
        this.mainImage = image;
    }

    public String getMainImage(){
        return mainImage;
    }

    public void setPersonal(boolean isPersonal){
        this.isPersonal = isPersonal;
    }   

    public Boolean isPersonal(){
        return isPersonal;
    }
}
