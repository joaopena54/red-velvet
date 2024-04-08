package com.redvelvet.redvelvet;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redvelvet.redvelvet.config.CategoryInitializationService;
import com.redvelvet.redvelvet.config.RoleInitializationService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class RedvelvetApplication {

	@Autowired
    private CategoryInitializationService categoryInitializationService;

	@Autowired
	private RoleInitializationService roleInitializationService;

	public static void main(String[] args) {
		SpringApplication.run(RedvelvetApplication.class, args);
	}

	@PostConstruct
    public void init() {
        List<String> initialCategoryNames = Arrays.asList("Breakfast", "Brunch", "Lunch", "Dinner", "Sandwiches", "Wraps", "Appetisers", "Soups", "Salads", "Sides", "Snacks", "Burgers", "Pizzas", "Pies", "Tarts", "Mince", "Chicken", "Seafood", "Rice", "Noodles", "Pastas", "Beef", "Fried", "Pork", "Turkey", "Duck", "Sauces", "Bread", "Cookies", "Cake", "Dessert", "Cheesecake", "Ice cream", "Smoothies and Shakes", "Vegetarian", "Drinks", "Cocktails");
        categoryInitializationService.initializeCategories(initialCategoryNames);

		roleInitializationService.initRoles();
    }

}
