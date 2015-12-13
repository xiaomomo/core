package com.lhhy.framework.utils;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


public abstract class Utils {

	private static final DecimalFormat dfFen = new DecimalFormat("0");
	
	public static final <T> List<T> EMPTY_LIST(){
		return new ArrayList<T>(0);
	}
	
	public static final <K,V> Map<K, V> EMPTY_MAP(){
		return new HashMap<K,V>(0);
	}
	
	public static <T> List<T> newArrayList(){
		return new ArrayList<T>();
	}
	
	public static <K, V> Map<K, V> newHashMap(){
		return new HashMap<K, V>();
	}
	
	public static <K, V> Map<K, V> newConcurrentHashMap(){
		return new ConcurrentHashMap<K, V>();
	}
	
	public static <K, V> Map<K, V> newTreeMap(){
		return new TreeMap<K, V>();
	}
	
	public static final String null2str(String str){
		return (str == null ? "" : str);
	}
	
	public static final String safeStr(String str){
		return str;
	}
	
	public static String formatQueryName(Class<?> clazz, String suffix){
		String str = clazz.getName();
		str = str.substring(0, str.lastIndexOf("."));
		return str.concat(suffix);
	}
	
	public static String getNamespace(Class<?> clazz){
		String str = clazz.getName();
		str = str.substring(0, str.lastIndexOf("."));
		return str;
	}
	
	public static Date now(){
		return new Date();
	}
	
	public static boolean isNumber(String str){
		if(StringUtils.isBlank(str))
			return false;
		return str.matches("\\d*");
	}
	/**
	 *		判断一个字符串是否为空。
	 *		  这里的空是指：null 或者 "" 或者 " "（只有空格）都算是空
	 *      如果不排除空格的话，建议使用org.apache.commons.lang.StringUtils.isEmpty()的方法，
	 *        这个方法有个缺点，就是不能排除空格
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null)
			return true;
		if("".equals(str.trim()))
			return true;
		return false;
	}
	
	public static boolean isAllEmpty(String[] args){
		if(args == null)
			return true;
		if(args.length == 0)
			return true;
		for(int i = 0; i < args.length; i ++){
			if(!isEmpty(args[i]))
				return false;
		}
		return true;
	}
	
	public static boolean equal(String str1, String str2){
		if(isEmpty(str1) && isEmpty(str2))
			return true;
		if(isEmpty(str1) || isEmpty(str2))
			return false;
		return str1.equals(str2);
	}
	
	public static boolean equalIgnoreCase(String str1, String str2){
		if(isEmpty(str1) && isEmpty(str2))
			return true;
		if(isEmpty(str1) || isEmpty(str2))
			return false;
		return str1.equalsIgnoreCase(str2);
	}
	
	public static boolean isIn(String[] args, String key){
		if(isEmpty(key))
			return false;
		if(args == null)
			return false;
		if(args.length == 0)
			return false;
		for(String str : args){
			if(equal(key, str)){
				return true;
			}
		}
		return false;
	}
	
	public static String getSafeStr(String str){
		return StringUtils.trimToEmpty(str);
	}
	
	public static String getSafeSQL(String str){
		if(isEmpty(str))
			return "";
		str = replace(str, "'", "‘");
		str = replace(str, ";", "；");
		//str = replace(str, "%", "%");
		
		return str != null ? str.trim() : "";
	}
	
	public static String getSubStr(String str, int length){
		if(isEmpty(str))
			return str;
		if(str.length() > length)
			return str.substring(0, length - 1).concat("...");
		else
			return str;
	}
	
	public static String buildStr(Object... args){
		if(args != null && args.length > 0){
			StringBuilder str = new StringBuilder();
			for(int i = 0; i < args.length; i ++)
				str.append(args[i]);
			return str.toString();
		}
		return "";
	}
	
	public static int toInt(String str) throws Exception{
		int ret = 0;
		if(!isEmpty(str))
			ret = Integer.parseInt(str);
		return ret;
	}
	
	public static Long toLong(String str) throws Exception{
		long ret = 0;
		if(!isEmpty(str))
			ret = Long.parseLong(str);
		return ret;
	}
	
	public static boolean isChineseCharacter(String str){
		if(isEmpty(str))
			return false;
		for(int i = 0; i < str.length(); i ++){
			if(str.substring(i, i + 1).matches("[\u4e00-\u9fa5]+"))
				return true;
		}
		return false;
	}
	
	public static String toIntSql(String[] array){
		if(array != null && array.length > 0){
			StringBuilder str = new StringBuilder();
			for(String s : array){
				str.append(",").append(s);
			}
			if(str.length() > 0)
				str.deleteCharAt(0);
			return str.toString();
		}
		return null;
	}
	
	public static Integer random6() {
		return (int)((Math.random()*9+1)*100000);
	}
	
	public static String toSql(String[] array){
		if(array != null && array.length > 0){
			StringBuilder str = new StringBuilder();
			for(String s : array){
				str.append(",'").append(s).append("'");
			}
			if(str.length() >  0)
				str.deleteCharAt(0);
			return str.toString();
		}
		return null;
	}
	
	public static String replace(String str, String oldchar, String newchar){
		if(isEmpty(str))
			return "";
		int loc = str.indexOf(oldchar);
		if(loc < 0)
			return str;
		StringBuilder builder = new StringBuilder();
		builder.append(str.substring(0, loc)).append(oldchar);
		builder.append(replace(str.substring(loc + oldchar.length()), oldchar, newchar));
		return builder.toString();
	}
	
	public static boolean isIllegalChar(String s){
		if(isEmpty(s))
			return true;
		Pattern p = Pattern.compile("^[\\w]+$");
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	public static String[] splitArray(String str, String split){
		int i = str.indexOf(split);
		return new String[]{str.substring(0, i), str.substring(i + 1, str.length() - 1)};
	}
	
	public static String listToString(java.util.List<String> list, String split){
		if(list == null || list.size() == 0)
			return "";
		StringBuilder str = new StringBuilder();
		for(Object s : list){
			str.append(s).append(split);
		}
		if(str != null)
			str.deleteCharAt(str.length() - 1);
		return str.toString();
	}
	
	public static String format(String str, Object...strings){
		if(isEmpty(str))
			return "";
		return MessageFormat.format(str, strings);
	}
	
	public static String unescape(String src) {
		if(isEmpty(src))
			return null;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	/**
	 * 返回以0作前缀的指定长度的序列号
	 */
	public static String fixLength(String value, int fixLen) {
		return fixLength(value, fixLen, '0');
	}

