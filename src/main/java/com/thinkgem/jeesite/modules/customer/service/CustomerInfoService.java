package com.thinkgem.jeesite.modules.customer.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.customer.dao.CustomerInfoDao;
import com.thinkgem.jeesite.modules.customer.entity.CustomerAnalysis;
import com.thinkgem.jeesite.modules.customer.entity.CustomerInfo;
import com.thinkgem.jeesite.modules.customer.entity.Family;
import com.thinkgem.jeesite.modules.customer.entity.Resume;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Cxq
 * @ClassName: CustomerInfoService
 * @Description: 客户管理Service
 * @date 2018年3月14日 下午2:36:28
 */
@Service
@Transactional(readOnly = true)
public class CustomerInfoService extends CrudService<CustomerInfoDao, CustomerInfo> {

    @Transactional(readOnly = false)
    public void saveCustomerInfo(CustomerInfo customerInfo) {
        dao.saveCustomerInfo ( customerInfo );
    }

    @Transactional(readOnly = false)
    public void saveResume(Resume resume) {
        dao.saveResume ( resume );
    }

    @Transactional(readOnly = false)
    public void saveFamily(Family family) {
        dao.saveFamily ( family );
    }

    public CustomerInfo findCustomerInfoById(String customerId) {
        return dao.findCustomerInfoById ( customerId );
    }

    public List<Resume> findResumeListById(String customerId) {
        return dao.findResumeListById ( customerId );
    }

    public List<Family> findFamilyListById(String customerId) {
        return dao.findFamilyListById ( customerId );
    }

    /**
     * @param 设定文件
     * @return void    返回类型
     * @throws
     * @Title: saveCustomerAnalysis
     * @Description: 保存客户分析内容
     */
    @Transactional(readOnly = false)
    public void saveCustomerAnalysis(CustomerAnalysis customerAnalysis) {
        dao.saveCustomerAnalysis ( customerAnalysis );
    }

    public List<CustomerAnalysis> findCusAnalysisByCustomerId(String customerId) {
        return dao.findCusAnalysisByCustomerId ( customerId );
    }

    public String[] findCustomerGroupById(String userId) {
        return dao.findCustomerGroupById ( userId );
    }

    @Transactional(readOnly = false)
    public void deleteResumeByCustomerId(String customerId) {
        dao.deleteResumeByCustomerId ( customerId );
    }

    @Transactional(readOnly = false)
    public void deleteFamilyByCustomerId(String customerId) {
        dao.deleteFamilyByCustomerId ( customerId );
    }

    @Transactional(readOnly = false)
    public void deleteCustomerInfo(String customerId) {
        dao.deleteCustomerInfo ( customerId );
    }

    public List<CustomerInfo> findCreateByDate(String customerId) {
        return dao.findCreateByDate ( customerId );
    }

    @Transactional(readOnly = false)
    public void updateCustomerInfo(CustomerInfo customerInfo) {
        dao.updateCustomerInfo ( customerInfo );
    }

    public List<Resume> findResumeIdsByCusId(String customerId) {
        return dao.findResumeIdsByCusId ( customerId );
    }

    @Transactional(readOnly = false)
    public void updateResume(Resume resume) {
        dao.updateResume ( resume );
    }

    public List<Family> findFamilyIdsByCusId(String customerId) {
        return dao.findFamilyIdsByCusId ( customerId );
    }

    @Transactional(readOnly = false)
    public void updateFamily(Family family) {
        dao.updateFamily ( family );
    }

    public List<CustomerAnalysis> findCusAnalysisIdsByCusId(String customerId) {
        return dao.findCusAnalysisIdsByCusId ( customerId );
    }

    @Transactional(readOnly = false)
    public void updateCustomerAnalysis(CustomerAnalysis customerAnalysis) {
        dao.updateCustomerAnalysis ( customerAnalysis );
    }

    /**
     * 查询所有客户信息
     *
     * @return
     */
    public List<CustomerInfo> getCustomerInfoList() {

        return dao.getCustomerInfoList ();
    }

    /**
     * 查询树状信息
     *
     * @return
     */
    public List<CustomerInfo> getCustomerInfoZtreeList() {

        return dao.getCustomerInfoZtreeList ();
    }

    public Page<CustomerInfo> findPageList(Page<CustomerInfo> page, CustomerInfo customerInfo) {
        customerInfo.setPage ( page );
        page.setList ( dao.findPageList ( customerInfo.getParentId () ) );
        return page;
    }

    /**
     * 获取所有父节点的信息
     *
     * @return
     */
    public List<CustomerInfo> getZtreeInfoList() {

        return dao.getZtreeInfoList ();
    }

    /**
     * 查询我的客户的树状信息
     *
     * @param id
     * @return
     */
    public List<CustomerInfo> getMyCustomerInfoZtreeList(String id) {

        return dao.getMyCustomerInfoZtreeList ( id );
    }

    /**
     * 保存审批意见
     *
     * @param customerInfo
     */
    @Transactional(readOnly = false)
    public void saveexamine(CustomerInfo customerInfo) {
        dao.saveexamine ( customerInfo );
    }

}
