package com.redvelvet.business.step.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Step {

    @NotBlank
    private String description;

    @Column(name = "step_number")
    private int stepNumber;
}
