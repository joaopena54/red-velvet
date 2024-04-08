package com.redvelvet.redvelvet.business.exceptions;

public class ListNotFoundException extends RuntimeException {
    
    public ListNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
