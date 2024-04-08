package com.redvelvet.redvelvet.business.exceptions;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
