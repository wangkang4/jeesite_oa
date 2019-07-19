package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.ProjectHelpDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProject;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectExpense;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectHelp;
import com.thinkgem.jeesite.modules.oapms.entity.ProjectRelativer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectHelpService extends CrudService<ProjectHelpDao, PmsProjectHelp> {

    @Autowired
    private ProjectHelpDao projecHelptDao;

    public Page<PmsProjectHelp> findProjectHelpList(Page<PmsProjectHelp> page, PmsProjectHelp pmsProjectHelp) {
        pmsProjectHelp.setPage ( page );
        page.setList ( projecHelptDao.findProjectHelpList ( pmsProjectHelp ) );
        return page;
    }

    public PmsProjectHelp getOneProjectHelp(String id) {
        return projecHelptDao.getOneProjectHelp ( id );
    }

    public PmsProject getOnePmsProjectByProjectId(String id) {
        return projecHelptDao.getOnePmsProjectByProjectId ( id );
    }

    @Transactional(readOnly = false)
    public int addOneProjectHelp(PmsProjectHelp pmsProjectHelp) {
        return projecHelptDao.addOneProjectHelp ( pmsProjectHelp );
    }

    @Transactional(readOnly = false)
    public void insertHelperToRelative(ProjectRelativer pr) {
        projecHelptDao.insertHelperToRelative ( pr );
    }

    @Transactional(readOnly = false)
    public int updateOneProjectHelp(PmsProjectHelp pmsProjectHelp) {
        return projecHelptDao.updateOneProjectHelp ( pmsProjectHelp );
    }

    @Transactional(readOnly = false)
    public int deleteHelperInRelative(ProjectRelativer prbefore) {
        return projecHelptDao.deleteHelperInRelative ( prbefore );
    }

    @Transactional(readOnly = false)
    public void deleteOneProjectHelp(String id) {
        projecHelptDao.deleteOneProjectHelp ( id );
    }

    public Page<PmsProjectExpense> findProjectExpenseList(Page<PmsProjectExpense> page,
                                                          PmsProjectExpense pmsProjectExpense) {
        pmsProjectExpense.setPage ( page );
        page.setList ( projecHelptDao.findProjectExpenseList ( pmsProjectExpense ) );
        return page;
    }

    public PmsProjectExpense getOneProjectExpense(String id) {
        return projecHelptDao.getOneProjectExpense ( id );
    }

    @Transactional(readOnly = false)
    public int updateOneProjectExpense(PmsProjectExpense pmsProjectExpense) {
        return projecHelptDao.updateOneProjectExpense ( pmsProjectExpense );
    }

    @Transactional(readOnly = false)
    public int addOneProjectExpense(PmsProjectExpense pmsProjectExpense) {
        return projecHelptDao.addOneProjectExpense ( pmsProjectExpense );
    }

    @Transactional(readOnly = false)
    public int deleteOneProjectExpense(String id) {
        return projecHelptDao.deleteOneProjectExpense ( id );
    }

}
