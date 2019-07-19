package com.thinkgem.jeesite.modules.risk.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;

import java.util.List;
@MyBatisDao
public interface RiskInfoDao extends CrudDao<RiskInfo>{
	
	/**显示所有的风险信息*/
	List<RiskInfo> selectRiskInfoList(RiskInfo riskInfo);
	
	
	/**通过责任人名字查找责任人id从User表中*/

	 String  selectIdByResponseName(String responseName);
	 
	 /**向风险信息表中插入新信息*/
	 
	int  insertNewInfo(RiskInfo riskInfo);
	
	/**通过项目名称去查找项目id从项目表中*/
	String selectIdByProjectName(String projectName);
	
	/**通过风险信息的id去删除一条风险信息*/
	int deleteRiskInfoByRiskId(String id);
	
	/**
	 * 通过风险id去查找对应的风险信息
	 * @param id
	 * @return
	 */
	RiskInfo findRiskInfoById(String id);
	
	/** 通过风险id修改风险表的信息*/
	int updateRiskInfoById(RiskInfo riskInfo);
	
	/**
	 * 获取所有存在的项目名
	 * @return
	 */
	List<String> getProjectName();
	
	
	
}
