/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oaproject.dao.PmTaskMainDao;
import com.thinkgem.jeesite.modules.oaproject.entity.PmTaskMain;
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
public class PmTaskMainService extends CrudService<PmTaskMainDao, PmTaskMain> {
    @Autowired
    private PmTaskMainDao pmTaskMainDao;

    public PmTaskMain get(String id) {
        return super.get ( id );
    }

    public List<PmTaskMain> findList(PmTaskMain pmTaskMain) {
        return super.findList ( pmTaskMain );
    }

    public Page<PmTaskMain> findPage(Page<PmTaskMain> page, PmTaskMain pmTaskMain) {
        return super.findPage ( page, pmTaskMain );
    }

    @Transactional(readOnly = false)
    public void save(PmTaskMain pmTaskMain) {
        super.save ( pmTaskMain );
    }

    @Transactional(readOnly = false)
    public void delete(PmTaskMain pmTaskMain) {
        super.delete ( pmTaskMain );
    }

    /**
     * 保存抄送人和责任人
     *
     * @param correlationList
     */
    @Transactional(readOnly = false)
    public void saveCorrelationList(List<PmTaskMain> correlationList) {

        pmTaskMainDao.saveCorrelationList ( correlationList );
    }

    /**
     * 根据任务id查询对应信息
     *
     * @param taskId
     * @return
     */
    public PmTaskMain findPmTaskMain(String taskId) {
        // TODO Auto-generated method stub
        return pmTaskMainDao.findPmTaskMain ( taskId );
    }

    /**
     * 根据任务ID查询任务的负责人和抄送人
     *
     * @param id
     * @return
     */
    public List<PmTaskMain> getCorrelationList(String id) {

        return pmTaskMainDao.getCorrelationList ( id );
    }

    /**
     * 根据任务id删除抄送人和负责人
     *
     * @param id
     */
    @Transactional(readOnly = false)
    public void delCorrelation(String id) {

        pmTaskMainDao.delCorrelation ( id );
    }

    /**
     * 自定义的分页查询
     *
     * @param page
     * @param pmTaskMain
     * @return
     */
    public Page<PmTaskMain> findPageList(Page<PmTaskMain> page, PmTaskMain pmTaskMain) {
        pmTaskMain.setPage ( page );
        page.setList ( pmTaskMainDao.findPageList ( pmTaskMain ) );
        return page;
    }

}