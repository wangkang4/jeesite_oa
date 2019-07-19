package com.thinkgem.jeesite.modules.tb.chapter.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.chapter.entity.Chapter;
import com.thinkgem.jeesite.modules.tb.chapter.entity.ChapterType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface ChapterDao extends CrudDao<Chapter> {
	/**
	 * 
	 * @Title: selectChapterType
	 * @Description: TODO(查询一共有多少印章类型) 
	 * @author: WangFucheng
	 * @return List<String>   返回类型 
	 * @throws
	 */
	List<ChapterType> selectChapterType();
	/**
	 * 
	 * @Title: selectFileType
	 * @Description: TODO(查询一共有多少用印文件类型) 
	 * @author: WangFucheng
	 * @return List<ChapterType>   返回类型 
	 * @throws
	 */
	List<ChapterType> selectFileType();
	
	String selectTaskIdByProcinsId(String procInstId);
	Chapter getByProcInsId(String procInsId);
	//修改审核状态
	void updateStatu(Chapter chapter);
	//部门经理审批
	void updateProneText(Chapter chapter);
	//研发总监审批
	void updatePrtwoText(Chapter chapter);
	//主管审批
	void updatePrthreeText(Chapter chapter);
	//财务总监审批
	void updatePrfourText(Chapter chapter);
	//印章管理人审批
	void updatePrfiveText(Chapter chapter);
	//商务主管审核意见
	void updatePrsixText(Chapter chapter);
	List<Chapter> findEmployeesList(Chapter chapter);
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<Chapter> findList2(@Param(value="name")String name);
	/**
	 * 验证印章是否被外借使用
	 * @param ct
	 * @return
	 */
	int findChapterUse(@Param(value = "ct")String ct);
	/**
	 * 查询正在被使用的外借章名
	 * @param ctName
	 * @return
	 */
	String findChapterUseName(@Param(value = "ctName")String ctName);
}
