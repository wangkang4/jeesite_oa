package com.thinkgem.jeesite.modules.risk.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.risk.entity.RiskBack;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;
@MyBatisDao
public interface RiskInfoBackDao  extends CrudDao<RiskInfo>{
		/**
		 * 通过风险信息表中的id查询风险反馈信息
		 * @param id
		 * @return
		 */
		RiskBack findRiskBackByRiskInfoId(String id);
		
		/**
		 * 修改反馈信息表
		 * @return
		 */
		int updateRiskBack(RiskBack riskBack);
		
		/**
		 * 
		 * @return
		 */
		int addRiskBack(RiskBack riskBack);
		
}
