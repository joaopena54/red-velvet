package com.redvelvet.redvelvet.business.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redvelvet.redvelvet.business.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe,Long>{

    Optional<Recipe> findByName(String name);
    
}
