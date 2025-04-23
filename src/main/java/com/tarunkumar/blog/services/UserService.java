package com.tarunkumar.blog.services;

import java.util.List;
import com.tarunkumar.blog.entities.User;
import com.tarunkumar.blog.payloads.UserDto;

public interface UserService {
	
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
	

}
