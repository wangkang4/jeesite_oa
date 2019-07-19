package com.thinkgem.jeesite.modules.risk.service;

import com.thinkgem.jeesite.modules.risk.dao.PageDao;
import com.thinkgem.jeesite.modules.risk.entity.PageBean;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {
	@Autowired
	private PageDao pageDao;
	
	
	public PageBean<RiskInfo> findAllRiskInfo(PageBean<RiskInfo> pageBean){
		
	System.out.println("pageNum:"+pageBean.getPageNum());
		Integer totalRecord=pageDao.findAllRiskInfo(pageBean);
		System.out.println("totalRecord:"+totalRecord);
		System.out.println("pageSize:"+pageBean.getPageSize());
		pageBean.setTotalRecord(totalRecord);
		PageBean<RiskInfo> pb=new PageBean<RiskInfo> (pageBean.getPageNum(), pageBean.getPageSize(), totalRecord);
		Integer startIndex=pb.getStartIndex();
		System.out.println("startIndex:"+startIndex);
		pageBean.setStartIndex(startIndex);
		pb.setList(pageDao.findRiskInfo(pageBean));
		return pb ;
	}
	
}
