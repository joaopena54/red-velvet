package com.redvelvet.business.ingredient.entity.model

import com.redvelvet.business.base.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.jetbrains.annotations.NotNull

@Entity
class Ingredient : BaseEntity() {

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var approved: Boolean = false
}
