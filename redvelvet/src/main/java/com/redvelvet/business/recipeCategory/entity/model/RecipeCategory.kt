package com.redvelvet.business.recipeCategory.entity.model

import com.redvelvet.business.base.entity.BaseEntity
import com.redvelvet.business.category.entity.model.Category
import com.redvelvet.business.recipe.entity.model.Recipe
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull

class RecipeCategory : BaseEntity () {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    var recipe: Recipe? = null

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category? = null
}