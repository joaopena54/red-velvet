package com.redvelvet.redvelvet.business.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.redvelvet.redvelvet.business.dtos.RecipeDTO;

public interface RecipeService {

    public void createRecipe(RecipeDTO recipeDTO, Long userId);

    public void addMainImage(MultipartFile file, Long recipeId) throws IOException;

    public void addImages(List<MultipartFile> files, Long recipeId) throws IOException;

    public void editRecipe(RecipeDTO recipeDTO);

    public void deleteRecipe(Long id);

    public void addToList(Long recipeId , Long listId);

    public void removeFromList(Long recipeId , Long listId);

    public RecipeDTO getRecipe(Long id);

    public List<String> getRecipeImages(Long recipe);
    

}
