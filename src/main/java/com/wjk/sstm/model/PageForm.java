package com.wjk.sstm.model;

public class PageForm {

	private int pageStart; //当前页数
	private int rows; //每页多少条
	private Long total; //总数
	public PageForm() {
		
	}
	public PageForm(int pageStart,int rows) {
		this.pageStart=pageStart;
		this.rows=rows;
	}
	public int getPageStart() {
		return (pageStart-1)*rows;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
}
