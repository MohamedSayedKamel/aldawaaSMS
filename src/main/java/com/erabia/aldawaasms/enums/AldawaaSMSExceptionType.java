package com.erabia.aldawaasms.enums;

import java.util.Arrays;

public enum AldawaaSMSExceptionType {
	
	BAD_REQUEST("BAD_REQUEST","Bad Request"),
	WORNG_CLIENT_SECRET_OR_CLIENT_ID("E-001","worng client secret or client id"),
	WORNG_ACTION_NUMBER("E-002","worng action number"),
	WORNG_MOBILE_NUMBER("E-003","worng mobile number"),
	WORNG_COUNTRY_CODE("E-004","worng country code"),
	WORNG_OTP("E-005","worng otp"),
	THE_OTP_IS_WORNG_OR_IT_IS_EXPIRED("E-006","the otp is worng or it is  expired"),
	MISSING_NEW_PASSWORD("E-007","missing new password"),
	MISSING_SMS_PARAMETERS("E-008","missing sms parameters"),
	MISSING_URL("E-009","missing url");
	
	
	private String errorCode;
	private String errorMessage;
	
	private AldawaaSMSExceptionType(String errorCode, String message) {
		this.errorCode = errorCode;
		this.errorMessage = message;
	}

	private AldawaaSMSExceptionType() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public static AldawaaSMSExceptionType valueOf(Object value) {
		return Arrays.stream(values()).filter(AldawaaSMSExceptionType -> AldawaaSMSExceptionType.errorCode.equals(value))
				.findFirst().get();
	}


}
