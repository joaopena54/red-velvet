package com.redvelvet.redvelvet.business.entities;

import com.redvelvet.redvelvet.enums.Measures;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ingredient {
    
    private String name;
    private int quantity;
    private Measures measure;

    public Ingredient(String name , int quantity, Measures measure){

        this.name = name;
        this.quantity = quantity;
        this.measure = measure;

    }

    public Ingredient(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Measures getMeasure() {
        return measure;
    }
    
    public void setMeasure(Measures measure) {
        this.measure = measure;
    }
}
