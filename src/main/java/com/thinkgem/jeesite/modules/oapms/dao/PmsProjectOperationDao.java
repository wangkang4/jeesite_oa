package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectOperation;

import java.util.List;

@MyBatisDao
public interface PmsProjectOperationDao extends CrudDao<PmsProjectOperation> {

    void insertOneOpertion(PmsProjectOperation pmsProjectOperation);

    List<PmsProjectOperation> findProjectOperationList(PmsProjectOperation pmsProjectOperation);

}
