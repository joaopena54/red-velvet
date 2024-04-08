package com.redvelvet.redvelvet.business.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    
    public CategoryAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }

}
