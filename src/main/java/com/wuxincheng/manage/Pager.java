package com.wuxincheng.manage;

import java.util.List;

/**
 * 分页封装
 * 
 * @author wuxincheng
 * 
 */
public class Pager {

	private List<Object> dataList;

	/** 开始页码 */
	private int start;
	/** 结束页码 */
	private int end;
	/** 页数 */
	private int pagerSize;
	/** 当前页面 */
	private int currentPage;
	/** 总有效记录数 */
	private int totalCount;

	public Pager() {
	}
	
	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPagerSize() {
		return pagerSize;
	}

	public void setPagerSize(int pagerSize) {
		this.pagerSize = pagerSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
