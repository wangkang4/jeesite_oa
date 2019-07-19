package com.thinkgem.jeesite.modules.leave.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.leave.entity.ActivityLeave2;
import com.thinkgem.jeesite.modules.sys.entity.Office;

@MyBatisDao
public interface ActivityLeave2Dao extends CrudDao<ActivityLeave2> {

    String getOfficeNameByUserId(String id);

    Office getOfficeByUserId(String id);

    void updatePrText(ActivityLeave2 activityLeave2);

    void updateHrText(ActivityLeave2 activityLeave2);

    void updateLeaderText(ActivityLeave2 activityLeave2);

    void updateLeadertwoText(ActivityLeave2 activityLeave2);

    void updateLeaderthreeText(ActivityLeave2 activityLeave2);

    ActivityLeave2 getByProcInsId(String procInsId);

    //更新消除的请假天数
    void updateRemoveDays(ActivityLeave2 activityLeave2);

    //通过业务主表的流程节点id去查找流程表(Task)的id
    String getTaskIdByPId(String pid);

    int delete(String id);

    //设置状态码
    int updateStatus(String id);

    String findAttachNameByAttachAddress(String attachAddress);
}
