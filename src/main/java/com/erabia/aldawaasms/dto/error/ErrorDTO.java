package com.erabia.aldawaasms.dto.error;

import java.io.Serializable;

public class ErrorDTO implements Serializable

{

	private static final long serialVersionUID = 1L;

	private String type;

	private String reason;

	private String message;

	private String subjectType;

	private String subject;

	private String errorCode;

	private String language;

	private Integer position;

	private String exceptionMessage;

	public ErrorDTO() {
		// default constructor
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setSubjectType(final String subjectType) {
		this.subjectType = subjectType;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setErrorCode(final String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setPosition(final Integer position) {
		this.position = position;
	}

	public Integer getPosition() {
		return position;
	}

	public void setExceptionMessage(final String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}