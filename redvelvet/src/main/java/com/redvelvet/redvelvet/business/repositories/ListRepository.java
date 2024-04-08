package com.redvelvet.redvelvet.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redvelvet.redvelvet.business.entities.RecipeList;

public interface ListRepository extends JpaRepository<RecipeList,Long>{
    
}
