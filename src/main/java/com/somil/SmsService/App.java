package com.somil.SmsService;
import static spark.Spark.port;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

import java.util.Random;

import javax.servlet.MultipartConfigElement;

import org.json.JSONObject;

import spark.Request;
import spark.Response;

public class App {
	public static void main(String[] args) {
		 port(8088);
//		 sent messages get Call
		get("/sentMessages", (request, response) -> {
			
			response.type("application/json");
			return RequestRouter.fetchListOfSentMessage(request, response);
		});
		
		// send post call
		post("/send", (request, response) -> {
			request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(""));
			response.type("application/json");
			return RequestRouter.sendMessage(request, response);
		
		});
		// handle preflight in case of auth
		options("/*", (request, response) -> {
			return handlePreflight(request, response);
		});
		// allow all origin
		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			final String method = request.requestMethod();
			if (!method.equals("OPTIONS")) {
				response.header("Access-Control-Max-Age", "86400");
				response.type("application/json");
			}
		});
	}
	
	private static Object handlePreflight(Request request, Response response) {
		final String requestHeaders = request.headers("Access-Control-Request-Headers");
		response.header("Access-Control-Allow-Headers", requestHeaders);
		String requestMethod = request.headers("Access-Control-Request-Method");
		response.header("Access-Control-Allow-Methods", requestMethod);
		return "OK";
	}
}

