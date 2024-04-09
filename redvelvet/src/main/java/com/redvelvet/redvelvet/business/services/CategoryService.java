package com.redvelvet.redvelvet.business.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.redvelvet.redvelvet.business.dtos.SimpleRecipeDTO;

public interface CategoryService {

    public List<String> getCategories();
    
    public void addCategory(String name);

    public void deleteCategory(String name);

    public Page<SimpleRecipeDTO> getRecipesFromCategory(String name, Long userId, int pageNo, int pageSize);

}
