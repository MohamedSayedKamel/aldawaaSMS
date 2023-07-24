package com.erabia.aldawaasms.dto.error;

import java.io.Serializable;
import java.util.List;

public class ErrorListDTO implements Serializable

{
	private static final long serialVersionUID = 1L;

	private List<ErrorDTO> errors;

	public ErrorListDTO() {
		// default constructor
	}

	public void setErrors(final List<ErrorDTO> errors) {
		this.errors = errors;
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}

}