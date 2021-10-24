package com.example.myserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

// Exception handler class
@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler
	public ResponseEntity<?> toResponse(Throwable throwable, HttpServletResponse response) {

		throwable.printStackTrace();

		//	ErrorBean errorBean;
		if(throwable.getMessage().equals("Session Expired")) {

			//Setting error number
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized access...");
		}

		return ResponseEntity.status(500).body("General Error");
	}
}