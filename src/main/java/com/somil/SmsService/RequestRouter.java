/**
 * 
 */
package com.somil.SmsService;

import spark.Request;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.StatusBean;
import spark.Response;

/**
 * @author Somil Khandelwal
 *
 *	Every Request going through this
 */
public class RequestRouter {
	
	public static JSONObject sendMessage(Request request, Response response)
	{
		StatusBean status = new StatusBean();
		final String mobileNumber = request.queryParams("mobileNumber");
		final String countryCode = request.queryParams("countryCode");
		final String firstName= request.queryParams("firstName");
		final String lastName= request.queryParams("lastName");
		final String otp= request.queryParams("otp");
		final String message= request.queryParams("message");


		if(mobileNumber==null || countryCode==null)
		{
			response.status(400);
			status.setError(true);
			status.setErrorMessage("Mobile Number or Country code can't be null");
		}
		else {
			status = SmsSender.sendMessage(mobileNumber, countryCode, firstName,lastName,otp,message );
			if(status.getError()==true)
			{
				response.status(400);
			}
			else
			{
				response.status(200);
			}
		}
		JSONObject statusObject = new JSONObject(status);
		return statusObject;
	}
	
	public static JSONObject fetchListOfSentMessage (Request request, Response response)
	{
		JSONObject obj = new JSONObject();
		try {
			CsvOperation.getEntries();
			JSONArray arr = new JSONArray(CsvOperation.getEntries());
			obj.put("error", false);
			obj.put("data", arr);
		} catch (IOException e) {
			obj.put("errorMessage", e.toString());
			obj.put("error", true);
			response.status(403);
			return obj;
		}
		response.status(200);
		return obj;
	}
}
