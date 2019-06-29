package com.indraphan.learn.ws.ui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indraphan.learn.ws.ui.model.request.UpdateUserDetailRequestModel;
import com.indraphan.learn.ws.ui.model.request.UserDetailsRequestModel;
import com.indraphan.learn.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	Map<String, UserRest> users;
	
	/**
	 * GET HTTP Request to get all user information
	 * 
	 * @param page
	 * @param limit
	 * @param sort
	 * @return ResponseEntity<List<UserRest>>
	 */
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserRest>> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) 
	{
		List<UserRest> userList = users.entrySet().stream()
									.map(Map.Entry::getValue)
									.skip((page <= 1) ? 0 : (page-1) * limit)
									.limit(limit)
									.collect(Collectors.toList());
		
		return new ResponseEntity<List<UserRest>>(userList, HttpStatus.OK);
	}

	/**
	 * GET HTTP Request to get user information based on userId
	 * Response could be in XML and JSON format
	 * 
	 * @param userId 						User ID
	 * @return ResponseEntity<UserRest>		Response entity with user information
	 */
	@GetMapping(path = "/{userId}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) 
	{
		if(users.containsKey(userId))
		{
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		}
		
		return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
	}

	/**
	 * POST HTTP Request to create user
	 * 
	 * @param userDetail					User detail information, accept JSON or XML format
	 * @return ResponseEntity<UserRest>		Response entity with newly created user information
	 */
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetail) 
	{
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetail.getEmail());
		returnValue.setFirstName(userDetail.getFirstName());
		returnValue.setLastName(userDetail.getLastName());
		
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<>();
		users.put(userId, returnValue);

		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	/**
	 * PUT HTTP Request to update user information
	 * 
	 * @param userId						User ID
	 * @param userDetail					User detail information, accept JSON or XML format
	 * @return ResponseEntity<UserRest>		Response entity with updated user information
	 */
	@PutMapping(path = {"/{userId}"},
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailRequestModel userDetail) 
	{ 
		UserRest user = users.get(userId);
		
		if(user != null) 
		{
			user.setFirstName(userDetail.getFirstName());
			user.setLastName(userDetail.getLastName());
			
			users.put(userId, user);
			
			return new ResponseEntity<UserRest>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
	}

	/**
	 * DELETE HTTP Request to delete user
	 * 
	 * @param userId					User ID
	 * @return ResponseEntity<Void>		Empty response entity
	 */
	@DeleteMapping(path = {"/{userId}"})
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) 
	{
		users.remove(userId);
		
		return ResponseEntity.noContent().build();
	}

}
