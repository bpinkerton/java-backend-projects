package com.revature.exception;

public class ResourceNotCreatedException extends RuntimeException{

    public ResourceNotCreatedException(String resourceName) {
        super("Could not create " + resourceName);
    }
}
