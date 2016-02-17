package com.xwq.util;

public class TextUtil {
	
	public static boolean isEmpty(String text) {
		if(text == null || "".equals(text.trim())) return true;
		return false;
	}
}
