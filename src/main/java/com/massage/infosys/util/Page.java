package com.massage.infosys.util;

public class Page {

	private int pageNo;

	public Page(int pageNo, int pageSize) {
		this.pageSize = pageSize;
		setPageNo(pageNo);
	}

	private int pageSize;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {

		this.pageNo = (pageNo - 1) * pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
