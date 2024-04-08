package com.redvelvet.redvelvet.business.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redvelvet.redvelvet.business.entities.RecipeCategory;

public interface CategoryRepository extends JpaRepository<RecipeCategory,Long> {

    Optional<RecipeCategory> findByName(String categoryName);
    
}
