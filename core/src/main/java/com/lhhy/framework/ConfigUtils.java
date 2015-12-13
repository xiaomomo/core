package com.lhhy.framework;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhhy.framework.utils.Utils;

public final class ConfigUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfigUtils.class);
	
	private ConfigUtils(){}
	
	private static Map<String, Object> cachedParams = Utils.newHashMap();
	
	public static void put(String key, Object value){
		if(cachedParams.containsKey(key)){
			LOG.warn("Key={} already exists, old value will be overite.", key);
		}
		
		LOG.debug("put key={}, value={} in localMap", key, value);
		
		cachedParams.put(key, value);
	}
	
	public static String getString(String key){
		return getString(key, "");
	}
	
	public static String getString(String key, String defaultValue){
		Object obj = cachedParams.get(key);
		if(obj == null)
			return defaultValue;
		
		return obj.toString();
	}
	
	public static int getInt(String key){
		return getInt(key, -1);
	}

	public static int getInt(String key, int defaultValue){
		Object obj = cachedParams.get(key);
		if(obj == null)
			return defaultValue;
		
		if(!Utils.isNumber(obj.toString()))
			return defaultValue;
			
		return Integer.parseInt(obj.toString());
	}
	
	//从配置文件获得配置的默认首页
	public static int getPageSize(){
		int pageSize = getInt(Constant.DEFAULT_PAGESIZE_KEY);
		if(pageSize <= 0)
			pageSize = Constant.DEFAULT_PAGESIZE;
		
		return pageSize;
	}
}
