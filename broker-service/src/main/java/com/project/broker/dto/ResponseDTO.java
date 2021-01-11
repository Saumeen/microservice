package com.project.broker.dto;

import java.util.Map;

import lombok.Data;


@Data
public class ResponseDTO {
	private int status;
	private boolean success;
	private String message;
	private Object data;

	
	
	public ResponseDTO(int status, boolean success, String message, Object data) {
		super();
		this.status = status;
		this.success = success;
		this.message = message;
		this.data = data;
	}



}