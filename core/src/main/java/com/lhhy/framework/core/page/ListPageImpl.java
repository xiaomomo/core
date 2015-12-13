package com.lhhy.framework.core.page;

import java.util.List;

import com.lhhy.framework.utils.Utils;

public class ListPageImpl<T> extends AbstractListPage<T>{

	private List<T> dataList;
	private long rows = 0;
	
	
	@Override
	public long getRows() {
		return this.rows;
	}

	@Override
	public List<T> getDataList() {
		if(dataList == null)
			return Utils.EMPTY_LIST();
		return dataList;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
