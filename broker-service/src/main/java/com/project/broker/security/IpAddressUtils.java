package com.project.broker.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class IpAddressUtils {
	
	
	private HttpServletRequest request;

	@Autowired
	public IpAddressUtils(HttpServletRequest request) {
		this.request = request;
	}

	public String getIpAddress() {
		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");

			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		log.info("{}", request);
		return remoteAddr;
	}

}
