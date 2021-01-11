/**
 * 
 */
package com.project.broker.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.broker.dto.ResponseDTO;
import com.project.broker.dto.RoleDto;
import com.project.broker.dto.UserAuth;
import com.project.broker.service.RolePrivilageService;
import com.project.broker.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author saumeen
 *
 */

@RestController
@RequestMapping("/auth")
@Slf4j
public class AutheticationController {

	@Autowired
	UserService userService;

	@Autowired
	private Environment environment;

	@GetMapping("/check")
	public String check() {

		return "Working on port " + environment.getProperty("local.server.port");
	}

	@PostMapping("/addUser")
	public ResponseDTO signupDetails(@RequestBody UserAuth userAuth) {
		log.info("Executing addItem method");

		try {
			userService.addUser(userAuth);
		} catch (Exception e) {
			log.error("Error -> {}", e.getMessage());
			return new ResponseDTO(HttpStatus.OK.value(), false, "User not added!!", null);
		}

		return new ResponseDTO(HttpStatus.OK.value(), true, "User Added", null);

	}

	@PostMapping("/loginUser")
	public ResponseDTO loginUserValidation(@RequestBody UserAuth userAuth) {

		UserAuth user = userService.isValid(userAuth.getUsername(), userAuth.getPassword());

		if (user == null) {
			return new ResponseDTO(HttpStatus.OK.value(), false, "Not Found User!!", "");
		}
		return new ResponseDTO(HttpStatus.OK.value(), true, "User found!!", user);
	}

}
