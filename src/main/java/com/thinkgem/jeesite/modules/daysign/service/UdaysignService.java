/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.daysign.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.daysign.dao.UdaysignDao;
import com.thinkgem.jeesite.modules.daysign.entity.Udaysign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 打卡成功Service
 *
 * @author ShiLiangYu
 * @version 2017-07-31
 */
@Service
@Transactional(readOnly = true)
public class UdaysignService extends CrudService<UdaysignDao, Udaysign> {
    @Autowired
    private UdaysignDao udaysignDao;

    public Udaysign get(String id) {
        return super.get ( id );
    }

    public List<Udaysign> findList(Udaysign udaysign) {
        return super.findList ( udaysign );
    }

    public Page<Udaysign> findPage(Page<Udaysign> page, Udaysign udaysign) {
        return super.findPage ( page, udaysign );
    }

    @Transactional(readOnly = false)
    public void save(Udaysign udaysign) {
        super.save ( udaysign );
    }

    @Transactional(readOnly = false)
    public void update(Udaysign udaysign) {
        udaysignDao.update ( udaysign );
    }

    @Transactional(readOnly = false)
    public void delete(Udaysign udaysign) {
        super.delete ( udaysign );
    }

}