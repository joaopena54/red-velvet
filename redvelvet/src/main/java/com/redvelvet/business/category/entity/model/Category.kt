package com.redvelvet.business.category.entity.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Category {

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var approved: Boolean = false

}