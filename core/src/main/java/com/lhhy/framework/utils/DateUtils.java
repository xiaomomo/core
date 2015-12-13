package com.lhhy.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtils {
		
	private DateUtils(){}
	
	private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * 生成短序列号
	 * <p>
	 * 根据当前时间生成，用于少量数据记录时。(可能出现重复，一般用于记录较少且变动不频繁的静态表的记录生成)
	 * 
	 * @return 序列号
	 */	
	public static String getShortSerial() {
		return getCurrentDateString("mmssSSS");
	}
	
	/**
	 * 按格式取当前时间字符串
	 * 
	 * @param formatString 格式字符串
	 * @return
	 */
	public static String getCurrentDateString(String formatString) {
		return getDateString(Utils.now(), formatString);
	}
	/**
	 * 取时间字符串
	 * 
	 * @param date 时间
	 * @param formatString 转换格式
	 * @return 时间字符串
	 */
	public static String getDateString(Date date, String formatString) {
		return getDateString(date, formatString, Locale.PRC);
	}
	
	/**
	 * 取时间字符串
	 * 
	 * @param date 时间
	 * @param formatString 转换格式
	 * @param locale 地区
	 * @return 时间字符串
	 */
	public static String getDateString(Date date, String formatString, Locale locale) {
		if (date == null)
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString, locale);

		return dateFormat.format(date);
	}
	
	/**
	 * 通过字符串获取日期
	 * @author Tommy
	 * @param dateString 日期字符串
	 * @param reg 日期格式（如:"yyyy-MM-dd hh:mm"、"yyyy/MM/dd hh:mm"）
	 * @return 转换好的Date
	 */
	public static Date getDateByString(String dateString,String reg) {
		Date date = null;
		
		try {
			SimpleDateFormat df = new SimpleDateFormat(reg);
			date = df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			LOG.error("日期转换错误,日期格式不正确");
		}
		
		return date;
	}
	
	public static Date addDayByMin(int minute){
		return addDay(Utils.now(), Calendar.MINUTE, minute);
	}
	
	public static Date addDayByHour(int hour){
		return addDay(Utils.now(), Calendar.HOUR, hour);
	}
	
	public static Date addDay(int day){
		return addDay(Utils.now(), Calendar.DAY_OF_MONTH, day);
	}
	public static Date addYear(Date cur, int n){
		return addDay(cur, Calendar.YEAR, n);
	}
	/**
	 * 添加second(秒）后的日期
	 * 
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addDay(Date date, int type, int interval){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(type, interval);
		return c.getTime();
	}
	
	public static int diff2days(Date date, Date date2){
		if(date2.compareTo(date) <= 0)
			return 0;
		long interval = date2.getTime() - date.getTime();
		return (int) (interval / (1000*60));
	}
	
	public static void main(String[] args){
		System.out.println(addDay(new Date(), Calendar.DAY_OF_MONTH, 1));
	}
}
