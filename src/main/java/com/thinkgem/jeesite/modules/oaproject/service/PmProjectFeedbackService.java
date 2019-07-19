/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oaproject.dao.PmProjectFeedbackDao;
import com.thinkgem.jeesite.modules.oaproject.entity.PmProjectFeedback;
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
public class PmProjectFeedbackService extends CrudService<PmProjectFeedbackDao, PmProjectFeedback> {

    public PmProjectFeedback get(String id) {
        return super.get ( id );
    }

    public List<PmProjectFeedback> findList(PmProjectFeedback pmProjectFeedback) {
        return super.findList ( pmProjectFeedback );
    }

    public Page<PmProjectFeedback> findPage(Page<PmProjectFeedback> page, PmProjectFeedback pmProjectFeedback) {
        return super.findPage ( page, pmProjectFeedback );
    }

    @Transactional(readOnly = false)
    public void save(PmProjectFeedback pmProjectFeedback) {
        super.save ( pmProjectFeedback );
    }

    @Transactional(readOnly = false)
    public void delete(PmProjectFeedback pmProjectFeedback) {
        super.delete ( pmProjectFeedback );
    }

}