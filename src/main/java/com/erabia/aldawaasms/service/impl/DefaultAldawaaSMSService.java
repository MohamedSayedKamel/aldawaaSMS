package com.erabia.aldawaasms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erabia.aldawaasms.bean.AldawaaSMSConfigBean;
import com.erabia.aldawaasms.bean.request.AldawaaGiftCardBean;
import com.erabia.aldawaasms.bean.response.AldawaaSMSResponse;
import com.erabia.aldawaasms.bean.response.AldawaaSendGiftCardSMSResponse;
import com.erabia.aldawaasms.bean.response.AldawaaSendNewPasswordSMSResponse;
import com.erabia.aldawaasms.bean.response.AldawaaSendNotifyMeResponse;
import com.erabia.aldawaasms.bean.response.AldawaaSendOTPResponse;
import com.erabia.aldawaasms.bean.response.AldawaaVerifyOTPResponse;
import com.erabia.aldawaasms.enums.AldawaaSMSActionType;
import com.erabia.aldawaasms.enums.AldawaaSMSExceptionType;
import com.erabia.aldawaasms.enums.Language;
import com.erabia.aldawaasms.exception.AldawaaSMSException;
import com.erabia.aldawaasms.exception.WebServiceApiUnirestException;
import com.erabia.aldawaasms.service.AldawaaSMSService;
import com.erabia.aldawaasms.util.WebServiceApiUnirestUtil;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service("aldawaaSMSService")
public class DefaultAldawaaSMSService implements AldawaaSMSService{
	
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String CONFIG_BEAN_IS_NULL ="Aldawaa SMS Config Bean is null";
	private static final String BASEURL_IS_NULL ="base url is null";
	private static final String CLIENTID_IS_NULL ="client id is null";
	private static final String CLIENT_SECRET_IS_NULL ="client secret is null";
	private static final String SENDER_NAME_IS_NULL = "sender name is null";
	private static final String LINK_IS_NULL ="link is null";
	private static final String AMOUNT_IS_NULL ="amount is null";
	private static final String RECEIVER_NAME_IS_NULL = "receiver name is null";
	private static final String COUNTRY_CODE_IS_NULL ="country code is null";
	private static final String MOBILE_NUMBER_IS_NULL = "mobile number is null";
	private static final String LANGUAGE_IS_NULL = "language is null";
	private static final String OTP_IS_NULL = "otp is null";
	private static final String PASSWORD_IS_NULL = "password is null";

