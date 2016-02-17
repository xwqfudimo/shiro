package com.xwq.util;

import java.util.HashMap;
import java.util.Map;

public class ParamUtil {
	
	private static Map<String,Object> paramMap;
	
	static {
		paramMap = new HashMap<String,Object>();
	}
	
	public static Map<String,Object> put(String key, String value) {
		paramMap.put(key, value);
		return paramMap;
	}
	
}
