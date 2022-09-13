package com.app.movie.model;

public class jwtResponse {

	String token;

	public jwtResponse() {
		super();
	}

	public jwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
