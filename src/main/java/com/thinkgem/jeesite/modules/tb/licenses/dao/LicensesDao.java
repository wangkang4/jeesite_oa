package com.thinkgem.jeesite.modules.tb.licenses.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.licenses.entity.Licenses;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface LicensesDao extends CrudDao<Licenses> {
	
	String selectTaskIdByProcinsId(String procInstId);
	Licenses getByProcInsId(String procInsId);
	//修改审核状态
	void updateStatu(Licenses licenses);
	//主管审批
	void updateProneText(Licenses licenses);
	//财务总监审批
	void updatePrtwoText(Licenses licenses);
	//印章管理人审批
	void updatePrthreeText(Licenses licenses);
	//员工证照列表
	List<Licenses> findEmployeesList(Licenses licenses);
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Licenses> findList2(@Param(value="name")String name);
}
