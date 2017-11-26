package com.rivanmota.exception;

import com.rivanmota.model.Errors;

public class ErrorException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Errors errors;

	public ErrorException(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

}