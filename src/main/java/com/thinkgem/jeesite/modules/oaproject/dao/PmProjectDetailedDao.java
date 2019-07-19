/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oaproject.entity.*;

/**
 * 单表生成DAO接口
 *
 * @author zhangbingbing
 * @version 2018-03-14
 */
@MyBatisDao
public interface PmProjectDetailedDao extends CrudDao<PmProjectDetailed> {
    /**
     * 保存项目与项目相关信息
     *
     * @param projectRelation
     */
    void saveProjectRelationList(PmProjectRelation projectRelation);

    /**
     * 保存竞争对手信息
     *
     * @param projectOpponent
     */
    void saveOpponentList(PmProjectOpponent projectOpponent);

    /**
     * 保存竞争对手关联信息
     *
     * @param projectOpponent
     */
    void saveProjectOpponentList(PmProjectOpponent projectOpponent);

    /**
     * 保存合作单位信息
     *
     * @param projectCooperation
     */
    void saveCooperationList(PmProjectCooperation projectCooperation);

    /**
     * 保存合作单位关联信息
     *
     * @param projectCooperation
     */
    void saveProjectCooperationList(PmProjectCooperation projectCooperation);

    /**
     * 根据项目id查找项目的详细信息
     *
     * @param projectId
     * @return
     */
    PmProjectDetailed getProjectDetailed(String projectId);

    /**
     * 保存项目和客户之间的关系
     *
     * @param projectCustomer
     */
    void saveProjectCustomerList(PmProjectCustomer projectCustomer);

    /**
     * 删除项目关联表
     *
     * @param projectId
     */
    void delProjectRelation(String projectId);

    /**
     * 删除项目客户关联表
     *
     * @param projectId
     */
    void delProjectCustomer(String projectId);

    /**
     * 删除项目竞争对手关联表
     *
     * @param projectId
     */
    void delProjectOpponent(String projectId);

    /**
     * 删除项目合作单位关联表
     *
     * @param projectId
     */
    void delProjectCooperation(String projectId);

}