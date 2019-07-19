package com.thinkgem.jeesite.modules.newTrends.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.newTrends.dao.NewTrendsDao;
import com.thinkgem.jeesite.modules.newTrends.entity.NewTrends;
import com.thinkgem.jeesite.modules.newTrends.entity.NewTrendsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewTrendsService extends CrudService<NewTrendsDao, NewTrends> {
    @Autowired
    private NewTrendsDao newTrendsDao;

    @Transactional(readOnly = false)
    public Page<NewTrends> findPages(Page<NewTrends> page, NewTrends newTrends) {
        newTrends.setPage ( page );
        page.setList ( newTrendsDao.findList ( newTrends ) );
        return page;
    }

    @Transactional(readOnly = false)
    public Page<NewTrends> findPage2(Page<NewTrends> page, NewTrends newTrends) {
        newTrends.setPage ( page );
        page.setList ( newTrendsDao.findList2 ( newTrends ) );
        return page;
    }

    @Transactional(readOnly = false)
    public void save(NewTrends newTrends) {
        if (StringUtils.isBlank ( newTrends.getId () )) {
            newTrends.preInsert ();
            dao.insert ( newTrends );
        } else {
            newTrends.preUpdate ();
            dao.update ( newTrends );
        }

    }

    @Transactional(readOnly = false)
    public void saveNewText(NewTrendsText newText) {
        newTrendsDao.saveNewText ( newText );

    }

    public List<String> findText(String text) {
        return newTrendsDao.findText ( text );
    }

    @Transactional(readOnly = false)
    public void deleteNewText(String id) {
        newTrendsDao.deleteNewText ( id );

    }

}
