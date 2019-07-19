package com.thinkgem.jeesite.modules.mobile.dailyrecord.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.dao.TCheckBackDao;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.TCheckBack;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCheckBackService extends CrudService<TCheckBackDao, TCheckBack> {

    @Autowired
    private TCheckBackDao tCheckBackDao;

    public List<TCheckBack> findRecondlist(Integer offset, int pageSize, String userid) {
        TCheckBack tCheckBack = new TCheckBack ();
        User user = new User ();
        user.setId ( userid );
        tCheckBack.setUser ( user );
        Page<TCheckBack> page = new Page<TCheckBack> ();

        page.setPageSize ( pageSize );

        page.setOffset ( offset );

        page.setMobilePage ( true );

        tCheckBack.setPage ( page );

        return tCheckBackDao.findRecondlist ( tCheckBack );
    }

    public User getUserone(String userid) {
        return tCheckBackDao.getUserone ( userid );
    }

    public List<TCheckBack> findgetsendlist(Integer offset, int pageSize, String userid) {
        TCheckBack tCheckBack = new TCheckBack ();
        User user = new User ();
        user.setId ( userid );
        tCheckBack.setUser ( user );
        Page<TCheckBack> page = new Page<TCheckBack> ();

        page.setPageSize ( pageSize );

        page.setOffset ( offset );

        page.setMobilePage ( true );

        tCheckBack.setPage ( page );

        return tCheckBackDao.findgetsendlist ( tCheckBack );
    }

}
