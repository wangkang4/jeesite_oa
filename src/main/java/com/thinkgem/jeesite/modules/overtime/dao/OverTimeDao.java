package com.thinkgem.jeesite.modules.overtime.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.overtime.entity.DownloadOverTime;
import com.thinkgem.jeesite.modules.overtime.entity.OverTime;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface OverTimeDao extends CrudDao<OverTime> {

    String getOfficeNameByUserId(String id);

    Office getOfficeByUserId(String id);

    void updatePrText(OverTime overTime);

    void updateHrText(OverTime overTime);

    void updateLeaderText(OverTime overTime);

    void updateLeadertwoText(OverTime overTime);

    OverTime getByProcInsId(String procInsId);

    //删除数据
    int delete(String id);

    //通过实体类型的id去任务主表里查找taskId
    String getTaskIdByPId(String pid);

    List<OverTime> monthAll(String year, String month);

    List<DownloadOverTime> downList(String year, String month, @Param("userId1") String userId1);
}
