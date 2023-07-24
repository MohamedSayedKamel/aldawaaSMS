package com.erabia.aldawaasms.enums;

public enum AldawaaSMSActionType {
	
	SEND_OTP("01.0"),
	VERIFY_OTP("02.0"),
	SEND_NEW_PASSWORD_SMS("03.0"),
	SEND_GIFT_CARD_SMS("04.0"),
	SEND_NOTIFY_Me_SMS("05.0");
	private String code;
		
	private AldawaaSMSActionType(String code) {
		this.code = code;
	}

	private AldawaaSMSActionType() {
	}

	public String getCode() {
		return code;
	}

}
