package com.indraphan.learn.ws.ui.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateUserDetailRequestModel {
	@NotNull(message = "First name cannot be empty")
	private String firstName;
	
	@NotNull(message = "Last name cannot be empty")
	private String lastName;
}
