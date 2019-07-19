package com.thinkgem.jeesite.modules.daily.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.daily.dao.TCheckBacklogDao;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ShiLiangYu
 */
@Service
@Transactional(readOnly = true)
public class TCheckBacklogService extends CrudService<TCheckBacklogDao, TCheckBacklog> {

    @Autowired
    private TCheckBacklogDao tCheckBacklogDao;

    //获取提交人数；
    public int getNum() {
        int num = tCheckBacklogDao.getNum ();
        return num;
    }

    @Override
    public Page<TCheckBacklog> findPage(Page<TCheckBacklog> page, TCheckBacklog entity) {
        // TODO Auto-generated method stub
        return super.findPage ( page, entity );
    }

    @Override
    @Transactional(readOnly = false)
    public void save(TCheckBacklog entity) {
        // TODO Auto-generated method stub
        super.save ( entity );
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(TCheckBacklog entity) {
        // TODO Auto-generated method stub
        super.delete ( entity );
    }

    public int findYstayCount(TCheckBacklog tCheckBacklog) {
        // TODO Auto-generated method stub
        return tCheckBacklogDao.findYstayCount ( tCheckBacklog );
    }

}
