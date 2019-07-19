package com.thinkgem.jeesite.modules.risk.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.List;

public class PageBean<T> extends DataEntity<PageBean<T>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RiskInfo riskInfo;
	
	private Integer pageNum;//当前页数
	private Integer pageSize;//一页显示多少条
	private Integer totalRecord;//总共查询的条数
	
	
	private Integer totalPage;//一共有多少页，需要计算得出
	private Integer startIndex;//开始索引，也就是从数据库中的第几条数据开始查
	//有了pageSize和startIndex我们就知道了limit的两个关键字
	
	//讲每页要显示的数据存放到list集合中
	List<T> list;
	
	//分页显示的数据，就是前段页面显示的1,2,3,的页数
	private Integer start;
	private Integer end;
	
	public PageBean() {
		super();
	}

	public PageBean(Integer pageNum,Integer pageSize,Integer totalRecord){
		this.pageNum=pageNum;
		this.pageSize=pageSize;
		this.totalRecord=totalRecord;
		//总页数
		if(totalRecord%pageSize==0){
			//正好每页显示完整的条目数
			this.totalPage=totalRecord/pageSize;
		}else{
			this.totalPage=totalRecord/pageSize+1;
		}
		//开始索引
		this.startIndex=(pageNum-1)*pageSize;
		this.start=1;
		this.end=5;
		//显示页数的算法，显5页
		if(totalPage<=5){
			//总页数都小于5了,那么end就是总页数的值
			this.end=totalPage;
		}else{
			//总页数大于5,需要根据当前页数来计算出起始也是和结束页数
			this.start=pageNum-2;
			this.end=pageNum+2;
			if(start<0){
				//如果当前页是第1页或者第二页，无视此规则
				this.start=1;
				this.end=5;
			}
			if(end>this.totalPage){
				this.end=totalPage;
				this.start=end-5;
			}
			
		}
	}

	public RiskInfo getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(RiskInfo riskInfo) {
		this.riskInfo = riskInfo;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "PageBean [riskInfo=" + riskInfo + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalRecord="
				+ totalRecord + ", totalPage=" + totalPage + ", startIndex=" + startIndex + ", list=" + list
				+ ", start=" + start + ", end=" + end + "]";
	}
	
	
	
	
}
