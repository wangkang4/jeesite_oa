package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.VisitDao;
import com.thinkgem.jeesite.modules.oapms.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitService extends CrudService<VisitDao, Visit> {
    @Autowired
    private VisitDao visitDao;

    public Page<Visit> findPage(Page<Visit> page, Visit visit) {
        return super.findPage ( page, visit );
    }

    @Transactional(readOnly = false)
    public void insertVisit(Visit visit) {
        visitDao.insertVisit ( visit );
    }

    @Transactional(readOnly = false)
    public void deleteVisitById(String visitId) {
        visitDao.deleteVisitById ( visitId );
    }

    public String selectCreateByByVisitId(String visitId) {
        return visitDao.selectCreateByByVisitId ( visitId );
    }
}
