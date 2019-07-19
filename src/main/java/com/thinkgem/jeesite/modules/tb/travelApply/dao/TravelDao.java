package com.thinkgem.jeesite.modules.tb.travelApply.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.travelApply.entity.TravelApply;
import com.thinkgem.jeesite.modules.tb.travelApply.entity.TravelPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface TravelDao extends CrudDao<TravelApply>{
	
	
	/**
	 * 向附表中插入数据
	 * @param travelPlan
	 */
	void insertPlan(TravelPlan travelPlan);
	
	/**
	 * 删除负表信息
	 * @param planId
	 */
	void deletePlan(String planId);

	/**
	 * 修改流程状态信息
	 * @param travelApply
	 */
	void updateStatu(TravelApply travelApply);

	/**
	 * 查询实例ID
	 * @param procInsId
	 * @return
	 */
	String selectTaskIdByProcinsId(String procInsId);

	/**
	 * 修改一级审核人意见
	 * @param travelApply
	 */
	void updateProneText(TravelApply travelApply);

	/**
	 * 修改二级审核人意见
	 * @param travelApply
	 */
	void updatePrtwoText(TravelApply travelApply);

	/**
	 * 通过流程实例ID查询数据信息
	 * @param procInsId
	 * @return
	 */
	TravelApply getByProcInsId(String procInsId);

	/**
	 * 查询负表信息，返回集合
	 * @param planId
	 * @return
	 */
	List<TravelPlan> findPlan(String planId);

	/**
	 * 删除主表信息
	 * @param procInsId
	 */
	void deletetravelApply(String procInsId);

	/**
	 * 删除流程执行表信息
	 * @param procInsId
	 */
	void deleteTask(String procInsId);

	/**
	 * 查询是否有改天数据信息
	 * @param num
	 * @return
	 */
	Integer selectNum(String num);
	
	/**
	 * 查询所属区域的员工信息
	 * @param name
	 * @return
	 */
	List<TravelApply> findList3(@Param(value="name")String name);

	/**
	 * 财务人员查看员工申请列表
	 * @return
	 */
	List<TravelApply> findListCaiWu( TravelApply travelApply);


	
}
