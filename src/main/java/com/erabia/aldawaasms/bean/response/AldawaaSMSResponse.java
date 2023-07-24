package com.erabia.aldawaasms.bean.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class AldawaaSMSResponse {
	@Expose
	@SerializedName("status")
	private boolean status;
	@Expose
	@SerializedName("errorCode")
	private String errorCode;
	@Expose
	@SerializedName("messageEN")
	private String messageEN;
	@Expose
	@SerializedName("messageAR")
	private String messageAR;
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessageEN() {
		return messageEN;
	}
	public void setMessageEN(String messageEN) {
		this.messageEN = messageEN;
	}
	public String getMessageAR() {
		return messageAR;
	}
	public void setMessageAR(String messageAR) {
		this.messageAR = messageAR;
	}
	
}
