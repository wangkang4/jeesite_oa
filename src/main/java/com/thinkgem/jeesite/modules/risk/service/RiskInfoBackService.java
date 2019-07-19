package com.thinkgem.jeesite.modules.risk.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.risk.dao.RiskInfoBackDao;
import com.thinkgem.jeesite.modules.risk.entity.RiskBack;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RiskInfoBackService extends CrudService<RiskInfoBackDao, RiskInfo> {
	@Autowired
	private RiskInfoBackDao riskInfoBackDao;
	
	/**
	 * 通过id找到风险反馈信息
	 * @param id
	 * @return
	 */
	public RiskBack findRiskBackById(String id){
		RiskBack back=riskInfoBackDao.findRiskBackByRiskInfoId(id);
		return back;
	}
	/**
	 * 修改风险反馈信息
	 * @return
	 */
	@Transactional(readOnly=false)
	public int updateRiskBack(RiskBack riskBack){
		int id=riskInfoBackDao.updateRiskBack(riskBack);
		return id;
	}
	/**
	 * 添加风险信息
	 * @return
	 */
	@Transactional(readOnly=false)
	public int addRiskBack(RiskBack riskBack){
		int id=riskInfoBackDao.addRiskBack(riskBack);
		return id;
	}


}
