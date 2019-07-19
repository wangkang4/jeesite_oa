package com.thinkgem.jeesite.modules.tb.oversee.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.tb.oversee.dao.OverseeDao;
import com.thinkgem.jeesite.modules.tb.oversee.entity.Oversee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OverseeService extends CrudService<OverseeDao, Oversee> {

    @Autowired
    private ActTaskService actTaskService;

    public Page<Oversee> findOverseePage(Page<Oversee> page, Oversee oversee) {
        oversee.setPage(page);
        page.setList(dao.findOverseeList(oversee));
        return page;
    }
}
