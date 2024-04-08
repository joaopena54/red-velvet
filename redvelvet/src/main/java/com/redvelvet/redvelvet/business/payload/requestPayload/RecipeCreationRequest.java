package com.redvelvet.redvelvet.business.payload.requestPayload;

import com.redvelvet.redvelvet.business.dtos.RecipeDTO;

public class RecipeCreationRequest {
    
    private RecipeDTO recipeDTO;
    private Long userId;

    public RecipeCreationRequest(RecipeDTO recipeDTO, Long userId){
        this.recipeDTO = recipeDTO;
        this.userId = userId;
    }

    public RecipeCreationRequest(){
        
    }
    
    public RecipeDTO getRecipeDTO(){
        return recipeDTO;
    }

    public Long getUserId(){
        return userId;
    }

    public void setRecipeDTO(RecipeDTO recipeDTO){
        this.recipeDTO = recipeDTO;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    

}
