package com.thinkgem.jeesite.modules.tb.induction.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.induction.entity.Induction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface InductionDao extends CrudDao<Induction>{
	int insert(Induction induction);
	void updateProneText(Induction induction);
	void updatePrtwoText(Induction induction);
	void updatePrthreeText(Induction induction);
	void updatePrfourText(Induction induction);
	void updatePrfiveText(Induction induction);
	void updatePrsixText(Induction induction);
	void updateStatu(Induction induction);
	String selectTaskIdByProcinsId(String procInstId);
	Induction getByProcInsId(String procInsId);
	String selectAreaNameByOfficeId(String officeId);
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Induction> findList2(@Param(value="name")String name);

	/**
	 * 财务人员查看员工录用列表
	 * @param induction
	 * @return
	 */
	List<Induction> findList3(Induction induction);
}
