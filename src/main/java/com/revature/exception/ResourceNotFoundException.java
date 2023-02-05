package com.revature.exception;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String resourceName, Object identifier) {
        super("Could not find " + resourceName + " with id: " + identifier);
    }
}
