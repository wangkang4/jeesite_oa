package com.thinkgem.jeesite.modules.tb.driving.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.driving.entity.Driving;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 
 * @ClassName: DrivingDao 
 * @Description: 自驾车出差申请dao层
 * @author: WangFucheng
 * @date 2018年7月16日 下午2:57:59 
 *
 */
@MyBatisDao
public interface DrivingDao extends CrudDao<Driving>{
	/**
	 * 
	 * @Title: selectTaskIdByProcinsId
	 * @Description: 通过流程实例id获取任务id
	 * @author: WangFucheng
	 * @param ProcinsId
	 * @return String   返回类型 
	 * @throws
	 */
	String selectTaskIdByProcinsId(String ProcinsId);
	/**
	 * 
	 * @Title: updateStatu
	 * @Description: 修改审核状态
	 * @author: WangFucheng
	 * @param driving void   返回类型 
	 * @throws
	 */
	void updateStatu(Driving driving);
	/**
	 * 
	 * @Title: updateProneText
	 * @Description: 修改主管的审核意见
	 * @author: WangFucheng
	 * @param driving void   返回类型 
	 * @throws
	 */
	void updateProneText(Driving driving);
	/**
	 * 
	 * @Title: getByProcInsId
	 * @Description: 通过流程实例id获取业务表实体
	 * @author: WangFucheng
	 * @param procInsId
	 * @return Driving   返回类型 
	 * @throws
	 */
	Driving getByProcInsId(String procInsId);
	/**
	 * 
	 * @Title: findEmployeesList
	 * @Description: 获取所有员工列表
	 * @author: WangFucheng
	 * @param driving
	 * @return List<Driving>   返回类型 
	 * @throws
	 */
	List<Driving> findEmployeesList(Driving driving);
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Driving> findList2(@Param(value="name")String name);
	/**
	 * 总裁意见
	 * @param driving
	 */
	void updatePrtwoText(Driving driving);
}
