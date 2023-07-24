package com.erabia.aldawaasms.exception;

/**
 * 
 *
 */
public class WebServiceApiUnirestException extends Exception {
	private static final long serialVersionUID = -2727878348941788892L;
	private final String requestData;
	private final String responseData;
	private final String headers;
	private final String baseURL;

	public WebServiceApiUnirestException(final String message,
			final String requestData, final String responseData, final String headers, final String baseURL) {
		super(message);
		this.responseData = responseData;
		this.requestData = requestData;
		this.baseURL=baseURL;
		this.headers=headers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public String getRequestData() {
		return requestData;
	}

	public String getResponseData() {
		return responseData;
	}

	public final String getHeaders() {
		return headers;
	}

	public final String getBaseURL() {
		return baseURL;
	}
	
	
}