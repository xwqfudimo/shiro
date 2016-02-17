package com.xwq.model;

import java.io.UnsupportedEncodingException;

import com.xwq.util.JsonUtil;

public class Result {
	private boolean success;
	private String message;
	
	public Result() {
	}
	
	public Result(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String success() throws UnsupportedEncodingException {
		return success("");
	}
	
	public static String success(String msg) throws UnsupportedEncodingException {
		Result result = new Result(true, msg);
		return JsonUtil.toJson(result);
	}
	
	public static String failure() throws UnsupportedEncodingException {
		return failure("");
	}
	
	public static String failure(String msg) throws UnsupportedEncodingException {
		Result result = new Result(false, msg);
		return  JsonUtil.toJson(result);
	}
}
