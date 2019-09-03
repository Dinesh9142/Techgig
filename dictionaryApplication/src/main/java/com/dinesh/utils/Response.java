package com.dinesh.utils;

import java.io.Serializable;

public class Response implements Serializable  {

	private static final long serialVersionUID = -7571113449729273240L;
	
	Boolean status;
	Object data;
	String errorMessages;
	String messages;
	
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}
	
}
