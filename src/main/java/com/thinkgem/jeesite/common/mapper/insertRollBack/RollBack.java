package com.thinkgem.jeesite.common.mapper.insertRollBack;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface RollBack {
    String selectTaskIdByProcinsId(String ProcinsId);

    void insertRollBack(@Param(value = "taskId") String taskId, @Param(value = "process") String process);

    List<String> selectRollBack();

    List<HistoryPROC> selectHistoryPROC(String procInstId);

    void withdraw(@Param(value = "tableName") String tableName, @Param(value = "id") String id);

    String selectProc(@Param(value = "tableName") String tableName, @Param(value = "id") String id);

    void deleteTaskByProc(String proc);
}
