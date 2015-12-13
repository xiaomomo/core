package com.lhhy.framework.utils;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;

/**
 * Token 生成工具类
 * 
 * @author ken
 *
 */
public class TokenUtils {

	private static final AtomicLong T_SEED = new AtomicLong(100);
	private static final AtomicLong P_SEED = new AtomicLong(1000);
	private static final long SELF_ADD = 345;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
	
	public static String makeToken(String phone){
		try{
			//反转phone
			String resertPhone = reverse(phone);
			//转成数字
			long baseNo = 0;
			if(Utils.isNumber(resertPhone)){
				baseNo = Long.valueOf(resertPhone);
			}else{
				baseNo = resertPhone.hashCode();
			}
			
			long seed = T_SEED.getAndIncrement();
			long timestamp = System.currentTimeMillis();
			seed = seed + SELF_ADD + (timestamp % 1111) + (timestamp % 111);
			
			//加上seed, timestamp
			baseNo = baseNo + seed;
			//转成十六进制
			StringBuilder sb = new StringBuilder("T");
			sb.append(sdf.format(Utils.now()));
			sb.append(Long.toHexString(baseNo).toUpperCase());
			
			return sb.toString();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return "TTTTFFFFFFFF";
	}
	
	public static String makePassPortCode(String phone){
		try{
			//反转phone
			String resertPhone = reverse(phone);
			//转成数字
			long baseNo = 0;
			if(Utils.isNumber(resertPhone)){
				baseNo = Long.valueOf(resertPhone);
			}else{
				baseNo = resertPhone.hashCode();
			}
			long seed = P_SEED.getAndIncrement();
			long timestamp = System.currentTimeMillis();
			seed = seed + SELF_ADD + (timestamp % 3333) + (timestamp % 333);
			
			//加上seed, timestamp
			baseNo = baseNo + seed;
			//转成十六进制
			StringBuilder sb = new StringBuilder("P");
			sb.append(sdf.format(Utils.now()));
			sb.append(Long.toHexString(baseNo).toUpperCase());
			
			return sb.toString();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return "PPPPFFFFFFFF";
	}
	
	public static String reverse(String str){
		if(StringUtils.isBlank(str))
			return str;
		
		String tmp = "";
		for(int i = str.length(); i > 0; i --){
			tmp = tmp + str.substring(i - 1, i);
		}
		return tmp;
	}
}
