package com.thinkgem.jeesite.modules.tb.pay.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.contract.entity.Contract;
import com.thinkgem.jeesite.modules.tb.pay.entity.Pay;
import com.thinkgem.jeesite.modules.tb.pay.entity.PayTypeBig;
import com.thinkgem.jeesite.modules.tb.pay.entity.PayTypeSmall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface PayDao extends CrudDao<Pay>{
	
	void updateProneText(Pay pay);
	void updatePrtwoText(Pay pay);
	void updatePrthreeText(Pay pay);
	void updatePrfourText(Pay pay);
	void updatePrfiveText(Pay pay);
	void updatePrsixText(Pay pay);
	void updatePrsevText(Pay pay);
	
	void updateStatu(Pay pay);
	String selectTaskIdByProcinsId(String ProcinsId);
	
	List<PayTypeSmall> selectFromPayTypeSmall();
	List<PayTypeBig> selectFromPayTypeBig();
	Pay getByProcInsId(String procInsId);
	List<Pay> findEmployeesList(Pay pay);
	void upload(Pay pay);
	List<Contract> getContract(String userId);
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Pay> findList2(@Param(value="name")String name);
	/**
	 * 通过流程实例id删除对应数据
	 * @param procInsId
	 */
	void deletePay(String procInsId);
	/**
	 * 通过流程实例id删除对应流程数据
	 * @param procInsId
	 */
	/*void deleteTask(String procInsId);*/
}
