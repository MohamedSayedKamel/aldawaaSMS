package com.erabia.aldawaasms.bean.request;

import com.erabia.aldawaasms.enums.Language;

public class AldawaaSendGiftCardSMSRequest extends AldawaaSMSRequest{

	
	private String senderName;
	private String receiverName;
	private String amount;
	private String link;
	
	public AldawaaSendGiftCardSMSRequest(Language lang, String countryCode, String mobile, String senderName,
			String receiverName, String amount, String link) {
		super(lang, countryCode, mobile);
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.amount = amount;
		this.link = link;
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
