/*
 * SmartSecurityCodeUtils.java
 *
 * Copyright(c) 2011-2013 THINWEB INC. All right reserved.
 * GREENFRAMEWORK PROPRIETARY/CONFIDENITAL. Use is subject to license terms.
 * All principal to the development of Thinweb Group.
 */
package com.lhhy.framework.utils;

import org.apache.commons.lang.StringUtils;

public final class SecurityCodeUtils {

	private static final char[] _srcArray = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
		'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
		'w', 'u', 'v', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 
		'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 
		'O', 'P', 'Q', 'R', 'S', 'T', 'W', 'U', 'V', 'X', 
		'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', 
		'8', '9'
	};
	
	private static final char[] _doArray ={
		'~', '!', '@', ',', '.', '/', ';', ':', '}', '{', 
		'[', ']', '-', '_', '+', '=', '*', '&', '^', '%', '$'};
	
	private static final String[] _endoArray = {
		"-x01", "-y01", "-e11", "-w99", "-y61", "-h00", "-b10", "-c76", "-q33", "-g76", "-v29", "-a33", "-abc",
		"-iuy", "-ZYA", "-EAE", "-GDA", "-8YA", "-a8Y", "-BCe", "-ADB"
	};
	
	private static final String[] _tmpArray = {
		"1Xc", "A9r", "0Za", "H6b", "V5e", "5Ef", "8Eg", "A11", "R0q", "1Uw", 
		"C9j", "7Zb", "M02", "0E3", "9Ow", "Q7e", "4Cr", "Kg6", "3D4", "F7l", 
		"5Sk", "Y8i", "1H8", "K1t", "D1n", "7Bf", "G03", "0Hp", "I0s", "2Jc", 
		"L3r", "N1g", "6Ol", "P19", "S2y", "T06", "3X4", "5Wd", "C0y", "0U5",
		"A7s", "A0w", "0Q3", "W1p", "V4x", "1Vz", "9H6", "V0m", "6Js", "B0e",
		"Y9o", "Q09", "0R2", "P93", "G3n", "8Us", "O1n", "8Sb", "N4d", "B93", 
		"X2t", "7Ds"
	};

	private static final String[] MOBILE_CHAR = {"I","K","A","E","P","C","S","M","Q","Z"};
	
	public static String encode2(String str){
		return encode(encode(str));
	}

	public static String decode2(String str){
		return decode(decode(str));
	}
	public static String encode(String str){
		StringBuilder builder = new StringBuilder();
		if(StringUtils.isNotBlank(str)){
			for(int i = 0; i < str.length(); i ++){
				char c = str.charAt(i);
				int charindex = charIndex(_srcArray, c);
				if(charindex != -1){
					builder.append(_tmpArray[charindex]);
				}else{
					int doIndex = charIndex(_doArray, c);
					if(doIndex != -1)
						builder.append(_endoArray[doIndex]);
					else
						builder.append(c);
				}
			}
		}
		return builder.toString();
	}
	
	public static String decode(String str){
		StringBuilder builder = new StringBuilder();
		if(StringUtils.isNotBlank(str)){
			for(int i = 0; i < str.length(); i ++){
				char c = str.charAt(i);
				if('-' == c){
					String tmp = str.substring(i, i+4);
					int charIndex1 = stringIndex(_endoArray, tmp);
					builder.append(_doArray[charIndex1]);
					i = i + 3;
				}else{
					String tmp = str.substring(i, i+3);
					int charIndex2 = stringIndex(_tmpArray, tmp);
					builder.append(_srcArray[charIndex2]);
					i = i + 2;
				}
			}
		}
		return builder.toString();
	}

	public static int stringIndex(String[] array, String s){
		for(int i = 0; i < array.length; i ++){
			if(s.equals(array[i]))
				return i;
		}
		throw new IllegalArgumentException("Decode error : the format of the inputed string is invalid.");
	}
	
	public static int charIndex(char[] array, char c){
		for(int i = 0; i < array.length; i ++){
			if(c == array[i])
				return i;
		}
		return -1;
	}
	
	public static String encodePhone(String phone){
		if(StringUtils.isBlank(phone))
			return "";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < phone.length(); i ++){
			char c = phone.charAt(i);
			sb.append(MOBILE_CHAR[Integer.parseInt(String.valueOf(c))]);
		}
		return sb.toString();
	}
	
	public static String decodePhone(String str){
		if(StringUtils.isBlank(str))
			return "";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i ++){
			char c = str.charAt(i);
			sb.append(getMobileIndex(String.valueOf(c)));
		}
		
		return sb.toString();
	}
	
	private static int getMobileIndex(String c){
		for(int i = 0; i < MOBILE_CHAR.length; i ++){
			if(MOBILE_CHAR[i].equals(c))
				return i;
		}
		return 0;
	}
	
	public static void main(String[] args){
	}
	
}
