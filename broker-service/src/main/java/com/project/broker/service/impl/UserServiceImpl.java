package com.project.broker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.broker.dto.UserAuth;
import com.project.broker.exception.CustomRuntimeException;
import com.project.broker.model.RoleModel;
import com.project.broker.model.UserModel;
import com.project.broker.model.UserRolePrivilegeModel;
import com.project.broker.repository.PrivilegeRepository;
import com.project.broker.repository.RoleRepository;
import com.project.broker.repository.UserRepository;
import com.project.broker.repository.UserRolePrivilegeRepository;
import com.project.broker.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author saumeen
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PrivilegeRepository privilageRepo;

	@Autowired
	UserRolePrivilegeRepository userRolePrivilegeRepository;

	@Override
	public void addUser(UserAuth userAuth) {
		UserModel user = userRepository.findByUsernameAndEmail(userAuth.getUsername().toUpperCase(), userAuth.getEmail().toUpperCase());

		if (user == null) {
			UserModel userModel = new UserModel();
			userModel.setEmail(userAuth.getEmail().toUpperCase());
			userModel.setUsername(userAuth.getUsername().toUpperCase());
			userModel.setPassword(new BCryptPasswordEncoder().encode(userAuth.getPassword()));
			userModel.setContactno(userAuth.getContactno());
			RoleModel roleModel = roleRepository.findByRoleName(userAuth.getRoleName().toUpperCase());
			if (roleModel == null) {
				throw new CustomRuntimeException("Role not exits !!");
			} else {
				UserRolePrivilegeModel userRolePrivilegeModel = new UserRolePrivilegeModel();
				userRolePrivilegeModel.setUser(userModel);
				userRolePrivilegeModel.setRole(roleModel);

				userRolePrivilegeRepository.save(userRolePrivilegeModel);
			}

		} else {
			throw new CustomRuntimeException("User Exits Already!!");
		}

	}

	@Override
	public UserAuth isValid(String username, String password) {

		log.info("Username :: {} pasword :: {} encoed pasword --> {}", username, password);
		UserModel user = userRepository.findByUsername(username.toUpperCase());
		if (user != null && new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			UserAuth userAuth = new UserAuth();
			userAuth.setUsername(user.getUsername());
			userAuth.setEmail(user.getEmail());
			return userAuth;
		}
		return null;

	}

	

}
