package com.redvelvet.business.recipeIngredient.entity.model

import com.redvelvet.business.base.entity.BaseEntity
import com.redvelvet.business.ingredient.entity.model.Ingredient
import com.redvelvet.business.recipe.entity.model.Recipe
import jakarta.persistence.Column
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull

class RecipeIngredient : BaseEntity () {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    var recipe: Recipe? = null

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    var ingredient: Ingredient? = null

    @NotNull
    @Column(nullable = false)
    var quantity: Double? = null

    @NotNull
    @Column(nullable = false)
    var measure: String? = null


}