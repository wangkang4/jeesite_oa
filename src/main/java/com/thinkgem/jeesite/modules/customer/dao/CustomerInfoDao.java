package com.thinkgem.jeesite.modules.customer.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.customer.entity.CustomerAnalysis;
import com.thinkgem.jeesite.modules.customer.entity.CustomerInfo;
import com.thinkgem.jeesite.modules.customer.entity.Family;
import com.thinkgem.jeesite.modules.customer.entity.Resume;

import java.util.List;

/**
 * @author Cxq
 * @ClassName: CustomerInfoDao
 * @Description: 客户管理DAO接口
 * @date 2018年3月14日 下午2:33:34
 */
@MyBatisDao
public interface CustomerInfoDao extends CrudDao<CustomerInfo> {

    void saveCustomerInfo(CustomerInfo customerInfo);

    void saveResume(Resume resume);

    void saveFamily(Family family);

    CustomerInfo findCustomerInfoById(String customerId);

    List<Resume> findResumeListById(String customerId);

    List<Family> findFamilyListById(String customerId);

    void saveCustomerAnalysis(CustomerAnalysis customerAnalysis);

    List<CustomerAnalysis> findCusAnalysisByCustomerId(String customerId);

    String[] findCustomerGroupById(String userId);

    void deleteResumeByCustomerId(String customerId);

    void deleteFamilyByCustomerId(String customerId);

    void deleteCustomerInfo(String customerId);

    List<CustomerInfo> findCreateByDate(String customerId);

    void updateCustomerInfo(CustomerInfo customerInfo);

    List<Resume> findResumeIdsByCusId(String customerId);

    void updateResume(Resume resume);

    List<Family> findFamilyIdsByCusId(String customerId);

    void updateFamily(Family family);

    List<CustomerAnalysis> findCusAnalysisIdsByCusId(String customerId);

    void updateCustomerAnalysis(CustomerAnalysis customerAnalysis);

    /**
     * 查询所有客户信息
     *
     * @return
     */
    List<CustomerInfo> getCustomerInfoList();

    /**
     * 查询树状信息
     *
     * @return
     */
    List<CustomerInfo> getCustomerInfoZtreeList();

    /**
     * 点击树状结构时的查询语句
     *
     * @param parentCode
     * @return
     */
    List<CustomerInfo> findPageList(String parentCode);

    /**
     * 获取所有父节点的信息
     *
     * @return
     */
    List<CustomerInfo> getZtreeInfoList();

    /**
     * 查询我的客户的树状信息
     *
     * @param id
     * @return
     */
    List<CustomerInfo> getMyCustomerInfoZtreeList(String id);

    /**
     * 保存审批意见
     *
     * @param customerInfo
     */
    void saveexamine(CustomerInfo customerInfo);

}
