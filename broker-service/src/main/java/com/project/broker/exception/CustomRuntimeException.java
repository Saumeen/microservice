package com.project.broker.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8710935651633532794L;

	private String message;

	public CustomRuntimeException(String message) {
		this.message = message;

	}

	@Override
	public String getLocalizedMessage() {

		return message;
	}

	@Override
	public void printStackTrace() {
		log.info("{}", message);
	}

	@Override
	public String getMessage() {
		return message;
	}

}
