package com.appstudent.app.ws.ui.model.response;

public enum ErrorMessages {
	
	RECORD_ALREADY_EXISTS("Entry already exists"),
	INVALID_USER_REQUEST("There is no student with the providedId"),
	INSUFICIENT_DATA_PROVIDED("Not enought data was provided, please fill all fields.");
	
	private String errorMessage;
	
	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	
}
