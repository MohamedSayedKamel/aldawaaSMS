package com.erabia.aldawaasms.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erabia.aldawaasms.bean.AldawaaSMSConfigBean;
import com.erabia.aldawaasms.bean.request.AldawaaGiftCardBean;
import com.erabia.aldawaasms.dto.error.ErrorDTO;
import com.erabia.aldawaasms.dto.error.ErrorListDTO;
import com.erabia.aldawaasms.enums.Language;
import com.erabia.aldawaasms.exception.AldawaaSMSException;
import com.erabia.aldawaasms.service.AldawaaSMSService;

@RestController
@RequestMapping(path = "/aldawaaSMS")
public class AldawaaSMSController {
    private static final String  BASE_URL = "http://webservices.al-dawaa.com/sms/api.php";
	private static final String  CLIENT_CODE = "ECOMMERCE";
	private static final String  CLIENT_SECRET = "S5RhkakGxUb5qTU647mNUKxDfXdYgqsvwotJvMMNgQIkQhisE5n4mWHoU";
	AldawaaSMSConfigBean bean =null;
	@Autowired
	private AldawaaSMSService aldawaaSMSService;	

	@PostMapping(path = "/sendOTP")
	public void sendOTP(@RequestParam(defaultValue = BASE_URL, required = true) String baseUrl,
			@RequestParam(defaultValue = CLIENT_CODE, required = true) String clientCode,
			@RequestParam(defaultValue = CLIENT_SECRET, required = true) String clientSecret,
			@RequestParam(required = true) Language lang,
			@RequestParam(required = true) String countryCode,
			@RequestParam(required = true) String mobile) throws AldawaaSMSException {
		
		 bean = new AldawaaSMSConfigBean(baseUrl, clientCode, clientSecret);
		aldawaaSMSService.sendOTP(bean,lang,countryCode,mobile);
	}
	 
	@PostMapping(path = "/verifyOTP")
	public void verifyOTP(@RequestParam(defaultValue = BASE_URL, required = true) String baseUrl,
			@RequestParam(defaultValue = CLIENT_CODE, required = true) String clientCode,
			@RequestParam(defaultValue = CLIENT_SECRET, required = true) String clientSecret,
			@RequestParam(required = true) Language lang,
			@RequestParam(required = true) String countryCode,
			@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String otpCode) throws AldawaaSMSException {
		
		 bean = new AldawaaSMSConfigBean(baseUrl, clientCode, clientSecret);
		aldawaaSMSService.verifyOTP(bean,lang,countryCode,mobile,otpCode);
	}
	
	@PostMapping(path = "/sendNewPassword")
	public void sendNewPassword(@RequestParam(defaultValue = BASE_URL, required = true) String baseUrl,
			@RequestParam(defaultValue = CLIENT_CODE, required = true) String clientCode,
			@RequestParam(defaultValue = CLIENT_SECRET, required = true) String clientSecret,
			@RequestParam(required = true) Language lang,
			@RequestParam(required = true) String countryCode,
			@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String password) throws AldawaaSMSException {
		
		 bean = new AldawaaSMSConfigBean(baseUrl, clientCode, clientSecret);
		aldawaaSMSService.sendNewPasswordSMS(bean,lang,countryCode,mobile,password);
	}
	
	@PostMapping(path = "/sendGiftCard")
	public void sendGiftCard(@RequestParam(defaultValue = BASE_URL, required = true) String baseUrl,
			@RequestParam(defaultValue = CLIENT_CODE, required = true) String clientCode,
			@RequestParam(defaultValue = CLIENT_SECRET, required = true) String clientSecret,
			@RequestParam(required = true) Language lang,
			@RequestParam(required = true) String countryCode,
			@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String senderName,
			@RequestParam(required = true) String receiverName,
			@RequestParam(required = true) String amount,
			@RequestParam(required = true) String link) throws AldawaaSMSException {
		
		 bean = new AldawaaSMSConfigBean(baseUrl, clientCode, clientSecret);
		AldawaaGiftCardBean giftCardBean = new AldawaaGiftCardBean(senderName,receiverName,amount,link);
		aldawaaSMSService.sendGiftCardSMS(bean,lang,countryCode,mobile,giftCardBean);
	}
	
	@PostMapping(path = "/sendNotifyMeSMS")
	public void sendNotifyMeSMS(@RequestParam(defaultValue = BASE_URL, required = true) String baseUrl,
			@RequestParam(defaultValue = CLIENT_CODE, required = true) String clientCode,
			@RequestParam(defaultValue = CLIENT_SECRET, required = true) String clientSecret,
			@RequestParam(required = true) Language lang,
			@RequestParam(required = true) String countryCode,
			@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String link) throws AldawaaSMSException {
		
		 bean = new AldawaaSMSConfigBean(baseUrl, clientCode, clientSecret);
		aldawaaSMSService.sendNotifyMeSMS(bean,lang,countryCode,mobile,link);
	}
	
	@ResponseBody
	@ExceptionHandler({ AldawaaSMSException.class })
	public ErrorListDTO handleAldawaaSMSException(final AldawaaSMSException ex){
		ErrorListDTO errorListDTO=new ErrorListDTO();
		ErrorDTO errorDTO=new ErrorDTO();
		
		errorDTO.setErrorCode(ex.getType().getErrorCode());
		errorDTO.setExceptionMessage(ex.getMessage());
		errorDTO.setReason(ex.getType().getErrorMessage());
		errorDTO.setType(ex.getType().toString());
		errorListDTO.setErrors(Arrays.asList(errorDTO));
		
		return errorListDTO;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler({ IllegalArgumentException.class })
	public ErrorListDTO handleIllegalArgumentException(final IllegalArgumentException ex){
		ErrorListDTO errorListDTO=new ErrorListDTO();
		ErrorDTO errorDTO=new ErrorDTO();
		
		errorDTO.setErrorCode(HttpStatus.BAD_REQUEST.toString());
		errorDTO.setExceptionMessage(ex.getMessage());
		errorDTO.setReason(ex.getMessage());
		errorDTO.setType("IllegalArgumentException");
		errorListDTO.setErrors(Arrays.asList(errorDTO));
		
		return errorListDTO;
	}
}
