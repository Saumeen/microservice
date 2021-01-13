package com.project.broker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.broker.dto.IpDto;
import com.project.broker.service.CacheService;
import com.project.broker.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MasterController {

	@Autowired
	CommonService commonService;
	@Autowired
	CacheService cacheService;
	
	@PostMapping("/master/sendIpAddress")
	public String sendIpAddress(@RequestBody IpDto ipDto) {
		log.info("Ip setting method called");
		return commonService.setIpAddress(ipDto);

	}
	
	@GetMapping("/master/evictCache")
	public void evictCache() {
		cacheService.evictCache();
	}
}
