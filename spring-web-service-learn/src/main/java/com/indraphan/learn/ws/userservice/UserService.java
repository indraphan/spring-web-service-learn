package com.indraphan.learn.ws.userservice;

import java.util.List;

import com.indraphan.learn.ws.ui.model.request.UpdateUserDetailRequestModel;
import com.indraphan.learn.ws.ui.model.request.UserDetailsRequestModel;
import com.indraphan.learn.ws.ui.model.response.UserRest;

public interface UserService {

	UserRest createUser(UserDetailsRequestModel userDetail);
	
	UserRest getUser(String userId);
	
	List<UserRest> getUsers(int page, int limit, String sort);
	
	UserRest updateUser(String userId, UpdateUserDetailRequestModel userDetail);
	
	void deleteUser(String userId);
}
