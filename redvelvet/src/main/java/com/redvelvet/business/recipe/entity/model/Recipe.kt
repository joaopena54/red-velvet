package com.redvelvet.business.recipe.entity.model

import com.redvelvet.business.base.entity.BaseEntity
import com.redvelvet.business.ingredient.entity.model.Ingredient
import com.redvelvet.business.step.entity.model.Step
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import kotlin.time.Duration

@Entity
class Recipe : BaseEntity() {

    @NotBlank
    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = true)
    var rating: Int? = null

    @Column(nullable = true)
    var prepareDuration: Duration? = null

    @Column(nullable = true)
    var link: String? = null

    @ElementCollection
    @CollectionTable(
        name = "recipe_procedure",
        joinColumns = [JoinColumn(name = "recipe_id")]
    )
    var procedure: List<Step> = mutableListOf()
}
