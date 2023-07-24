package com.erabia.aldawaasms.enums;

public enum Language {
	EN("en"),AR("ar");
	
	private String code;

	private Language(String code) {
		
		this.code=code;
	}

	public String getCode() {
		return code;
	}
}
