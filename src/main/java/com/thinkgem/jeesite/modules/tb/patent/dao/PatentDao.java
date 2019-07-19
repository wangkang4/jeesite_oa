package com.thinkgem.jeesite.modules.tb.patent.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.patent.entity.Patent;
import com.thinkgem.jeesite.modules.tb.patent.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface PatentDao extends CrudDao<Patent>{
	
	/**
	 * 插入负表数据
	 * @param person
	 */
	void insertPerson(Person person);

	/**
	 * 根据P_ID查询负表信息
	 * @param id
	 * @return
	 */
	List<Person> findPerson(String id);

	void updateProneText(Patent patent);

	void updatePrtwoText(Patent patent);

	void updatePrthreeText(Patent patent);

	void updatePrfourText(Patent patent);

	void updateStatu(Patent patent);

	String selectTaskIdByProcinsId(String procInsId);
	
	/**
	 * 删除附表指定信息
	 * @param id
	 */
	void deletePerson(String id);

	Patent getByProcInsId(String procInsId);
	
	/**
	 * 撤销流程
	 * @param procInsId
	 */
	void deletePatent(String procInsId);
	void deleteTask(String procInsId);

	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Patent> findList2(@Param(value="name")String name);

	/**
	 * 财务人员查看员工申请列表
	 * @param patent
	 * @return
	 */
	List<Patent> findList3(Patent patent);
}
