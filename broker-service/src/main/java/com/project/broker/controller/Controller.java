package com.project.broker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.broker.dto.IpDto;
import com.project.broker.dto.ResponseDTO;
import com.project.broker.dto.RoleDto;
import com.project.broker.service.CommonService;
import com.project.broker.service.RolePrivilageService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Controller {

	@Autowired
	RolePrivilageService rolePrivilageService;

	@Autowired
	CommonService commonService;

	@PostMapping("/admin/addRole")
	public ResponseDTO addrole(@RequestBody RoleDto roleDto) {

		log.info("Role Dto started {}", roleDto.toString());
		rolePrivilageService.addRole(roleDto);
		return new ResponseDTO(HttpStatus.OK.value(), true, "Role added!!", null);
	}

//	@GetMapping("/admin/getAllRole")
//	public ResponseDTO getAllRole() {
//		log.info("Get all role method called ...");
//		return new ResponseDTO(HttpStatus.OK.value(),true,"Successfully completed !!", rolePrivilageService.getAllRoles());
//	}
//	
	@GetMapping("/admin/getPrivilageByRole/{role}")
	public ResponseDTO getPrivilageByRole(@PathVariable String role) {
		log.info("Role is {}", role);
		return new ResponseDTO(HttpStatus.OK.value(), true, "Successfully completed !!",
				rolePrivilageService.getPrivilegeByRole(role));
	}

	@PostMapping("/master/sendIpAddress")
	public String sendIpAddress(@RequestBody IpDto ipDto) {
		log.info("Ip setting method called");
		return commonService.setIpAddress(ipDto);

	}
}
