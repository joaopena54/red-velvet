package com.redvelvet.redvelvet.config;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redvelvet.redvelvet.business.entities.RecipeCategory;
import com.redvelvet.redvelvet.business.repositories.CategoryRepository;

@Service
public class CategoryInitializationService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void initializeCategories(List<String> initialCategoryNames) {
        for (String categoryName : initialCategoryNames) {
            RecipeCategory category = new RecipeCategory(categoryName);
            categoryRepository.save(category);
        }
    }
    
}
