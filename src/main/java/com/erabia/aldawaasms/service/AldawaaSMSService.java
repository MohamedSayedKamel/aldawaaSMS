package com.erabia.aldawaasms.service;

import com.erabia.aldawaasms.bean.AldawaaSMSConfigBean;
import com.erabia.aldawaasms.bean.request.AldawaaGiftCardBean;
import com.erabia.aldawaasms.enums.Language;
import com.erabia.aldawaasms.exception.AldawaaSMSException;

public interface AldawaaSMSService {
	public void sendOTP(AldawaaSMSConfigBean configBean, Language lang, String countryCode,
			String mobileNumber) throws AldawaaSMSException;

	public void verifyOTP(AldawaaSMSConfigBean configBean, Language lang, String countryCode,
			String mobileNumber,String otpCode) throws AldawaaSMSException;

	public void sendNewPasswordSMS(AldawaaSMSConfigBean configBean, Language lang, String countryCode,
			String mobileNumber,String password) throws AldawaaSMSException;
	
	public void sendGiftCardSMS(AldawaaSMSConfigBean configBean, Language lang, String countryCode,
			String mobileNumber,AldawaaGiftCardBean giftCardBean) throws AldawaaSMSException;
	
	public void sendNotifyMeSMS(AldawaaSMSConfigBean configBean, Language lang, String countryCode,
			String mobileNumber,String link) throws AldawaaSMSException;
}
