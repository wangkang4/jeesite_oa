package com.thinkgem.jeesite.modules.tb.contract.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.contract.entity.Contract;
import com.thinkgem.jeesite.sale.entity.GetSaleCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface ContractDao extends CrudDao<Contract>{
	String selectAreaById(String id);
	void updateProneText(Contract contract);
	void updatePrtwoText(Contract contract);
	void updatePrthreeText(Contract contract);
	void updatePrfourText(Contract contract);
	void updatePrfiveText(Contract contract);
	void updatePrsixText(Contract contract);
	void updatePrsevenText(Contract contract);
	void updateStatu(Contract contract);
	void updateStatus(Contract contract);
	List<Contract> selectContractToAssociated(String contractApply);
	String selectTaskIdByProcinsId(String ProcinsId);
	Contract getByProcInsId(String procInsId);
	GetSaleCount selectCount();
	void updateCount(GetSaleCount gsc);
	List<Contract> findContract(Contract contract);
	void abandon(Contract contract);
	void withdraw(String id);
	List<String> selectProcInsId();
	List<Contract> findEmployeesList(Contract contract);
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Contract> findList2(@Param(value="name")String name);


	/**
	 * 根据申请人姓名查询所属部门
	 * @param name
	 * @return String
	 */
	String findOfficeByName(@Param("name")String name);

	/**
	 * 根据产品经理id查询产品经理名字
	 * @param id
	 * @return name
	 */
	String getManagerName(@Param("id")String id);
}
