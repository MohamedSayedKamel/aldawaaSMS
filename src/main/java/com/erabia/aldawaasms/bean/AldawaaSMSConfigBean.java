package com.erabia.aldawaasms.bean;

public class AldawaaSMSConfigBean {

	private String baseURL;
	private String clientCode;
	private String clientSecret;	
	

	public AldawaaSMSConfigBean(String baseURL, String clientcode, String clientSecret) {
		super();
		this.baseURL = baseURL;
		this.clientCode=clientcode;
		this.clientSecret=clientSecret;		
	}



	public String getBaseURL() {
		return baseURL;
	}



	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}



	public String getClientCode() {
		return clientCode;
	}



	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}



	public String getClientSecret() {
		return clientSecret;
	}



	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}



}
