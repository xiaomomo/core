package com.lhhy.framework.core;

import java.util.List;
import java.util.Map;

import com.lhhy.framework.core.page.ListPage;

public interface DBProcess {

	int save(Class<?> clazz, String suffix, Object value);
	
	int save(String suffix, Object dest);
	
	int update(Class<?> clazz, String suffix, Object value);
	
	/**
	 * 删除对象
	 * @author breeze
	 * @param suffix 
	 * @param dest
	 * @return 影响数据库条数
	 */
	int delete(String suffix, Object dest);
	
	int delete(Class<?> clazz, String suffix, Object dest);
	
	int update(String suffix, Object dest);
	
	<T> T get(String suffix, Object params);

	<T> T get(Class<T> clazz, String suffix, Object params);
	
	<T> List<T> list(Class<T> clazz, String suffix, Object params);
	
	<T> List<T> list(String suffix, Object params);
	
	<K,T> Map<K,T> query(Class<?> clazz, String suffix, Object params);
	
	<K,T> Map<K,T> query(String suffix, Object params);
	
	<T> ListPage<T> listPage(Class<T> clazz, String suffix, Object params);
	
	<T> ListPage<T> listPage(String namespace, String suffix, Object params);
}
