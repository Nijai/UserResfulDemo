package com.rest.service;

import java.util.List;

import com.rest.model.User;

public interface IUserService {
	public User saveUser(User uObj);
	public User updateUser(User uObj,int uid);
	public User getUserById(int uid);
	public List<User> getAllUser();
	public boolean delUser(int uid);

}
