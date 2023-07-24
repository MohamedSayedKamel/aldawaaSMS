package com.erabia.aldawaasms.bean.request;

import com.erabia.aldawaasms.enums.Language;

public class AldawaaSendOTPResquest extends AldawaaSMSRequest{

	public AldawaaSendOTPResquest(Language lang, String countryCode, String mobile) {
		super(lang, countryCode, mobile);
	}
	

}
