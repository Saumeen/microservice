package com.project.broker.service;

import com.project.broker.dto.UserAuth;


public interface UserService {
	
	/**
	 * 
	 * Add new user in the system
	 * @param userAuth
	 * @throws Exception
	 */
	public void addUser(UserAuth userAuth);	
	
	/**
	 * For Login Authenticate User
	 * @param username
	 * @param password
	 * @return
	 */
	public UserAuth isValid(String username,String password);
	
	

}
