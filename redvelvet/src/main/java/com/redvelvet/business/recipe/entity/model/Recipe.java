package com.redvelvet.business.recipe.entity.model;

import com.redvelvet.business.base.entity.BaseEntity;
import com.redvelvet.business.step.entity.model.Step;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Recipe extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Integer rating;

    @Column(nullable = true, name = "prepare_duration")
    private long prepareDurationInMinutes;

    @Column(nullable = true)
    private String link;

    @ElementCollection
    @CollectionTable(
            name = "recipe_procedure",
            joinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Step> procedure;

}
