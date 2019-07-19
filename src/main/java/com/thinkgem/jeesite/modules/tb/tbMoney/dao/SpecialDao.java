package com.thinkgem.jeesite.modules.tb.tbMoney.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Special;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface SpecialDao extends CrudDao<Special>{
	
	void updateStatu(Special special);
	//主管意见
	void updateProneText(Special special);
    //财务总监意见
	void updatePrtwoText(Special special);
	void updatePrthreeText(Special special);
	void updatePrfourText(Special special);
	String selectTaskIdByProcinsId(String ProcinsId);
	Special getByProcInsId(String procInsId);
	void uploadSpecial(Special special);
	List<Special> findEmployeesList();
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Special> findList2(@Param(value="name")String name);
}
