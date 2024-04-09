package com.redvelvet.redvelvet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.redvelvet.redvelvet.business.dtos.RecipeDTO;
import com.redvelvet.redvelvet.business.entities.Ingredient;
import com.redvelvet.redvelvet.business.entities.Recipe;
import com.redvelvet.redvelvet.business.entities.Role;
import com.redvelvet.redvelvet.business.entities.User;
import com.redvelvet.redvelvet.business.repositories.RecipeRepository;
import com.redvelvet.redvelvet.business.repositories.RoleRepository;
import com.redvelvet.redvelvet.business.repositories.UserRepository;
import com.redvelvet.redvelvet.business.services.RecipeService;
import com.redvelvet.redvelvet.enums.Measures;
import com.redvelvet.redvelvet.enums.RoleType;

@SpringBootTest
public class RecipeServiceTests {

    @Autowired
    private RecipeService recipeService;

    @Autowired  
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired RoleRepository roleRepository;

    @Test
    public void createRecipe() {

        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName(RoleType.ROLE_USER).get();

        roles.add(role);

        User user = new User("user1","password1","email1",roles);

        user = userRepository.save(user);

        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDTO.setName("recipe1");

        List<String> procedure = new ArrayList<>();

        procedure.add("step1");
        procedure.add("step2");

        recipeDTO.setProcedure(procedure);

        recipeDTO.setDescription("description");

        List<String> categories = new ArrayList<>();

        categories.add("Breakfast");

        recipeDTO.setCategories(categories);

        Ingredient ing1 = new Ingredient("potato", 2, Measures.KG);
        Ingredient ing2 = new Ingredient("rice", 1, Measures.CUP);

        List<Ingredient> ingredients = new ArrayList<>();

        
        ingredients.add(ing1);
        ingredients.add(ing2);

        recipeDTO.setIngredients(ingredients);

        recipeDTO.setPersonal(true);

        recipeService.createRecipe(recipeDTO, user.getId() );

        Long numberOf = recipeRepository.count();

        assertEquals(numberOf, 1);

        Recipe recipe = recipeRepository.findByName("recipe1").get();

        assertEquals("recipe1",recipe.getName());

        assertEquals("potato", recipe.getIngredients().get(0).getName());

        assertEquals(1, recipe.getCategory().size());

    }
    
}
