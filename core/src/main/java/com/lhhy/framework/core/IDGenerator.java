package com.lhhy.framework.core;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.lhhy.framework.ConfigUtils;
import com.lhhy.framework.utils.DateUtils;
import com.lhhy.framework.utils.Utils;

public final class IDGenerator {

	//原子自增量
	private static AtomicInteger atomicInt = new AtomicInteger(1);
	private static AtomicInteger atomicOrder = new AtomicInteger(1);
	private static NumberFormat numberFormat;
	private static NumberFormat numberFormatLong;
	//时间串格式化
	private static final SimpleDateFormat sdf = new SimpleDateFormat("mmssSSS", Locale.PRC);
	
	private static final String[] chars = {"A","B","C","F","L","E","8","P","Q","W","R","Z","T","Y","I","U","O","K","J",
		"S","H","G","6","X","V","N","M","0","1","3","8","7","2","4","5"
		};
	
	private static Random random = new Random();
	
	static{
		numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMinimumIntegerDigits(3);
        numberFormat.setMaximumIntegerDigits(3);
        
        numberFormatLong = NumberFormat.getInstance();
        numberFormatLong.setGroupingUsed(false);
        numberFormatLong.setMinimumIntegerDigits(6);
        numberFormatLong.setMaximumIntegerDigits(6);
	}
	
	public static String getID12(){
		StringBuilder sb = new StringBuilder();
		sb.append(ConfigUtils.getString("Host.flag", ""));
		//获得一个随机字母
		sb.append(chars[random.nextInt(35)]);
		sb.append(getShort());
		int is = atomicInt.getAndIncrement();
		if(is > 999){
			atomicInt.set(1);
			is = 1;
		}
		sb.append(numberFormat.format(is));
		
		return sb.toString();
	}
	
	public static String getOrderCode20(){
		StringBuilder sb = new StringBuilder();
		sb.append(DateUtils.getCurrentDateString("yyyyMMddHHmm"));
		sb.append(ConfigUtils.getString("Host.flag", ""));
		//获得一个随机字母
		sb.append(chars[random.nextInt(35)]);
		int is = atomicOrder.getAndIncrement();
		if(is > 99999){
			atomicOrder.set(1);
			is = 1;
		}
		sb.append(numberFormatLong.format(is));
		return sb.toString();
	}
	
	private static String getShort(){
		return sdf.format(Utils.now());
	}
	
	public static void main(String[] args){
		System.out.println(getID12());
		System.out.println(getOrderCode20());
	}
}
