package com.erabia.aldawaasms.bean.request;

import com.erabia.aldawaasms.enums.Language;

public class AldawaaSendNewPasswordSMSRequest extends AldawaaSMSRequest{
	
	private String password;
	
	public AldawaaSendNewPasswordSMSRequest(Language lang, String countryCode, String mobile, String password) {
		super(lang, countryCode, mobile);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
