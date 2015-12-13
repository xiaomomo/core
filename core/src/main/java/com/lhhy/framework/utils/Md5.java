package com.lhhy.framework.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Md5 {
	
	private static final Logger log = LoggerFactory.getLogger(Md5.class);
	private Md5(){}
	/**
	 * 利用MD5进行加密,采用JDK自带的MD5算法 返回一个经过MD5加密后的32位的信息摘要
	 * 
	 * @return String
	 */
	public static String encoderByMd5(String str) {
		try {
			MessageDigest messageDigest = null;
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				} else {
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
				}
			}
			return md5StrBuff.toString();
		} catch (Exception e) {
			log.error("MD5处理出错：" + e.getMessage(), e);
			throw new RuntimeException("参数有误，无法处理");
		}
	}
	
	public static void main(String[] args){
		System.out.println(encoderByMd5("123abc098"));
	}
}
