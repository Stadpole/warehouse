package com.bupt.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * description: author: LiPeng
 **/

public class MessageUtil {
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";//"yyyy-MM-dd HH:mm:ss";

	public static String sendSuccessMessage() {
		return sendMessage("true", "SUCCESS", null);
	}
	public static String sendSuccessMessage(Object data) {
		return sendMessage("true", "SUCCESS", data);
	}
	public static String sendSuccessMessage(String message) {
		return sendMessage("true", message, null);
	}
	public static String sendSuccessMessage(String message,Object data) {
		return sendMessage("true", message, data);
	}
	public static String sendFailMessage() {
		return sendMessage("false", "FAILED", null);
	}
	public static String sendFailMessage(Object data) {
		return sendMessage("false", "FAILED", data);
	}
	public static String sendFailMessage(String message) {
		return sendMessage("false", message, null);
	}
	public static String sendFailMessage(String message,Object data) {
		return sendMessage("false", message, data);
	}
	
	public static String sendMessage(String status,String message,Object data){
		Map<String, Object> result = new HashMap<>();
		result.put("status", status);
		result.put("message", message);
		result.put("data", data);
		return Object2Json(result,DATE_TIME);
	}
	
//	public static String sendMessage(String status,String message,String json){
//		Map<String, Object> result = new HashMap<>();
//		result.put("status", status);
//		result.put("message", message);
//		result.put("data", json);
//		return Object2Json(result,null);
//	}
	
	public static String Object2Json(Object obj, String dateFormat) {
		Gson gson = null;
		if (null != dateFormat) {
			gson = new GsonBuilder().setDateFormat(dateFormat).create();
		} else {
			gson = new Gson();
		}
		try {
			if (null != gson && null != obj) {
				return gson.toJson(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
