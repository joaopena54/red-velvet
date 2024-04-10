package com.redvelvet.redvelvet.business.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.redvelvet.redvelvet.business.entities.RecipeCategory;
import com.redvelvet.redvelvet.business.entities.User;
import com.redvelvet.redvelvet.business.dtos.SimpleRecipeDTO;
import com.redvelvet.redvelvet.business.entities.Recipe;
import com.redvelvet.redvelvet.business.exceptions.CategoryAlreadyExistsException;
import com.redvelvet.redvelvet.business.exceptions.CategoryNotFoundException;
import com.redvelvet.redvelvet.business.repositories.CategoryRepository;
import com.redvelvet.redvelvet.business.repositories.RecipeRepository;
import com.redvelvet.redvelvet.business.services.CategoryService;
import com.redvelvet.redvelvet.utils.ImageProcessingUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ImageProcessingUtil imageProcessingUtil;

    @Override
    public void addCategory(String name) {

        RecipeCategory category = new RecipeCategory(name);

        Optional<RecipeCategory> opCategory = categoryRepository.findByName(name);

        if(opCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Cant create new category since theres already one with this name");
        }

        categoryRepository.save(category);

    }

    @Override
    public void deleteCategory(String name) {

        Optional<RecipeCategory> opCategory = categoryRepository.findByName(name);

        if(!opCategory.isPresent()){
            throw new CategoryNotFoundException("Category does not exist");
        }

        RecipeCategory category = opCategory.get();

        List<Recipe> recipes = category.getRecipes();

        for(Recipe recipe : recipes) {
            recipe.removeCategory(category.getCategoryId());
        }

        recipeRepository.saveAll(recipes);

        categoryRepository.deleteById(category.getCategoryId());

    }

    @Override
    public Page<SimpleRecipeDTO> getRecipesFromCategory(String name, Long userId, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);


        Optional<RecipeCategory> opCategory = categoryRepository.findByName(name);

        if(!opCategory.isPresent()){
            throw new CategoryNotFoundException("Category does not exist");
        }

        RecipeCategory category = opCategory.get();

        Page<Recipe> recipePage = recipeRepository.findByCategory(category, pageable);

        return recipePage.map(recipe -> {
            String mainImage = imageProcessingUtil.getMainFileName(recipe.getMainImage()).get(0);
            User recipeCreator = recipe.getCreator();
            if (userId.equals(recipeCreator.getId()) || !recipe.isPersonal()) {
                return new SimpleRecipeDTO(recipe.getName(), recipe.getId(), mainImage);
            } else {
                return null; // Skip this recipe if it doesn't meet the criteria
            }
        });

    }

    @Override
    public List<String> getCategories() {

        List<String> categoryNames = categoryRepository.findAll()
        .stream()
        .map(RecipeCategory::getName)
        .collect(Collectors.toList());

        return categoryNames;

    }
    
}