	private static final Gson GSON = (new GsonBuilder().setPrettyPrinting()).create();
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAldawaaSMSService.class);
	private static final Map<String,AldawaaSMSExceptionType> errors;


	private  Map<String, AldawaaSMSExceptionType> getErrors() {
		return errors;
	}

	static
	{
		errors = new HashMap<>();
		for (AldawaaSMSExceptionType type : AldawaaSMSExceptionType.values()) 
			errors.put(type.getErrorCode(), type);
	}

	@Override
	public void sendOTP(AldawaaSMSConfigBean configBean, Language lang, String countryCode,
			String mobileNumber) throws AldawaaSMSException {
		validatAldawaaSMSConfigBean(configBean);
		validateMainParameters(lang, countryCode, mobileNumber);
		final Map<String, String> header = setHeader(configBean);
		
		getMainParameters(lang, countryCode, mobileNumber, AldawaaSMSActionType.SEND_OTP);
		Map<String, Object> request = getMainParameters(lang, countryCode, mobileNumber, AldawaaSMSActionType.SEND_OTP);
		
		LOG.info("sending OTP");
		AldawaaSendOTPResponse response = null;
		try {
			LOG.info("sendOTP ->sending request");
			response = WebServiceApiUnirestUtil.post(configBean.getBaseURL(), header, request,AldawaaSendOTPResponse.class);
		} catch (WebServiceApiUnirestException e) {
			LOG.info("sendOTP ->Bad Request");
			throw new AldawaaSMSException(AldawaaSMSExceptionType.BAD_REQUEST, e.getMessage(),
					e.getRequestData(), e.getResponseData(), e.getHeaders(), e.getBaseURL(),e.getMessage(),e.getMessage());
		}
		LOG.info("sendOTP -> Request has been sent successfully.");
		
		validatAldawaaSMSResponse(response,GSON.toJson(request), GSON.toJson(header), configBean.getBaseURL());
	}

	@Override
	public void verifyOTP(AldawaaSMSConfigBean configBean, Language lang,String countryCode, 
			String mobileNumber, String otpCode) throws AldawaaSMSException {
		validatAldawaaSMSConfigBean(configBean);
		validateMainParameters(lang, countryCode, mobileNumber);
		Preconditions.checkArgument(!Strings.isBlank(otpCode), OTP_IS_NULL);
		final Map<String, String> header = setHeader(configBean);
		Map<String, Object> request = getMainParameters(lang, countryCode, mobileNumber, AldawaaSMSActionType.VERIFY_OTP);
		request.put("otp", otpCode);

		LOG.info("verfiy OTP");
		AldawaaVerifyOTPResponse response = null;
		try {
			LOG.info("verfiy OTP ->sending request");

			response = WebServiceApiUnirestUtil.post(configBean.getBaseURL(), header, request,AldawaaVerifyOTPResponse.class);

		} catch (WebServiceApiUnirestException e) {
			LOG.info("verify OTP ->Bad Request");
			throw new AldawaaSMSException(AldawaaSMSExceptionType.BAD_REQUEST, e.getMessage(), e.getRequestData(),
					e.getResponseData(), e.getHeaders(), e.getBaseURL(), e.getMessage(), e.getMessage());
		}

		LOG.info("verify OTP -> Request has been sent successfully.");

		validatAldawaaSMSResponse(response, GSON.toJson(request), GSON.toJson(header), configBean.getBaseURL());

	}

	@Override
	public void sendNewPasswordSMS(AldawaaSMSConfigBean configBean, Language lang,String countryCode, 
			String mobileNumber, String password) throws AldawaaSMSException {
		validateMainParameters(lang, countryCode, mobileNumber);
		Preconditions.checkArgument(!Strings.isBlank(password), PASSWORD_IS_NULL);
		final Map<String, String> header = setHeader(configBean);

		Map<String, Object> request = getMainParameters(lang, countryCode, mobileNumber, AldawaaSMSActionType.SEND_NEW_PASSWORD_SMS);
		request.put("password", password);

		LOG.info("send new password ");
		AldawaaSendNewPasswordSMSResponse response = null;
		try {
			LOG.info("send new password ->sending request");

			response = WebServiceApiUnirestUtil.post(configBean.getBaseURL(), header, request,AldawaaSendNewPasswordSMSResponse.class);

		} catch (WebServiceApiUnirestException e) {
			LOG.info("send new password ->Bad Request");
			throw new AldawaaSMSException(AldawaaSMSExceptionType.BAD_REQUEST, e.getMessage(), e.getRequestData(),
					e.getResponseData(), e.getHeaders(), e.getBaseURL(), e.getMessage(), e.getMessage());
		}

		LOG.info("send new password -> Request has been sent successfully.");

		validatAldawaaSMSResponse(response, GSON.toJson(request), GSON.toJson(header), configBean.getBaseURL());
	}
	@Override
	public void sendGiftCardSMS(AldawaaSMSConfigBean configBean, Language lang,String countryCode, String mobileNumber, AldawaaGiftCardBean giftCardBean) throws AldawaaSMSException {
		validateMainParameters(lang, countryCode, mobileNumber);
		validateAldawaaGiftBean(giftCardBean);
		final Map<String, String> header = setHeader(configBean);
		
		Map<String, Object> request = getMainParameters(lang, countryCode, mobileNumber, AldawaaSMSActionType.SEND_GIFT_CARD_SMS);
		request.put("senderName", giftCardBean.getSenderName());
		request.put("receiverName", giftCardBean.getReceiverName());
		request.put("amount", giftCardBean.getAmount());
		request.put("link", giftCardBean.getLink());
		
		LOG.info("send Gift Card SMS");
		AldawaaSendGiftCardSMSResponse response = null;
		try {
			LOG.info("send Gift Card SMS ->sending request");

			response = WebServiceApiUnirestUtil.post(configBean.getBaseURL(), header, request,AldawaaSendGiftCardSMSResponse.class);

		} catch (WebServiceApiUnirestException e) {
			LOG.info("send Gift Card SMS ->Bad Request");
			throw new AldawaaSMSException(AldawaaSMSExceptionType.BAD_REQUEST, e.getMessage(), e.getRequestData(),
					e.getResponseData(), e.getHeaders(), e.getBaseURL(), e.getMessage(), e.getMessage());
		}

		LOG.info("Gift Card SMS -> Request has been sent successfully.");
		validatAldawaaSMSResponse(response, GSON.toJson(request), GSON.toJson(header), configBean.getBaseURL());

	}
	
	@Override
	public void sendNotifyMeSMS(AldawaaSMSConfigBean configBean, Language lang, String countryCode, String mobileNumber,
			String link) throws AldawaaSMSException {
		validateMainParameters(lang, countryCode, mobileNumber);
		Preconditions.checkArgument(!Strings.isBlank(link), LINK_IS_NULL);
		final Map<String, String> header = setHeader(configBean);
		Map<String, Object> request = getMainParameters(lang, countryCode, mobileNumber,AldawaaSMSActionType.SEND_NOTIFY_Me_SMS);
		request.put("link", link);
		LOG.info("sending Notify me sms");
		AldawaaSendNotifyMeResponse response = null;
		try {
			LOG.info("sendNotifyMe ->sending request");
			response = WebServiceApiUnirestUtil.post(configBean.getBaseURL(), header, request,
					AldawaaSendNotifyMeResponse.class);
		} catch (WebServiceApiUnirestException e) {
			LOG.info("sendNotifyMe ->Bad Request");
			throw new AldawaaSMSException(AldawaaSMSExceptionType.BAD_REQUEST, e.getMessage(), e.getRequestData(),
					e.getResponseData(), e.getHeaders(), e.getBaseURL(), e.getMessage(), e.getMessage());
		}
		LOG.info("sendNotifyMe -> Request has been sent successfully.");

		validatAldawaaSMSResponse(response, GSON.toJson(request), GSON.toJson(header), configBean.getBaseURL());
	}

	private void validateAldawaaGiftBean(AldawaaGiftCardBean giftCardBean) {
		Preconditions.checkArgument(!Strings.isBlank(giftCardBean.getSenderName()), SENDER_NAME_IS_NULL);
		Preconditions.checkArgument(!Strings.isBlank(giftCardBean.getReceiverName()), RECEIVER_NAME_IS_NULL);
		Preconditions.checkArgument(!Strings.isBlank(giftCardBean.getAmount()), AMOUNT_IS_NULL);
		Preconditions.checkArgument(!Strings.isBlank(giftCardBean.getLink()), LINK_IS_NULL);
		
	}

	private void validatAldawaaSMSConfigBean(AldawaaSMSConfigBean aldawaaSMSConfigBean) {
		Preconditions.checkArgument(aldawaaSMSConfigBean!=null, CONFIG_BEAN_IS_NULL);
		Preconditions.checkArgument(!Strings.isBlank(aldawaaSMSConfigBean.getBaseURL()), BASEURL_IS_NULL);
		Preconditions.checkArgument(!Strings.isBlank(aldawaaSMSConfigBean.getClientCode()),CLIENTID_IS_NULL);
		Preconditions.checkArgument(!Strings.isBlank(aldawaaSMSConfigBean.getClientSecret()), CLIENT_SECRET_IS_NULL);
	}

	private Map<String, String> setHeader(AldawaaSMSConfigBean configBean) {
		final Map<String, String> header = new HashMap<>();
		header.put("ClientCode", configBean.getClientCode());
		header.put("ClientSecret", configBean.getClientSecret());
		header.put("Content-Type", CONTENT_TYPE);
		return header;
	}
	
	private Map<String, Object> getMainParameters(Language lang, String countryCode,
			String mobileNumber, AldawaaSMSActionType actionType) {
		Map<String, Object> request = new HashMap<>();
		request.put("lang", lang.getCode());
		request.put("countryCode", countryCode);
		request.put("mobile", mobileNumber);
		request.put("action", actionType.getCode());
		return request;
	}

	private void validatAldawaaSMSResponse(AldawaaSMSResponse response, String request, String header,String baseURL) throws AldawaaSMSException {
		
		LOG.info("checking the response");
		AldawaaSMSExceptionType aldawaaSMSExceptionType = getAldawaaSMSExceptionType(response);
		
		if(aldawaaSMSExceptionType ==null)
		{
			LOG.info("Aldawaa Response -> Response is been successfully.");
			return;
		}
		
		LOG.error("There is an error in the Aldawaa Response");
		
		throw new AldawaaSMSException(aldawaaSMSExceptionType, aldawaaSMSExceptionType.getErrorMessage(),
				request,GSON.toJson(response), header,baseURL,response.getMessageAR(),response.getMessageEN());
		
	}

	private AldawaaSMSExceptionType getAldawaaSMSExceptionType(AldawaaSMSResponse response) {
		if (response == null) 
			return AldawaaSMSExceptionType.BAD_REQUEST;
	
		if (response.getStatus())
			return null;

		AldawaaSMSExceptionType type = getErrors().get(response.getErrorCode());
		return type == null ? AldawaaSMSExceptionType.BAD_REQUEST : type;
	}
	private void validateMainParameters(Language lang, String countryCode, String mobileNumber) {
		Preconditions.checkArgument(lang != null, LANGUAGE_IS_NULL);
		Preconditions.checkArgument(!Strings.isBlank(countryCode), COUNTRY_CODE_IS_NULL);
		Preconditions.checkArgument(!Strings.isBlank(mobileNumber), MOBILE_NUMBER_IS_NULL);
	}
	

}
