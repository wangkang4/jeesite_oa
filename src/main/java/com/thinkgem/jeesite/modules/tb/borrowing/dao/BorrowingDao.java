package com.thinkgem.jeesite.modules.tb.borrowing.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.borrowing.entity.Borrowing;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface BorrowingDao extends CrudDao<Borrowing>{
	
	String selectTaskIdByProcinsId(String ProcinsId);
	Borrowing getByProcInsId(String procInsId);
	void updateProneText(Borrowing borrowing);
	void updatePrtwoText(Borrowing borrowing);
	void updatePrthreeText(Borrowing borrowing);
	void updatePrfourText(Borrowing borrowing);
	void updatePrfiveText(Borrowing borrowing);
	void updatePrsixText(Borrowing borrowing);
	void updatePrsevenText(Borrowing borrowing);
	void updatePreightText(Borrowing borrowing);
	void updateStatu(Borrowing borrowing);
	List<Borrowing> findEmployeesList(Borrowing borrowing);
	void upload(Borrowing borrowing);
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Borrowing> findList2(@Param(value="name")String name);
}
