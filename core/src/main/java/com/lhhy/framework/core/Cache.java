package com.lhhy.framework.core;

public interface Cache {

	Object get(String key);
	
	CacheObject getCache(String key);
	
	void put(String key, Object value);
	
	void put(String key, Object value, int expired);
	
	Object remove(String key);
	
	CacheObject removeCache(String key);
	
	void clear();
}
