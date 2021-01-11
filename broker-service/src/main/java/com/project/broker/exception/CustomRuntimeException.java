package com.project.broker.exception;

import lombok.extern.slf4j.Slf4j;


public class CustomRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8710935651633532794L;

	public CustomRuntimeException(String message) {
		super(message);

	}

}
