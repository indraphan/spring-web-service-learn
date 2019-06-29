package com.indraphan.learn.ws.userservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indraphan.learn.ws.exceptions.UserServiceException;
import com.indraphan.learn.ws.shared.Utils;
import com.indraphan.learn.ws.ui.model.request.UpdateUserDetailRequestModel;
import com.indraphan.learn.ws.ui.model.request.UserDetailsRequestModel;
import com.indraphan.learn.ws.ui.model.response.UserRest;
import com.indraphan.learn.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
	Map<String, UserRest> users;
	Utils utils;
	
	public UserServiceImpl() { }
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetail) {
		UserRest user = new UserRest();
		user.setEmail(userDetail.getEmail());
		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		
		String userId = utils.generateUserId();
		user.setUserId(userId);
		
		if(users == null) users = new HashMap<>();
		users.put(userId, user);
		
		return user;
	}

	@Override
	public UserRest getUser(String userId) {
		if(users == null || !users.containsKey(userId)) throw new UserServiceException("User not found");
		
		return users.get(userId);
	}

	@Override
	public List<UserRest> getUsers(int page, int limit, String sort) {
		List<UserRest> userList = users.entrySet().stream()
				.map(Map.Entry::getValue)
				.skip((page <= 1) ? 0 : (page-1) * limit)
				.limit(limit)
				.collect(Collectors.toList());
		
		return userList;
	}

	@Override
	public UserRest updateUser(String userId, UpdateUserDetailRequestModel userDetail) {
		if(users == null || !users.containsKey(userId)) throw new UserServiceException("User not found");
		
		UserRest user = users.get(userId);
		
		if(user != null) 
		{
			user.setFirstName(userDetail.getFirstName());
			user.setLastName(userDetail.getLastName());
			
			users.put(userId, user);
		}
		else
		{
			throw new UserServiceException("User not found");
		}
		
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		if(users == null || !users.containsKey(userId)) throw new UserServiceException("User not found");
		
		users.remove(userId);
	}

}
