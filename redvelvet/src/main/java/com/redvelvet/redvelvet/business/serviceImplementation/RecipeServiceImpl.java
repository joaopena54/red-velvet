package com.redvelvet.redvelvet.business.serviceImplementation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.redvelvet.redvelvet.business.dtos.RecipeDTO;
import com.redvelvet.redvelvet.business.entities.Recipe;
import com.redvelvet.redvelvet.business.entities.RecipeCategory;
import com.redvelvet.redvelvet.business.entities.RecipeList;
import com.redvelvet.redvelvet.business.entities.User;
import com.redvelvet.redvelvet.business.exceptions.ListNotFoundException;
import com.redvelvet.redvelvet.business.exceptions.RecipeNotFoundException;
import com.redvelvet.redvelvet.business.repositories.CategoryRepository;
import com.redvelvet.redvelvet.business.repositories.ListRepository;
import com.redvelvet.redvelvet.business.repositories.RecipeRepository;
import com.redvelvet.redvelvet.business.repositories.UserRepository;
import com.redvelvet.redvelvet.business.services.RecipeService;
import com.redvelvet.redvelvet.utils.ImageProcessingUtil;

@Service
public class RecipeServiceImpl implements RecipeService{


    @Autowired
    private ImageProcessingUtil imageProcessingUtil;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListRepository listRepository;

    @Override
    public void createRecipe(RecipeDTO recipeDTO, Long userId) {
        
        Set<RecipeCategory> categories = new HashSet<RecipeCategory>();

        for (String categoryName : recipeDTO.getCategories()) {

            Optional<RecipeCategory> category = categoryRepository.findByName(categoryName);
            if (category.isPresent()) {

                categories.add(category.get());
            } else {
            // Handle the case where the category with the given name does not exist
            // This could include logging a warning or throwing an exception
            // Depending on your application's requirements
            }
        }

        Optional<User> opUser = userRepository.findById(userId);

        Optional<Recipe> opRecipe = recipeRepository.findByName(recipeDTO.getName());

        if(!opUser.isPresent()){
            throw new UsernameNotFoundException("User was not found");
        }

        if(opRecipe.isPresent()){
            throw new RecipeNotFoundException("Recipe already exists");
        } 
    
        Recipe recipe = new Recipe(recipeDTO.getName(),
        recipeDTO.getIngredients(),
        recipeDTO.getDescription(),
        recipeDTO.getProcedure(),
        categories,
        opUser.get()
        );


        //save recipe
        Recipe savedRecipe = recipeRepository.save(recipe);

        String mainImagePath = imageProcessingUtil.createRecipeMainImageDirectory(savedRecipe.getName(), savedRecipe.getId());

        String imagesPath = imageProcessingUtil.createRecipeImagesDirectory(savedRecipe.getName(), savedRecipe.getId());

        savedRecipe.setImagesPath(imagesPath);

        savedRecipe.setMainImage(mainImagePath);

        savedRecipe = recipeRepository.save(savedRecipe);

        //save recipe in each category
        for(RecipeCategory rCategory : categories){

            rCategory.addRecipe(savedRecipe);
            categoryRepository.save(rCategory);

        }

        //save recipe in user
        User user = opUser.get();
        user.addRecipe(savedRecipe);
        userRepository.save(user);

    }

    @Override
    public void editRecipe(RecipeDTO recipeDTO) {

        Optional<Recipe> optionalrecipe = recipeRepository.findById(recipeDTO.getId());

        if(!optionalrecipe.isPresent()){
            throw new RecipeNotFoundException("Recipe couldn't be found");
        }

        Recipe recipe = optionalrecipe.get();

        Set<RecipeCategory> categoriesList = new HashSet<RecipeCategory>();

        Set<RecipeCategory> existingCategoryList = recipe.getCategory();

        for (String categoryName : recipeDTO.getCategories()) {

            Optional<RecipeCategory> category = categoryRepository.findByName(categoryName);
            if (category.isPresent()) {
                categoriesList.add(category.get());
            } else {
            // Handle the case where the category with the given name does not exist
            // This could include logging a warning or throwing an exception
            // Depending on your application's requirements
            }
        }

        if(recipeDTO.getName() != null){
            recipe.setName(recipeDTO.getName());
        }

        if(recipeDTO.getDescription() != null){
            recipe.setDescription(recipeDTO.getDescription());
        }

        if(!recipeDTO.getProcedure().isEmpty()){
            recipe.setProcedure(recipeDTO.getProcedure());
        }

        if(!recipeDTO.getIngredients().isEmpty()){
            recipe.setIngredients(recipeDTO.getIngredients());
        }

        recipe.setCategory(categoriesList); 

        Recipe savedRecipe = recipeRepository.save(recipe);

        List<RecipeCategory> categoriesToRemove = new ArrayList<>(existingCategoryList);
        List<RecipeCategory> categoriesToAdd = new ArrayList<>();

        // Iterate through the new list of categories
        for (RecipeCategory category : categoriesList) {
            // If the category is not in the initial list, add it
            if (!existingCategoryList.contains(category)) {
                categoriesToAdd.add(category);
            }
            // Otherwise, remove it from the list of categories to remove
            else {
                categoriesToRemove.remove(category);
            }
        }

        // Remove categories that are not in the new list
        for (RecipeCategory category : categoriesToRemove) {
            category.removeRecipe(savedRecipe.getId());
        }

        // Add categories that are in the new list
        for (RecipeCategory category : categoriesToAdd) {
            category.addRecipe(savedRecipe);
        }

        // Batch update the modified categories
        categoryRepository.saveAll(categoriesToRemove);
        categoryRepository.saveAll(categoriesToAdd);

    }

