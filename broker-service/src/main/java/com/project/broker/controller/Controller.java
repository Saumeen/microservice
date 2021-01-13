package com.project.broker.controller;

import static com.project.broker.constant.Constant.ADD;
import static com.project.broker.constant.Constant.DELETE;
import static com.project.broker.constant.Constant.SUCCESSFULLY_COMPLETED;
import static com.project.broker.constant.Constant.UPDATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.broker.dto.HouseDetailsDto;
import com.project.broker.dto.LoginDto;
import com.project.broker.dto.ResponseDTO;
import com.project.broker.dto.UserAuth;
import com.project.broker.exception.CustomRuntimeException;
import com.project.broker.service.CommonService;
import com.project.broker.service.HouseDetailService;
import com.project.broker.service.RolePrivilageService;
import com.project.broker.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Controller {

	@Autowired
	private RolePrivilageService rolePrivilageService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private HouseDetailService houseDetailService;

	@Autowired
	private UserService userService;

	@PostMapping("/verifyLogin")
	public ResponseDTO loginUserValidation(@RequestBody LoginDto loginDto) {

		UserAuth user = userService.isValid(loginDto.getUsername(), loginDto.getPassword());

		if (user == null) {
			return new ResponseDTO(HttpStatus.OK.value(), false, "Not Found User!!", "");
		}
		return new ResponseDTO(HttpStatus.OK.value(), true, "User found!!", user);
	}

	@GetMapping("/getPrivilageByRole/{role}")
	public ResponseDTO getPrivilageByRole(@PathVariable String role) {
		log.info("Role is {}", role);
		return new ResponseDTO(HttpStatus.OK.value(), true, SUCCESSFULLY_COMPLETED,
				rolePrivilageService.getPrivilegeByRole(role));
	}

	@GetMapping("/getUserRole/{username}")
	public ResponseDTO getUserRole(@PathVariable String username) {
		try {
			return new ResponseDTO(HttpStatus.OK.value(), true, SUCCESSFULLY_COMPLETED,
					rolePrivilageService.getUserRole(username));
		} catch (CustomRuntimeException e) {
			log.error(e.getMessage());
			return new ResponseDTO(HttpStatus.OK.value(), false, e.getMessage(), "");
		}
	}

	@GetMapping("/isUserCanAdd/{role}")
	public ResponseDTO isUserCanAdd(@PathVariable String role) {
		return new ResponseDTO(HttpStatus.OK.value(), true, SUCCESSFULLY_COMPLETED,
				rolePrivilageService.isRoleContainPrivilege(role, ADD));
	}

	@GetMapping("/isUserCanUpdate/{role}")
	public ResponseDTO isUserCanUpdate(@PathVariable String role) {
		return new ResponseDTO(HttpStatus.OK.value(), true, SUCCESSFULLY_COMPLETED,
				rolePrivilageService.isRoleContainPrivilege(role, UPDATE));
	}

	@GetMapping("/isUserCanDelete/{role}")
	public ResponseDTO isUserCanDelete(@PathVariable String role) {
		return new ResponseDTO(HttpStatus.OK.value(), true, SUCCESSFULLY_COMPLETED,
				rolePrivilageService.isRoleContainPrivilege(role, DELETE));
	}

	@PostMapping("/addHouseDetail")
	public ResponseDTO addHouseDetail(@RequestBody HouseDetailsDto houseDetailsDto) {
		try {
			houseDetailService.addHouseDetails(houseDetailsDto);
		} catch (CustomRuntimeException e) {
			log.error(e.getMessage());
			return new ResponseDTO(HttpStatus.OK.value(), false, e.getMessage(), "");
		}
		return new ResponseDTO(HttpStatus.OK.value(), true, SUCCESSFULLY_COMPLETED, "");

	}
	
	
	@GetMapping("/getHouseDetails/{username}")
	public ResponseDTO getHouseDetails(@PathVariable String username) {
		return new ResponseDTO(HttpStatus.OK.value(), true, SUCCESSFULLY_COMPLETED, houseDetailService.getHouseDetailService(username));
		
		
	}
}
