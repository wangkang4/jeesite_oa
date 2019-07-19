package com.thinkgem.jeesite.modules.risk.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.risk.entity.PageBean;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;

import java.util.List;

@MyBatisDao
public interface PageDao {
	/**
	 * 查询出所有的风险信息
	 * @return
	 */
	Integer findAllRiskInfo(PageBean<RiskInfo> pageBean);
	
	/**
	 * 获取每页的数据
	 * @param satrtIndex
	 * @param pageSize
	 * @return
	 */
	List<RiskInfo> findRiskInfo(PageBean<RiskInfo> pageBean);
}
