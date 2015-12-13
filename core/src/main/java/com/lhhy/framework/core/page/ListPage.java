package com.lhhy.framework.core.page;

import java.util.List;

public interface ListPage<T> extends Iterable<T>{
	String PAGE_SIZE = "pageSize";
	String PAGE_NO = "pageNo";
	String START_ROW = "startRow";
	
	/**
	 * 获取分页的大小
	 * @param pageSize 可通过系统参数配置默认的"分页大小".
	 */
	int getPageSize();
	/**
	 * 设置/获取当前的分前页
	 * @param pageNo 默认是1
	 */
	int getPageNo();
	/**
	 * 获得总页数
	 * @return Integer
	 * 	<p>If no records, then return 0.</p>
	 */
	int getPages();
	/**
	 * 获得总记录数
	 * @return
	 * 	<p>If no records, then return 0.</p>
	 */
	long getRows();
	/**
	 * 获得记录集(分页的记录集合)
	 * @return List
	 * 	<p>If no records, then return a empty list</p>
	 */
	List<T> getDataList();
}
