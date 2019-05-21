/**
 * 
 */
package com.somil.SmsService;

import java.io.IOException;

import java.util.Random;

/**
 * @author Somil Khandelwal
 *
 *	Specific SMS RENDERs
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import bean.StatusBean;

public class SmsSender {
	public static final String ACCOUNT_SID = "***********";
	public static final String AUTH_TOKEN = "*************";

	public static final String MOBILE_NUMBER = "*********";

	public static int generateRandomNumer() {
		int m = (int) Math.pow(10, 6 - 1);
		return m + new Random().nextInt(9 * m);
	}

	public static String generateMessage(String otp, String customMessage) {
		return "Your OTP is " + otp+ customMessage;
	}

	public static String appendNumberAndCountryCode(String mobileNumber, String countryCode) {
		return "+" + countryCode + mobileNumber;
	}

	public static StatusBean sendMessage(String mobileNumber, String countryCode, String firstname, String lastName, String otp, String customMessage) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		StatusBean status = new StatusBean();
		Message message = null;
		String randomOtp = otp;
		if(otp==null)
		{
			randomOtp = Integer.toString(generateRandomNumer());
		}
		String messageToSend = generateMessage(randomOtp,customMessage);
		try {

			message = Message.creator(new PhoneNumber(appendNumberAndCountryCode(mobileNumber, countryCode)), // to
					new PhoneNumber(MOBILE_NUMBER), // from
					messageToSend).create();
		} catch (Exception e) {
			status.setError(true);
			status.setErrorMessage(e.toString());
			return status;
		}
		try {
			CsvOperation.setEntries(mobileNumber, countryCode, firstname, lastName, randomOtp, message.getSid());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status.setError(true);
			status.setErrorMessage(e.toString());
			return status;
		}
		status.setId(message.getSid());
		status.setError(false);
		return status;

	}
}