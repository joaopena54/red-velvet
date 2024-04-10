package com.redvelvet.redvelvet.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redvelvet.redvelvet.business.dtos.RecipeDTO;
import com.redvelvet.redvelvet.business.payload.requestPayload.RecipeCreationRequest;
import com.redvelvet.redvelvet.business.services.RecipeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("/edit/{id}")    
    public ResponseEntity<?> editRecipe(@RequestBody RecipeDTO recipeDTO, @PathVariable Long id) {
        try {

            if(recipeDTO == null)
                return ResponseEntity.badRequest().body("Request body is missing required fields");

            recipeService.editRecipe(recipeDTO);
        
        } catch(Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

        
        return ResponseEntity.ok().body("Recipe was sucessfully created");

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id){

        try {

            recipeService.deleteRecipe(id);

            return ResponseEntity.ok().body("Recipe was sucessfully deleted");

        } catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @PostMapping("/addToList/{recipeId}/{listId}")
    public ResponseEntity<?> addToList(@PathVariable Long recipeId , @PathVariable Long listId ){


        try {

            recipeService.addToList(recipeId, listId);

            return ResponseEntity.ok().body("Recipe was sucessfully added to list");

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("{recipe}/RecipeImages")
    public ResponseEntity<?> getRecipeImages(@PathVariable Long recipe){

        try {

            return ResponseEntity.ok().body(recipeService.getRecipeImages(recipe));

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @DeleteMapping("/removeFromList/{recipeId}/{listId}")   
    public ResponseEntity<?> removeFromList(@PathVariable Long recipeId , @PathVariable Long listId ){

        try {

            recipeService.removeFromList(recipeId, listId);

            return ResponseEntity.ok().body("Recipe was sucessfully removed from list");

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @PostMapping("/recipe/{id}/main-image")
    public ResponseEntity<?> addMainImage(@RequestBody MultipartFile file , @PathVariable Long id){

        try {

            recipeService.addMainImage(file, id);

            return ResponseEntity.ok().body("Recipe was sucessfully removed from list");

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PostMapping("/recipe/{id}/images")
    public ResponseEntity<?> addImages(@RequestBody List<MultipartFile> files , @PathVariable Long id){
        

        try {

            recipeService.addImages(files, id);

            return ResponseEntity.ok().body("Recipe was sucessfully removed from list");

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

}
