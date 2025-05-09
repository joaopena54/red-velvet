package com.redvelvet.business.step.entity.model

import com.redvelvet.business.base.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

@Embeddable
class Step : BaseEntity() {

    @NotBlank
    @Column(nullable = false)
    var description: String? = null

    @NotNull
    @Column(nullable = false)
    var stepNumber: Int? = null
}
