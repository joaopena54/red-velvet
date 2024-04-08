package com.redvelvet.redvelvet.business.services;

import java.util.List;

import com.redvelvet.redvelvet.business.dtos.SimpleRecipeDTO;

public interface CategoryService {

    public List<String> getCategories();
    
    public void addCategory(String name);

    public void deleteCategory(String name);

    public List<SimpleRecipeDTO> getRecipesFromCategory(String name, Long userId);

}
