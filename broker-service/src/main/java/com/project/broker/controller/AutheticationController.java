/**
 * 
 */
package com.project.broker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.broker.dto.ResponseDTO;
import com.project.broker.dto.RoleDto;
import com.project.broker.dto.UserAuth;
import com.project.broker.exception.CustomRuntimeException;
import com.project.broker.service.RolePrivilageService;
import com.project.broker.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author saumeen
 *
 */

@RestController
@RequestMapping("/admin")
@Slf4j
public class AutheticationController {

	@Autowired
	UserService userService;

	@Autowired
	private Environment environment;
	
	@Autowired
	RolePrivilageService rolePrivilageService;


	@GetMapping("/check")
	public String check() {

		return "Working on port " + environment.getProperty("local.server.port");
	}

	@PostMapping("/addUser")
	public ResponseDTO signupDetails(@RequestBody UserAuth userAuth) {
		log.info("Executing addItem method");

		try {
			userService.addUser(userAuth);
		} catch (CustomRuntimeException e) {
			log.error("Error -> {}", e.getMessage());
			return new ResponseDTO(HttpStatus.OK.value(), false,e.getMessage(), null);
		}

		return new ResponseDTO(HttpStatus.OK.value(), true, "User Added", null);

	}

	@PostMapping("/addRole")
	public ResponseDTO addrole(@RequestBody RoleDto roleDto) {

		try {
			log.info("Role Dto started {}", roleDto.toString());
			rolePrivilageService.addRole(roleDto);
			return new ResponseDTO(HttpStatus.OK.value(), true, "Role added!!", null);
		} catch (CustomRuntimeException e) {
			log.error("{}", e.getMessage());
			return new ResponseDTO(HttpStatus.OK.value(), false, e.getMessage(), null);

		}
	}
	

	

}
