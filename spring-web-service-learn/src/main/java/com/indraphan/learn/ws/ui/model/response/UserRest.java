package com.indraphan.learn.ws.ui.model.response;

import lombok.Data;

@Data
public class UserRest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userId;
}
