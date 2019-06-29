package com.indraphan.learn.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.indraphan.learn.ws.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) 
	{
		ErrorMessage error = new ErrorMessage();
		error.setTimestamp(new Date());
		error.setMessage((ex.getLocalizedMessage() != null) ? ex.getLocalizedMessage() : ex.toString());
		error.setExceptionHandlingMethod("handleAnyException");
		
		return new ResponseEntity<Object>(error, 
											new HttpHeaders(), 
											HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) 
	{
		ErrorMessage error = new ErrorMessage();
		error.setTimestamp(new Date());
		error.setMessage((ex.getLocalizedMessage() != null) ? ex.getLocalizedMessage() : ex.toString());
		error.setExceptionHandlingMethod("handleUserServiceException");
		
		return new ResponseEntity<Object>(error, 
											new HttpHeaders(), 
											HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
