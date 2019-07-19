/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oaproject.dao.PmProjectDetailedDao;
import com.thinkgem.jeesite.modules.oaproject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单表生成Service
 *
 * @author zhangbingbing
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class PmProjectDetailedService extends CrudService<PmProjectDetailedDao, PmProjectDetailed> {
    @Autowired
    private PmProjectDetailedDao pmProjectDetailedDao;

    public PmProjectDetailed get(String id) {
        return super.get ( id );
    }

    public List<PmProjectDetailed> findList(PmProjectDetailed pmProjectDetailed) {
        return super.findList ( pmProjectDetailed );
    }

    public Page<PmProjectDetailed> findPage(Page<PmProjectDetailed> page, PmProjectDetailed pmProjectDetailed) {
        return super.findPage ( page, pmProjectDetailed );
    }

    @Transactional(readOnly = false)
    public void save(PmProjectDetailed pmProjectDetailed) {
        super.save ( pmProjectDetailed );
    }

    @Transactional(readOnly = false)
    public void delete(PmProjectDetailed pmProjectDetailed) {
        super.delete ( pmProjectDetailed );
    }

    /**
     * 保存项目与项目相关信息
     *
     * @param projectRelation
     */
    @Transactional(readOnly = false)
    public void saveProjectRelationList(PmProjectRelation projectRelation) {

        pmProjectDetailedDao.saveProjectRelationList ( projectRelation );
    }

    /**
     * 保存竞争对手信息和关联信息
     *
     * @param projectOpponent
     */
    @Transactional(readOnly = false)
    public void saveProjectOpponentList(PmProjectOpponent projectOpponent) {
        //保存竞争对手信息
        pmProjectDetailedDao.saveOpponentList ( projectOpponent );
        //保存关联信息
        pmProjectDetailedDao.saveProjectOpponentList ( projectOpponent );
    }

    @Transactional(readOnly = false)
    public void saveProjectCooperationList(PmProjectCooperation projectCooperation) {
        //保存合作单位信息
        pmProjectDetailedDao.saveCooperationList ( projectCooperation );
        //保存合作单位关联信息
        pmProjectDetailedDao.saveProjectCooperationList ( projectCooperation );
    }

    /**
     * 根据项目id查找项目的详细信息
     *
     * @param projectId
     * @return
     */
    @Transactional(readOnly = false)
    public PmProjectDetailed getProjectDetailed(String projectId) {

        return pmProjectDetailedDao.getProjectDetailed ( projectId );
    }

    /**
     * 保存项目和客户之间的关系
     *
     * @param projectCustomer
     */
    @Transactional(readOnly = false)
    public void saveProjectCustomerList(PmProjectCustomer projectCustomer) {

        pmProjectDetailedDao.saveProjectCustomerList ( projectCustomer );
    }

    /**
     * 删除项目关联表
     *
     * @param projectId
     */
    @Transactional(readOnly = false)
    public void delProjectRelation(String projectId) {

        pmProjectDetailedDao.delProjectRelation ( projectId );
    }

    /**
     * 删除项目客户关联表
     *
     * @param projectId
     */
    @Transactional(readOnly = false)
    public void delProjectCustomer(String projectId) {

        pmProjectDetailedDao.delProjectCustomer ( projectId );
    }

    /**
     * 删除项目竞争对手关联表
     *
     * @param projectId
     */
    @Transactional(readOnly = false)
    public void delProjectOpponent(String projectId) {
        pmProjectDetailedDao.delProjectOpponent ( projectId );
    }

    /**
     * 删除项目合作单位关联表
     *
     * @param projectId
     */
    @Transactional(readOnly = false)
    public void delProjectCooperation(String projectId) {

        pmProjectDetailedDao.delProjectCooperation ( projectId );
    }

}