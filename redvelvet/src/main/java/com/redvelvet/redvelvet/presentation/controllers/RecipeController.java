package com.redvelvet.redvelvet.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redvelvet.redvelvet.business.dtos.RecipeDTO;
import com.redvelvet.redvelvet.business.payload.requestPayload.RecipeCreationRequest;
import com.redvelvet.redvelvet.business.services.RecipeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    
    @PostMapping("/create")    
    public ResponseEntity<?> createRecipe(@RequestBody RecipeCreationRequest recipeCreationRequest) {
        try {

            if(recipeCreationRequest.getRecipeDTO() == null || recipeCreationRequest.getUserId() == null)
                return ResponseEntity.badRequest().body("Request body is missing required fields");

            recipeService.createRecipe(recipeCreationRequest.getRecipeDTO(), recipeCreationRequest.getUserId());
        
        } catch(Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

        
        return ResponseEntity.ok().body("Recipe was sucessfully created");

    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Long id){

        try {

            RecipeDTO recipe = recipeService.getRecipe(id);

            return ResponseEntity.ok().body(recipe);

        } catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }


}
