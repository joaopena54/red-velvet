package com.redvelvet.business.ingredient.entity.model;

import com.redvelvet.business.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Ingredient extends BaseEntity {

    @Column(nullable = false)
    String name;

}
