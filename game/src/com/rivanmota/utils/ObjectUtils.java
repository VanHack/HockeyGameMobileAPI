package com.rivanmota.utils;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {

	/**
	 * Checa se um objeto n�o e nulo nem vazio
	 * @param obj
	 * @return <b>true</b> se o object n�o for nulo nem vazio e <b>false</b> caso ele seja null ou vazio.
	 */
	public static boolean isNotNullAndNotEmpty(Object obj) {
		if (obj == null)
			return false;
		
		if (obj instanceof String) {
			String s = (String) obj;
			if (s.trim().isEmpty())
				return false;
		}
		
		return true;
	}
	
	public static boolean isNullOrEmpty(Object obj) {
		return !isNotNullAndNotEmpty(obj);
	}
	
	public static boolean isCollectionNullOrEmpty(Collection<?> collection) {
		if (collection == null || collection.size() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isMapNullOrEmpty(Map<?, ?> map) {
		if (map == null || map.size() == 0) {
			return true;
		}
		return false;
	}
	
	public static String returnEmptyIfIsNull(String string) {
		if (isNullOrEmpty(string) || string.equals("null")) 
			return "";
		return string;
	}
	
	
	public static boolean validaInteger(String number){
		boolean resp = false;
		try {
			Integer.parseInt(number);
			resp = true;
		} catch (NumberFormatException e) {
			resp = false;
		} 
		return resp;
	}

}
