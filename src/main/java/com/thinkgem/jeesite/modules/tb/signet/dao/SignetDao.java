package com.thinkgem.jeesite.modules.tb.signet.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.signet.entity.Signet;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface SignetDao extends CrudDao<Signet> {
	
	String selectTaskIdByProcinsId(String procInstId);
	Signet getByProcInsId(String procInsId);
	//修改审核状态
	void updateStatu(Signet signet);
	//财务总监审批
	void updateProneText(Signet signet);
	//财务总监审批
	void updatePrtwoText(Signet signet);
	//员工印章刻制列表
	List<Signet> findEmployeesList(Signet signet);
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Signet> findList2(@Param(value="name")String name);
}
