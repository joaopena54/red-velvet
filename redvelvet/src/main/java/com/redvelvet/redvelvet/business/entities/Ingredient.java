package com.redvelvet.redvelvet.business.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ingredient {
    
    private String name;
    private int quantity;

    public Ingredient(String name , int quantity){

        this.name = name;
        this.quantity = quantity;

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

}
