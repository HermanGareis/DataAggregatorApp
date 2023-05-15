package com.dataaggregator.dataaggregator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception class is used to represent a Bad Request error (HTTP 400) in
 * the application.
 * 
 * It extends the RuntimeException class and sets the HTTP status code to
 * BAD_REQUEST.
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	public BadRequestException(String msg) {
		super(msg);
	}
}