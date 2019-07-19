/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oaproject.entity.PmProjectMain;

import java.util.List;

/**
 * 单表生成DAO接口
 *
 * @author zhangbingbing
 * @version 2018-03-13
 */
@MyBatisDao
public interface PmProjectMainDao extends CrudDao<PmProjectMain> {
    /**
     * 保存项目团队信息
     *
     * @param pmProjectMainList
     */
    void savePeople(List<PmProjectMain> pmProjectMainList);

    /**
     * 查询所有的项目
     *
     * @return
     */
    List<PmProjectMain> findPmProjectMainList();

    /**
     * 更具项目id查找项目概要信息
     *
     * @param projectId
     * @return
     */
    PmProjectMain getProjectMain(String projectId);

    /**
     * 根据项目id查找项目成员的详细信息
     *
     * @param id
     * @return
     */
    List<PmProjectMain> getProjectMainTeam(String id);

    /**
     * 删除成员表信息
     *
     * @param projectId
     */
    void delpeople(String projectId);

    /**
     * 查询项目关联表信息
     *
     * @param projectId
     * @return
     */
    List<PmProjectMain> getProjectRelationList(String projectId);

    /**
     * 查询竞争对手关联表信息
     *
     * @param projectId
     * @return
     */
    List<PmProjectMain> getProjectOpponentList(String projectId);

    /**
     * 查询项目客户信息关联表
     *
     * @param projectId
     * @return
     */
    List<PmProjectMain> getProjectCustomerList(String projectId);

    /**
     * 查询项目合作单位信息关联表
     *
     * @param projectId
     * @return
     */
    List<PmProjectMain> getProjectCooperationList(String projectId);

    int getNum(int year);

    /**
     * 获取客户信息
     *
     * @return
     */
    List<PmProjectMain> getCustomerList();

    /**
     * 保存附件
     *
     * @param projectDocument
     */
    void saveDocument(PmProjectMain projectDocument);

    /**
     * 查询附件信息
     *
     * @param id
     * @return
     */
    List<PmProjectMain> getDocumentList(String id);

    /**
     * 查询附件信息
     *
     * @param id
     * @return
     */
    PmProjectMain getDocument(String id);

    /**
     * 删除文件
     *
     * @param id
     */
    void delProjectDocument(String id);

    /**
     * 3保存审核意见
     *
     * @param pmProjectMain
     */
    void saveExamine(PmProjectMain pmProjectMain);

}