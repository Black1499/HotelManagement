package com.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
public static String toJson(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’");
		mapper.setDateFormat(sdf);
		
		String result = null;
		
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * */
	public static <T> T toObject(String json,Class<T> valueType) {
		
		ObjectMapper mapper=new ObjectMapper();
		T result=null;
		try {
			result=mapper.readValue(json,valueType);

		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
