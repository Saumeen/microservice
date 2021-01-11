package com.project.broker.service;

import java.util.List;

import com.project.broker.dto.RoleDto;

public interface RolePrivilageService {

	/**
	 * Adding role
	 * @param roleDtos
	 */
	public void addRole(RoleDto roleDtos);
	
	public List<String> getPrivilegeByRole(String roleNames);
	
	
	

}
