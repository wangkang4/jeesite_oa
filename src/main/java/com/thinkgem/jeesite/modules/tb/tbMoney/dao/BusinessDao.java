package com.thinkgem.jeesite.modules.tb.tbMoney.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Business;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.ReceptionStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface BusinessDao extends CrudDao<Business>{
	
	void updateStatu(Business business);
	//主管意见
	void updateProneText(Business business);
    //财务总监意见
	void updatePrtwoText(Business business);
	void updatePrthreeText(Business business);
	void updatePrfourText(Business business);
	String selectTaskIdByProcinsId(String ProcinsId);
	void insertReceptionStaff(ReceptionStaff receptionStaff);
	List<ReceptionStaff> selectReceptionStaff(String id);
	void deleteReceptionStaffById(String id);
	Business getByProcInsId(String procInsId);
	void uploadBusiness(Business business);
	List<Business> findEmployeesList();
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Business> findList2(@Param(value="name")String name);
}
