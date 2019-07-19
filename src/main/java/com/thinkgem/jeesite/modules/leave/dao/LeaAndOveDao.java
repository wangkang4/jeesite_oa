package com.thinkgem.jeesite.modules.leave.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.leave.entity.ActivityLeave2;
import com.thinkgem.jeesite.modules.leave.entity.LeaAndOve;
import com.thinkgem.jeesite.modules.overtime.entity.OverTime;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

@MyBatisDao
public interface LeaAndOveDao extends CrudDao<LeaAndOve> {

    List<OverTime> findOverTimeList(OverTime overTime);

    List<User> findUserPage(User user);

    OverTime getOneOverTime(String id);

    List<ActivityLeave2> findleaveList(ActivityLeave2 leave);

    ActivityLeave2 findOneLeave(String id);

    String findUsername(String id);


}
