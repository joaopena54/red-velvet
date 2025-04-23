package com.redvelvet.business.recipe.entity.model;

import com.redvelvet.business.base.entity.BaseEntity;
import com.redvelvet.business.ingredient.entity.model.Ingredient;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Recipe extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private List<Ingredient> ingredients;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "recipe_procedure", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> procedure;
}