    @Override
    public void deleteRecipe(Long id) {

        Optional<Recipe> opRecipe = recipeRepository.findById(id);

        if(!opRecipe.isPresent()){
            throw new RecipeNotFoundException("No recipe with that id");
        }

        Recipe currRecipe = opRecipe.get();

        User user = currRecipe.getCreator();

        Set<RecipeCategory> recipeCategories = currRecipe.getCategory();

        List<RecipeList> recipeLists = currRecipe.getLists();

        user.removeRecipe(id);

        for(RecipeCategory category : recipeCategories){
            category.removeRecipe(id);
        }

        for(RecipeList list : recipeLists){
            list.removeRecipe(id);
        }

        String directoryPath = currRecipe.getImagePath();
        File directory = new File(directoryPath);

        imageProcessingUtil.delete(directory);

        directoryPath = currRecipe.getMainImage();
        directory = new File(directoryPath);

        imageProcessingUtil.delete(directory);

        userRepository.save(user);

        categoryRepository.saveAll(recipeCategories);

        listRepository.saveAll(recipeLists);

        recipeRepository.deleteById(id);

    }

    @Override
    public void addToList(Long recipeId, Long listId) {

        Optional<RecipeList> opList = listRepository.findById(listId);

        if(!opList.isPresent()){
            throw new ListNotFoundException("List was not found");
        }

        Optional<Recipe> opRecipe = recipeRepository.findById(recipeId);

        if(!opRecipe.isPresent()){
            throw new RecipeNotFoundException("Recipe was not found");
        }

        opList.get().addRecipe(opRecipe.get());

        listRepository.save(opList.get());

    }

    @Override
    public RecipeDTO getRecipe(Long id) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (!optionalRecipe.isPresent()){
            throw new RecipeNotFoundException("Recipe couldn't be found");
        }

        Recipe recipe = optionalRecipe.get();

        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setCategories(recipe.getCategory().stream()
        .map(RecipeCategory::getName)
        .toList());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setProcedure(recipe.getProcedure());
        recipeDTO.setIngredients(recipe.getIngredients());

        return recipeDTO;

    }

    //acabar aqui
    @Override
    public List<String> getRecipeImages(Long recipeId){

        List<String> images = new ArrayList<>();

        Optional<Recipe> opCurrRecipe = recipeRepository.findById(recipeId);

        if(!opCurrRecipe.isPresent()){
            throw new RecipeNotFoundException("Recipe Not Found");
        }

        String imagepath = opCurrRecipe.get().getImagePath();

        images = imageProcessingUtil.getFileNameList(imagepath);

        return images;

    }

    @Override
    public void addMainImage(MultipartFile file, Long recipeId) throws IOException {
        
        List<MultipartFile> files = new ArrayList<>();

        List<String> filepath = new ArrayList<>();

        files.add(file);

        Optional<Recipe> opCurrRecipe = recipeRepository.findById(recipeId);

        if(!opCurrRecipe.isPresent()){
            throw new RecipeNotFoundException("Recipe Not Found");
        }

        filepath = imageProcessingUtil.addImageToDirectory(recipeId, files);

        imageProcessingUtil.addMainImage(recipeId, filepath.get(0));

    }

    @Override
    public void addImages(List<MultipartFile> files, Long recipeId) throws IOException {

        Optional<Recipe> opCurrRecipe = recipeRepository.findById(recipeId);

        if(!opCurrRecipe.isPresent()){
            throw new RecipeNotFoundException("Recipe Not Found");
        }

        imageProcessingUtil.addImageToDirectory(recipeId, files);

    }

    @Override
    public void removeFromList(Long recipeId, Long listId) {

        Optional<RecipeList> opList = listRepository.findById(listId);

        if(!opList.isPresent()){
            throw new ListNotFoundException("List was not found");
        }

        Optional<Recipe> opRecipe = recipeRepository.findById(recipeId);

        if(!opRecipe.isPresent()){
            throw new RecipeNotFoundException("Recipe was not found");
        }

        RecipeList list = opList.get();
        Recipe recipe = opRecipe.get();

        list.removeRecipe(recipe.getId());

        recipe.removeList(listId);

        listRepository.save(list);

        recipeRepository.save(recipe);

    }   
    
}
