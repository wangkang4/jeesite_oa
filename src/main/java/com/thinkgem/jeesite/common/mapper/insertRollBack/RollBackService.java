package com.thinkgem.jeesite.common.mapper.insertRollBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RollBackService {
    @Autowired
    private RollBack rollback;

    //会退的数据存入一个记录的表中
    @Transactional(readOnly = false)
    public void insertRollBack(String taskId, String process) {
        rollback.insertRollBack ( taskId, process );
    }

    public List<HistoryPROC> selectHistoryPROC(String procInstId) {
        return rollback.selectHistoryPROC ( procInstId );
    }

    @Transactional(readOnly = false)
    public void withdraw(String tableName, String id) {
        rollback.withdraw ( tableName, id );
        String proc = rollback.selectProc ( tableName, id );
        rollback.deleteTaskByProc ( proc );
    }
}
