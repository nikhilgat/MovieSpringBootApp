package com.app.movie.exceptions;

@SuppressWarnings("serial")
public class PostNotFoundException extends RuntimeException {

	public PostNotFoundException(String message) {
        super(message);
}
}