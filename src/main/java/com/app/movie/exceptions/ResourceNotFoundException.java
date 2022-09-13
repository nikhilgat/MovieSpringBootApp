package com.app.movie.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
}
}
