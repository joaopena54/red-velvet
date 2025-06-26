package com.redvelvet.business.recipeIngredient.entity.model;

import com.redvelvet.business.base.entity.BaseEntity;
import com.redvelvet.business.ingredient.entity.model.Ingredient;
import com.redvelvet.business.recipe.entity.model.Recipe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecipeIngredient extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @NotNull
    @Column(nullable = false)
    private BigDecimal quantity;

    //Enum
    @NotNull
    @Column(nullable = false)
    private String measure;
}
