package com.thinkgem.jeesite.modules.risk.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.risk.dao.RiskInfoDao;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RiskInfoService  extends CrudService<RiskInfoDao, RiskInfo>{
	@Autowired
	private RiskInfoDao riskInfoDao;
	
	
	
	//使用分页方法
	public Page<RiskInfo> findPage(Page<RiskInfo> page,RiskInfo riskInfo){
		riskInfo.setPage(page);
		List<RiskInfo> list=dao.findList(riskInfo);
		page.setList(list);
			return page;
	}
	//不使用分页查询时使用的展示列表方法
//	public List<RiskInfo> selectList(RiskInfo riskInfo){
//		
//		List<RiskInfo> list =riskInfoDao.selectRiskInfoList(riskInfo);
//		return list;
//	}
	
	public String findId(String responseName){
		
		String id=riskInfoDao.selectIdByResponseName(responseName);
		return id;
	}
	/**获取项目id*/
	public String findProjectId(String projectName){
		String id =riskInfoDao.selectIdByProjectName(projectName);
		return id;
	}
	
	/**向风险信息表中插入新的数据*/
	@Transactional(readOnly=false)
	public int insertNewRiskInfo(RiskInfo riskInfo){
		int i=riskInfoDao.insertNewInfo(riskInfo);
		
		return i;
	}
	/**删除一条风险信息*/
	@Transactional(readOnly=false)
	public int deleteRiskInfo(String id){
		int i=riskInfoDao.deleteRiskInfoByRiskId(id);
		return i;
	}
	/**获取一条风险信息**/
	public RiskInfo findRiskInfoById(String id){
		RiskInfo ri=riskInfoDao.findRiskInfoById(id);
		return  ri;
	}
	/**更新风险信息*/
	@Transactional(readOnly=false)
	public int updateRiskInfo(RiskInfo riskInfo){
		return riskInfoDao.updateRiskInfoById(riskInfo);
	}
	
	/**
	 * 查询项目名称
	 * @return
	 */
	public List<String> getProjectName(){
		List<String> list=riskInfoDao.getProjectName();
		return list;
	}
}
