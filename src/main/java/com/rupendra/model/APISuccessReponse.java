package com.rupendra.model;

import java.util.List;

public class APISuccessReponse {

	public APISuccessReponse(String status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	private String status;
	private String message;
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
