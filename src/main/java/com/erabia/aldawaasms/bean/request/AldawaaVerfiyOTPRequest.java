package com.erabia.aldawaasms.bean.request;

import com.erabia.aldawaasms.enums.Language;

public class AldawaaVerfiyOTPRequest extends AldawaaSMSRequest {
	private String otpCode;

	public AldawaaVerfiyOTPRequest(Language lang, String countryCode, String mobile, String otpCode) {
		super(lang, countryCode, mobile);
		this.otpCode = otpCode;
	}

	

	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}
}
