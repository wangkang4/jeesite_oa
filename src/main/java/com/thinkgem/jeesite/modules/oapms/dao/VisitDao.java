package com.thinkgem.jeesite.modules.oapms.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.Visit;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface VisitDao extends CrudDao<Visit> {
    /**
     * @param visit void   返回类型
     * @throws
     * @Title: insertVisit
     * @Description: TODO(插入一条拜访记录)
     * @author: WangFucheng
     */
    public void insertVisit(Visit visit);

    /**
     * @param visitId void   返回类型
     * @throws
     * @Title: deleteVisitById
     * @Description: TODO(根据id删除拜访记录)
     * @author: WangFucheng
     */
    public void deleteVisitById(@Param("visitId") String visitId);

    /**
     * @param visitId
     * @return String   返回类型
     * @throws
     * @Title: selectCreateByByVisitId
     * @Description: TODO(根据id查询创建人)
     * @author: WangFucheng
     */
    public String selectCreateByByVisitId(@Param("visitId") String visitId);
}
