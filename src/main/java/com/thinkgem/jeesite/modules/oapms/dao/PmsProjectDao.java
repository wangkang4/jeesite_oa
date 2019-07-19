package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface PmsProjectDao extends CrudDao<PmsProject> {

    int inserteOneProject(PmsProject pmsProject);

    int inserteRelativers(ProjectRelativer pr);

    int deleteOneProject(String id);

    PmsProject getOneProject(String id);

    List<ProjectRelativer> findPersonList(String id);

    List<PmsProject> findIdAndName();

    int updateOneProject(PmsProject pmsProject);

    void deleteAllRelativers(String id);

    List<Customer> findCustomerList();

    List<CustomerContact> findCustomerContactList(String id);

    List<ProjectRelativer> findAllprListByProject(String projectId);

    /**
     * @return List<HighchartsEntity>   返回类型
     * @throws
     * @Title: selectMoneyAndIndustry
     * @Description: TODO(以行业为X轴查询Y轴值)
     * @author: WangFucheng
     */
    List<HighchartsEntity> selectIndustryAndY();

    /**
     * @return List<HighchartsEntity>   返回类型
     * @throws
     * @Title: selectOfficeAndY
     * @Description: TODO(以办事处为X轴查询Y轴值)
     * @author: WangFucheng
     */
    List<HighchartsEntity> selectOfficeAndY();

    /**
     * @return List<HighchartsEntity>   返回类型
     * @throws
     * @Title: selectAreaAndY
     * @Description: TODO(以区域为X轴查询Y轴值)
     * @author: WangFucheng
     */
    List<HighchartsEntity> selectAreaAndY();

    /**
     * @return List<HighchartsEntity>   返回类型
     * @throws
     * @Title: selectCustomerAndY
     * @Description: TODO(以客户为X轴查询Y轴值)
     * @author: WangFucheng
     */
    List<HighchartsEntity> selectCustomerAndY();

    /**
     * @return List<HighchartsEntity>   返回类型
     * @throws
     * @Title: selectSalerAndY
     * @Description: TODO(以销售经理为X轴查询Y轴值)
     * @author: WangFucheng
     */
    List<HighchartsEntity> selectSalerAndY();

    /**
     * @return List<HighchartsEntity>   返回类型
     * @throws
     * @Title: selectProducterAndY
     * @Description: TODO(以产品经理为X轴查询Y轴值)
     * @author: WangFucheng
     */
    List<HighchartsEntity> selectProducterAndY();

    /**
     * @return HighchartsEntity   返回类型
     * @throws
     * @Title: selectTimeAndY
     * @Description: TODO(以时间为X轴查询Y轴值)
     * @author: WangFucheng
     */
    HighchartsEntity selectTimeAndY(@Param("year") String year, @Param("month") String month);

    /**
     * @return List<HighchartsEntity>   返回类型
     * @throws
     * @Title: selectStatusAndY
     * @Description: TODO(以项目状态为X轴查询Y轴值)
     * @author: WangFucheng
     */
    List<HighchartsEntity> selectStatusAndY();

    /**
     * @return List<HighchartsEntity>   返回类型
     * @throws
     * @Title: selectProductTypeAndY
     * @Description: TODO(以产品类型为X轴查询Y轴值)
     * @author: WangFucheng
     */
    List<HighchartsEntity> selectProductTypeAndY();


}
