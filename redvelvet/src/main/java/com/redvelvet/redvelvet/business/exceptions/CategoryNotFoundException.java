package com.redvelvet.redvelvet.business.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    
    public CategoryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
