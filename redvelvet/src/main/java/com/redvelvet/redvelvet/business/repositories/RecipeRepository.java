package com.redvelvet.redvelvet.business.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.redvelvet.redvelvet.business.entities.Recipe;
import com.redvelvet.redvelvet.business.entities.RecipeCategory;

public interface RecipeRepository extends JpaRepository<Recipe,Long>{

    Optional<Recipe> findByName(String name);

    Page<Recipe> findByCategory(RecipeCategory category, Pageable pageable);
    
}
