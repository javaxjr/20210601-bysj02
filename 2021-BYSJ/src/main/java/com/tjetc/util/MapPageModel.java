package com.tjetc.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapPageModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8443405549405606758L;
	private long count = 0;
	private long page = 0;
	private long pageSize = 0;
	private List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public List<Map<String,Object>> getData() {
		return data;
	}
	public void setData(List<Map<String,Object>> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "MapPageModel{" +
				"count=" + count +
				", page=" + page +
				", pageSize=" + pageSize +
				", data=" + data +
				'}';
	}
}
