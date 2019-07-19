package com.thinkgem.jeesite.modules.leave.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.leave.dao.LeaAndOveDao;
import com.thinkgem.jeesite.modules.leave.entity.ActivityLeave2;
import com.thinkgem.jeesite.modules.leave.entity.LeaAndOve;
import com.thinkgem.jeesite.modules.overtime.entity.OverTime;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaAndOveService extends CrudService<LeaAndOveDao, LeaAndOve> {

    @Autowired
    private LeaAndOveDao leaAndOveDao;

    public Page<OverTime> findOverTimePage(Page<OverTime> page, OverTime overTime) {
        overTime.setPage ( page );
        page.setList ( leaAndOveDao.findOverTimeList ( overTime ) );
        return page;
    }

    public Page<User> findUserPage(Page<User> page, User user) {
        user.setPage ( page );
        page.setList ( leaAndOveDao.findUserPage ( user ) );
        return page;
    }

    public OverTime getOneOverTime(String id) {
        return leaAndOveDao.getOneOverTime ( id );
    }

    public Page<ActivityLeave2> findleavePage(Page<ActivityLeave2> page, ActivityLeave2 leave) {
        leave.setPage ( page );
        page.setList ( leaAndOveDao.findleaveList ( leave ) );
        return page;
    }

    public ActivityLeave2 findOneLeave(String id) {
        return leaAndOveDao.findOneLeave ( id );
    }

    public String findUsername(String id) {
        return leaAndOveDao.findUsername ( id );
    }


}
