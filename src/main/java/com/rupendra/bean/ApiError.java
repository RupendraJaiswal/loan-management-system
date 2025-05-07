package com.rupendra.bean;

import java.time.LocalDateTime;

public class ApiError {
	private LocalDateTime timestamp;
	private String message;
	private String uri;
	private int status;

	public ApiError(LocalDateTime timestamp, String message, String uri, int status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.uri = uri;
		this.status = status;

	}

	public ApiError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
