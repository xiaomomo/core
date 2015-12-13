package com.lhhy.framework.utils;

import org.apache.commons.lang.StringUtils;

import com.lhhy.framework.ex.EmptyParameterException;
import com.lhhy.framework.ex.OutOfLengthException;

public final class Assert {

	private Assert(){}
	
	public static void isEmpty(String str, String message){
		if(StringUtils.isBlank(str)){
			throw new EmptyParameterException(message);
		}
	}
	
	public static void isBlank(String str, String message){
		if(StringUtils.isBlank(str)){
			throw new EmptyParameterException(message);
		}
	}
	
	public static void isNull(Object obj, String message){
		if(obj == null){
			throw new EmptyParameterException(message);
		}
	}
	
	public static void isLength(String str, int maxLength, String message){
		if(StringUtils.isBlank(str)){
			throw new EmptyParameterException();
		}
		
		if(str.length() > maxLength){
			throw new OutOfLengthException(message);
		}
	}
	
	public static void gtIntZero(Integer obj, String message){
		if(obj == null){
			throw new EmptyParameterException(message);
		}
		
		if(obj <= 0){
			throw new EmptyParameterException(message);
		}
	}
	
	public static void gtDoubleZero(Double obj, String message){
		if(obj == null){
			throw new EmptyParameterException(message);
		}
		
		if(obj <= 0d){
			throw new EmptyParameterException(message);
		}
	}
}
