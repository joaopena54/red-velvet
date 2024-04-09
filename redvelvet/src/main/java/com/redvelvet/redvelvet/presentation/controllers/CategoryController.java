package com.redvelvet.redvelvet.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redvelvet.redvelvet.business.dtos.SimpleRecipeDTO;
import com.redvelvet.redvelvet.business.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{name}/page/{pageNo}")
    public ResponseEntity<?> getCategoryRecipes(@PathVariable String name, @PathVariable int pageNo, @RequestBody Long userId){

        try {

            Page<SimpleRecipeDTO> recipes = categoryService.getRecipesFromCategory(name, userId, pageNo, 20);

            return ResponseEntity.ok().body(recipes);

        } catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories(){

        try {

            return ResponseEntity.ok().body(categoryService.getCategories());

        } catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PostMapping("/addCategory/{name}")
    public ResponseEntity<?> addCategory(@PathVariable String name){

        try {

            categoryService.addCategory(name);

            return ResponseEntity.ok().body("Category added");

        } catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @DeleteMapping("/deleteCategory/{name}")
    public ResponseEntity<?> deleteCategory(@PathVariable String name){

        try {

            categoryService.deleteCategory(name);

            return ResponseEntity.ok().body("Category deleted");

        } catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    

}