	/**
	 * 返回指定名称和长度以及指定前缀的序列号，通常使用0作前缀
	 */
	public static String fixLength(String value, int fixLen, char fixChar) {
		return repeat(fixChar, fixLen - value.length()).concat(value);
	}
	
	public static String repeat(char ch, int repeat) {
		if (repeat < 1)
			return "";
		char[] buf = new char[repeat];
		for (int i = 0; i < repeat; i++) {
			buf[i] = ch;
		}
		return new String(buf);
	}
	
	public static Map<String, String> orderMap(Map<String, String[]> reqMap){
		if(reqMap == null || reqMap.isEmpty())
			return Utils.newTreeMap();
		//解析请求的map
		Map<String, String> resultMap = Utils.newTreeMap();
		for(String key : reqMap.keySet()){
			resultMap.put(key, reqMap.get(key) == null ? "" : reqMap.get(key)[0]);
		}
		
		return resultMap;
	}
	
	public static boolean isMobile(String str){  
		 if(isEmpty(str))  
			 return false;  
		 return str.matches("^(13|14|15|18)\\d{9}$");  
	}
	 
	public static String replacePhone(String phone){
		if(StringUtils.isBlank(phone))
			return "";
		
		StringBuilder sb = new StringBuilder();
		sb.append(phone.substring(0, 3));
		sb.append("*****");
		sb.append(phone.substring(8,11));
		return sb.toString();
	}
	
	public static String double2fen(Double va){
		if(va == null)
			return "0";
		
		return dfFen.format(va);
	}
	
	public static void main(String[] args){
		System.out.println(replacePhone("13503090920"));
	}
}
