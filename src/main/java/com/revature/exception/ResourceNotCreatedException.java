package com.revature.exception;

public class ResourceNotCreatedException extends RuntimeException{

    public ResourceNotCreatedException(String resourceName, Object identifier) {
        super("Could not create " + resourceName + " with id: " + identifier);
    }
}
