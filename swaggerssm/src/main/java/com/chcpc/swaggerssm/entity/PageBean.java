package com.chcpc.swaggerssm.entity;

import java.util.List;
/**
 * 设定PageBean类对象属性，
 * 	其中pageSize为固定数值、
 * 	totalRecored是查询结果
 * 	totalPage是计算结果，由pageSize和totalRecored确定，get方法。
 * 	pageBean和pageEnd都是计算结果，由当前页和总页数确定。
 * 	url是路径参数
 * @author Administrator
 *
 */
public class PageBean<T> {
	private List<T> beanList;	//	数据库查询结果
	private int pageIndex;	//	当前页，页索引，客户请求参数
	private int pageSize;	//	页大小，每页最多显示多少条数据。由外部设定固定值
	private int totalRecored;	//	结果总条数
	private int totalPage;	//	结果总页数
	private int pageBegin;	//	起始页
	private int pageEnd;	//	末页
	private String url;	//	页码url,封装请求的路径参数，赋值
	//	1. 总页数计算
	public int getTotalPage() {
		//	结果总页数=总条数/页大小，不能整除则+1
		return totalRecored%pageSize==0?totalRecored/pageSize:totalRecored/pageSize+1;
	}
	//	2.起始页和末页计算
	public void setPageBeginAndEnd(){
		//	前后各显示4页，考虑各种情况。
		pageEnd = getTotalPage();		
		if(pageEnd<9){
			//	2.1 如果总页数少于9页，则前后页面数量固定
			//	起始页为1，末页为总页数
			pageBegin = 1;
		}else{
			//	2.2  如果总页数多于9页			
			if(pageIndex-5<1){
				//	（1）当前页码小于5时
				pageBegin = 1;
				pageEnd = 9;
			}else if(pageEnd-pageIndex<5){
				//	（2）当前页+5>末页时
				pageBegin = pageEnd-8;
			}else{
				//	（3）  否则起始页为当前页码-4，末页为当前页码+4
				pageBegin = pageIndex-4;
				pageEnd = pageIndex+4;				
			}
		}
	}	
	
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(List<T> beanList, int pageIndex, int pageSize, int totalRecored, int totalPage,
			int pageBegin, int pageEnd, String url) {
		super();
		this.beanList = beanList;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalRecored = totalRecored;
		this.totalPage = totalPage;
		this.pageBegin = pageBegin;
		this.pageEnd = pageEnd;
		this.url = url;
	}
	@Override
	public String toString() {
		return "PageBean [beanList=" + beanList + ", pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", totalRecored=" + totalRecored + ", totalPage=" + totalPage + ", pageBegin=" + pageBegin
				+ ", pageEnd=" + pageEnd + ", url=" + url + "]";
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecored() {
		return totalRecored;
	}
	public void setTotalRecored(int totalRecored) {
		this.totalRecored = totalRecored;
	}
//	public int getTotalPage() {
//		return totalPage;
//	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageBegin() {
		return pageBegin;
	}
	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
