package com.lhhy.framework.core.page;

import java.util.Iterator;

import com.lhhy.framework.ConfigUtils;

public abstract class AbstractListPage<T> implements ListPage<T>{

	private int pageSize = 0;
	private int pageNo = 1;
	
	public int getPages(){
		int count = 0;
		long totalCount = getRows();
		if(totalCount == 0)
			count = 0;
		if(totalCount < getPageSize())
			count = 1;
		if(getPageSize() != 0){
			double c = (double)(totalCount / getPageSize());
			count = (int)c;
			if((totalCount % getPageSize()) > 0)
				count = count + 1;
		}
		return count;
	}
	
	public int getPageSize() {
		if(this.pageSize <= 0)
			this.pageSize = ConfigUtils.getPageSize();
		
		return this.pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		int pagesCount = getPages();
		if(pageNo <= 0)
			this.pageNo = 1;
		else if(pageNo > pagesCount)
			this.pageNo = pagesCount > 0 ? pagesCount : 0;
		else
			this.pageNo = pageNo;
	}
	
	
	public Iterator<T> iterator() {
		return getDataList().iterator();
	}
}
