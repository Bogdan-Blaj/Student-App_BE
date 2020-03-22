package com.appstudent.app.ws.exceptions;

public class StudentServiceException extends RuntimeException{

	private static final long serialVersionUID = 6525268396611849615L;

	public StudentServiceException(String message) {
		super(message);
	}
}
