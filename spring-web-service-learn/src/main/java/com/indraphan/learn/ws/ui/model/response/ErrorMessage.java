package com.indraphan.learn.ws.ui.model.response;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorMessage {
	private Date timestamp;
	private String message;
	private String exceptionHandlingMethod;
}
