package com.redvelvet.redvelvet.business.dtos;

public class 
SimpleRecipeDTO {

    private String imageUrl;
    
    private String name;
    
    private Long id;

    //private picture;

    public SimpleRecipeDTO(String name, Long id, String imageUrl){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }   

    public SimpleRecipeDTO(String name, Long id){
        this.id = id;
        this.name = name;
    }

    public SimpleRecipeDTO(){

    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setMainImage(String url){
        this.imageUrl = url;
    }

    public String getImage(){
        return imageUrl;
    }
}
