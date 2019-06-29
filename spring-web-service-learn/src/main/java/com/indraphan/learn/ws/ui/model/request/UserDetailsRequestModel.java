package com.indraphan.learn.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDetailsRequestModel {
	@NotNull(message = "First name cannot be empty")
	private String firstName;
	
	@NotNull(message = "Last name cannot be empty")
	private String lastName;
	
	@NotNull(message = "Email cannot be empty")
	@Email
	private String email;
	
	@NotNull(message = "Password cannot be empty")
	@Size(min = 8, max = 16, message = "Password must be equal or greater then 8 characters and less than 16 characters")
	private String password;
}
