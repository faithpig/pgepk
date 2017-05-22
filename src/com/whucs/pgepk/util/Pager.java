package com.whucs.pgepk.util;
//用来分页的类
public class Pager {
private int currentPage;
private int offset;
private int maxPages;
private int maxRecords;
private int pageSize;
public int getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}
public int getOffset() {
	return offset;
}
public void setOffset(int offset) {
	this.offset = offset;
}
public int getMaxPages() {
	return maxPages;
}
public void setMaxPages(int maxPages) {
	this.maxPages = maxPages;
}
public int getMaxRecords() {
	return maxRecords;
}
public void setMaxRecords(int maxRecords) {
	this.maxRecords = maxRecords;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}

public Pager(){	
	currentPage=1;
	offset=0;
	maxPages=0;
	maxRecords=0;
	pageSize=0;
}
public void newPager(int _currentPage,String _method,int _maxRecords,int _pageSize){
	this.pageSize=_pageSize;
	this.maxRecords=_maxRecords;
	if(pageSize>maxRecords){
		pageSize=maxRecords;
	}
	if(maxRecords==0){
		this.maxPages=0;
	}
	else{
		if(maxRecords%pageSize==0){
			this.maxPages=maxRecords/pageSize;
		}
		else{
			this.maxPages=(maxRecords/pageSize)+1;
		}
	}
	if(_currentPage==0||_currentPage<0||_currentPage>maxPages){
		this.currentPage=1;
		this.offset=0;
		return;
	}
	if(_method==null){
		_method="";
	}
	if(_method.equals("first")){
		this.currentPage=1;
		this.offset=0;
		return;
	}
	if(_method.equals("next")){
		if(_currentPage!=maxPages){
		this.currentPage=_currentPage+1;
		this.offset=(currentPage-1)*pageSize;
		}
		else{
			this.currentPage=_currentPage;
			this.offset=(currentPage-1)*pageSize;
		}
		return;
	}
	if(_method.equals("back")){
		if(_currentPage==0||_currentPage<0||_currentPage==1){
		this.currentPage=1;
		this.offset=(currentPage-1)*pageSize;
		}
		else{
			this.currentPage=_currentPage-1;
			this.offset=(currentPage-1)*pageSize;
		}
		return;
	}
	if(_method.equals("last")){
		this.currentPage=maxPages;
		this.offset=(currentPage-1)*pageSize;
		return;
	}
	if(_method.equals("jump")){
		this.currentPage=_currentPage;
		this.offset=(currentPage-1)*pageSize;
		return;
	}
	this.currentPage=1;
	this.offset=0;
	return;
}
}
