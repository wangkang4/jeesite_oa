package com.thinkgem.jeesite.sale.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Business;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Special;
import com.thinkgem.jeesite.sale.entity.DownloadGetSale;
import com.thinkgem.jeesite.sale.entity.GetSale;
import com.thinkgem.jeesite.sale.entity.GetSaleCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface GetSaleDao extends CrudDao<GetSale>{

	String getOfficeNameByUserId(String id);

	Office getOfficeByUserId(String id);

	public List<DownloadGetSale> downList(GetSale getSale);

	//人事主管意见
	void updatePrText(GetSale getSale);
	//部门经理意见
	void updateLeaderText(GetSale getSale);
	//主管意见
	void updateLeadertwoText(GetSale getSale);
    //研发总监意见
	void updateManagerText(GetSale getSale);
	//财务意见
	void updateprthreeText(GetSale getSale);
	//财务主管意见
	void updateprfourText(GetSale getSale);
	//财务总监高会敏意见 
	void updateprtwoText(GetSale getSale);
	//总经理意见
	void updateprfiveText(GetSale getSale);
	//根据流程实例id查询当前任务主键
	String selectTaskKey(String procInstId);


	GetSale getByProcInsId(String procInsId);
	
		
		//通过procInsId获取saleDetailId;
    String getSaleDetailIdByProcInsId(String procInsId);
    
    List<GetSale> findPages(GetSale getSale);
    
    List<GetSale> findCWPage(GetSale getSale);
    	 
    List<GetSale> findDraft(GetSale getSale);
    
//   //更新审核状态为 驳回
//    int updateNotAgree(GetSale getSale);
//    
//    //更新审核状态为  审核成功；
//    int updateSuccess(GetSale getSale);
    void updateStatu(GetSale getSale);

	Object updateForm(GetSale getSale);

	void deleteDraft(GetSale getSale);
	
	/**
	 * 标题改为：2018030001-办公费用-名片打印费-商务部-麻青青
	 * @Title: selectCount
	 * @Description: TODO(查询最后一条数据是第几条以及所属月份) 
	 * @author: WangFucheng
	 * @return List<GetSaleCount>   返回类型 
	 * @throws
	 */
	GetSaleCount selectCount();
	/**
	 * 
	 * @Title: updateCount
	 * @Description: TODO(修改最后一条数据是第几条以及所属月份) 
	 * @author: WangFucheng
	 * @param getSaleCount void   返回类型 
	 * @throws
	 */
	void updateCount(GetSaleCount getSaleCount);
	
	String selectTaskIdByProcinsId(String ProcinsId);
	
	List<String> selectProcInsId(); 
	void upload(GetSale getSale);
	void withdraw(String id);
	void insertRollBack(@Param(value="taskId")String taskId,@Param(value="process")String process);
	List<String> selectRollBack();
	List<Special> selectSpecial(String id);
	List<Business> selectBusiness(String id);
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param name
	 * @return
	 */
	List<GetSale> findList2(@Param(value="name")String name);
	
	/**
	 * 修改详情表中的出差编号
	 * @param deId
	 */
	void updateNum(@Param(value="deId")String deId);
	
}
