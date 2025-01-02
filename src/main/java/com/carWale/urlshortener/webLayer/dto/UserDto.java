package com.carWale.urlshortener.webLayer.dto;

public class UserDto {
	private Long id;
    private String email;
    private String password;
    private String jwt;
    private String statusCode;
    private String message;

	public UserDto() {}
	
	public UserDto(String firstName, String lastName, String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.jwt = "1234";
		this.statusCode = "202";
		this.message = "message";
		this.id = 0L;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
    
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
