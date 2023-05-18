package com.sanches.coutingOfVotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ObjectAlreadyExists extends Exception {

	public ObjectAlreadyExists(String message) {
		super(message);
	}
}