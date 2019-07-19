package com.thinkgem.jeesite.modules.daily.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.daily.dao.TDailyDao;
import com.thinkgem.jeesite.modules.daily.entity.TDaily;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ShiLiangYu
 */
@Service
@Transactional(readOnly = true)
public class TDailyService extends CrudService<TDailyDao, TDaily> {
    @Autowired
    private TDailyDao tDailyDao;

    @Override
    public Page<TDaily> findPage(Page<TDaily> page, TDaily entity) {
        // TODO Auto-generated method stub
        return super.findPage ( page, entity );
    }

    @Override
    @Transactional(readOnly = false)
    public void save(TDaily entity) {
        // TODO Auto-generated method stub
        super.save ( entity );
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(TDaily entity) {
        // TODO Auto-generated method stub
        super.delete ( entity );
    }

    public TDaily getByTDaily(TDaily tDaily) {
        return tDailyDao.getByTDaily ( tDaily );
    }

    @Transactional(readOnly = false)
    public int update(TDaily tDaily) {
        return tDailyDao.update ( tDaily );
    }

    public List<TDaily> getMobileDaily(Integer offset, int pageSize, String userid) {
        TDaily tDaily = new TDaily ();
        User user = new User ();
        user.setId ( userid );
        tDaily.setUser ( user );
        Page<TDaily> page = new Page<TDaily> ();

        page.setPageSize ( pageSize );

        page.setOffset ( offset );

        page.setMobilePage ( true );

        tDaily.setPage ( page );
        return tDailyDao.getMobileDaily ( tDaily );
    }

    @Transactional(readOnly = false)
    public void inserone(TDaily tDaily) {
        tDailyDao.insert ( tDaily );
    }

    public String getofficeName(String str1) {
        return tDailyDao.getofficeName ( str1 );
    }

}
