package com.erabia.aldawaasms.bean.request;

import com.erabia.aldawaasms.enums.Language;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AldawaaSMSRequest {
	@Expose
	@SerializedName("lang")
	private Language lang;
	@Expose
	@SerializedName("countryCode")
	private String countryCode;
	@Expose
	@SerializedName("mobile")
	private String mobile;
	
	public AldawaaSMSRequest(Language lang,String countryCode,String mobile) {
		super();
		this.lang=lang;
		this.countryCode=countryCode;
		this.mobile=mobile;
	}

	

	public Language getLang() {
		return lang;
	}

	public void setLang(Language lang) {
		this.lang = lang;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
