package com.ws.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorCode {
	private String errorMessage;

	public ErrorCode() {
			}

	public ErrorCode(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	

}
