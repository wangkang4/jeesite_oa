package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.PmsProjectDao;
import com.thinkgem.jeesite.modules.oapms.entity.*;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service

public class PmsProjectService extends CrudService<PmsProjectDao, PmsProject> {

    @Autowired
    private PmsProjectDao pmsProjectDao;

    public List<PmsProject> findIdAndName() {
        return pmsProjectDao.findIdAndName ();
    }

    public Page<PmsProject> findPage(Page<PmsProject> page, PmsProject pmsProject) {
        pmsProject.setPage ( page );
        List<PmsProject> list = dao.findList ( pmsProject );
        if ("1".equals ( UserUtils.getUser ().getId () )) {
            page.setList ( list );
            return page;
        }
        List<PmsProject> slist = new ArrayList<PmsProject> ();
        for (int i = 0; i < list.size (); i++) {
            List<ProjectRelativer> prlist = pmsProjectDao.findAllprListByProject ( list.get ( i ).getProjectId () );
            for (ProjectRelativer pr : prlist) {
                if (UserUtils.getUser ().getId ().equals ( pr.getEmployees ().getId () )) {
                    slist.add ( list.get ( i ) );
                    break;
                } else {
                    continue;
                }
            }
        }
        page.setList ( slist );
        return page;
    }

    @Transactional(readOnly = false)
    public int inserteOneProject(PmsProject pmsProject) {
        return pmsProjectDao.inserteOneProject ( pmsProject );
    }

    @Transactional(readOnly = false)
    public int inserteRelativers(ProjectRelativer pr) {
        return pmsProjectDao.inserteRelativers ( pr );
    }

    @Transactional(readOnly = false)
    public int deleteOneProject(String id) {
        return pmsProjectDao.deleteOneProject ( id );
    }

    public PmsProject getOneProject(String id) {
        return pmsProjectDao.getOneProject ( id );
    }

    public List<ProjectRelativer> findPersonList(String id) {
        return pmsProjectDao.findPersonList ( id );
    }

    @Transactional(readOnly = false)
    public int updateOneProject(PmsProject pmsProject) {
        return pmsProjectDao.updateOneProject ( pmsProject );
    }

    @Transactional(readOnly = false)
    public void deleteAllRelativers(String id) {
        pmsProjectDao.deleteAllRelativers ( id );
    }

    public List<Customer> findCustomerList() {
        return pmsProjectDao.findCustomerList ();
    }

    public List<CustomerContact> findCustomerContactList(String id) {
        return pmsProjectDao.findCustomerContactList ( id );
    }

    public List<HighchartsEntity> selectIndustryAndY() {
        return pmsProjectDao.selectIndustryAndY ();
    }

    public List<HighchartsEntity> selectOfficeAndY() {
        return pmsProjectDao.selectOfficeAndY ();
    }

    public List<HighchartsEntity> selectAreaAndY() {
        return pmsProjectDao.selectAreaAndY ();
    }

    public List<HighchartsEntity> selectCustomerAndY() {
        return pmsProjectDao.selectCustomerAndY ();
    }

    public List<HighchartsEntity> selectSalerAndY() {
        return pmsProjectDao.selectSalerAndY ();
    }

    public List<HighchartsEntity> selectProducterAndY() {
        return pmsProjectDao.selectProducterAndY ();
    }

    public HighchartsEntity selectTimeAndY(String year, String month) {
        return pmsProjectDao.selectTimeAndY ( year, month );
    }

    public List<HighchartsEntity> selectStatusAndY() {
        return pmsProjectDao.selectStatusAndY ();
    }

    public List<HighchartsEntity> selectProductTypeAndY() {
        return pmsProjectDao.selectProductTypeAndY ();
    }

}
