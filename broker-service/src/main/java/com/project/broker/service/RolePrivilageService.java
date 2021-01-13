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
	
	public String getUserRole(String username);
	
	public boolean isRoleContainPrivilege(String role,String privilege);
	

}
