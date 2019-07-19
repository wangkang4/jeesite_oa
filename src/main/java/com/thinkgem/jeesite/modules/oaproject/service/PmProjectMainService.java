/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oaproject.dao.PmProjectMainDao;
import com.thinkgem.jeesite.modules.oaproject.entity.PmProjectMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单表生成Service
 *
 * @author zhangbingbing
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class PmProjectMainService extends CrudService<PmProjectMainDao, PmProjectMain> {

    @Autowired
    private PmProjectMainDao pmProjectMainDao;

    public PmProjectMain get(String id) {
        return super.get ( id );
    }

    public List<PmProjectMain> findList(PmProjectMain pmProjectMain) {
        return super.findList ( pmProjectMain );
    }

    public Page<PmProjectMain> findPage(Page<PmProjectMain> page, PmProjectMain pmProjectMain) {
        return super.findPage ( page, pmProjectMain );
    }

    @Transactional(readOnly = false)
    public void save(PmProjectMain pmProjectMain) {
        super.save ( pmProjectMain );
    }

    @Transactional(readOnly = false)
    public void delete(PmProjectMain pmProjectMain) {
        super.delete ( pmProjectMain );
    }

    /**
     * 保存团队信息
     *
     * @param pmProjectMainList
     */
    @Transactional(readOnly = false)
    public void savePeople(List<PmProjectMain> pmProjectMainList) {

        pmProjectMainDao.savePeople ( pmProjectMainList );
    }

    /**
     * 查询所有的项目信息
     *
     * @return
     */
    public List<PmProjectMain> findPmProjectMainList() {

        return pmProjectMainDao.findPmProjectMainList ();
    }

    /**
     * 更具项目id查找项目概要信息
     *
     * @param projectId
     * @return
     */
    public PmProjectMain getProjectMain(String projectId) {
        // TODO Auto-generated method stub
        return pmProjectMainDao.getProjectMain ( projectId );
    }

    /**
     * 根据项目id查找项目成员的详细信息
     *
     * @param projectId
     * @return
     */
    public List<PmProjectMain> getProjectMainTeam(String id) {

        return pmProjectMainDao.getProjectMainTeam ( id );
    }

    /**
     * 删除成员表信息
     *
     * @param projectId
     */
    @Transactional(readOnly = false)
    public void delpeople(String projectId) {

        pmProjectMainDao.delpeople ( projectId );
    }

    /**
     * 查询项目关联表信息
     *
     * @param projectId
     * @return
     */
    public List<PmProjectMain> getProjectRelationList(String projectId) {

        return pmProjectMainDao.getProjectRelationList ( projectId );
    }

    /**
     * 查询竞争对手关联表信息
     *
     * @param projectId
     * @return
     */
    public List<PmProjectMain> getProjectOpponentList(String projectId) {

        return pmProjectMainDao.getProjectOpponentList ( projectId );
    }

    /**
     * 查询项目客户信息关联表
     *
     * @param projectId
     * @return
     */
    public List<PmProjectMain> getProjectCustomerList(String projectId) {

        return pmProjectMainDao.getProjectCustomerList ( projectId );
    }

    /**
     * 查询项目合作单位信息关联表
     *
     * @param projectId
     * @return
     */
    public List<PmProjectMain> getProjectCooperationList(String projectId) {

        return pmProjectMainDao.getProjectCooperationList ( projectId );
    }

    public int getNum(int year) {

        return pmProjectMainDao.getNum ( year );
    }

    /**
     * 获取客户信息
     *
     * @return
     */
    public List<PmProjectMain> getCustomerList() {

        return pmProjectMainDao.getCustomerList ();
    }

    /**
     * 保存附件
     *
     * @param projectDocument
     */
    @Transactional(readOnly = false)
    public void saveDocument(PmProjectMain projectDocument) {
        // TODO Auto-generated method stub
        pmProjectMainDao.saveDocument ( projectDocument );
    }

    /**
     * 查询附件信息
     *
     * @param id
     * @return
     */
    public List<PmProjectMain> getDocumentList(String id) {
        // TODO Auto-generated method stub
        return pmProjectMainDao.getDocumentList ( id );
    }

    /**
     * 查询附件信息
     *
     * @param id
     * @return
     */
    public PmProjectMain getDocument(String id) {
        // TODO Auto-generated method stub
        return pmProjectMainDao.getDocument ( id );
    }

    /**
     * 删除文件
     *
     * @param id
     */
    @Transactional(readOnly = false)
    public void delProjectDocument(String id) {
        // TODO Auto-generated method stub
        pmProjectMainDao.delProjectDocument ( id );
    }

    /**
     * 保存审核意见
     *
     * @param pmProjectMain
     */
    @Transactional(readOnly = false)
    public void saveExamine(PmProjectMain pmProjectMain) {
        // TODO Auto-generated method stub
        pmProjectMainDao.saveExamine ( pmProjectMain );
    }

}