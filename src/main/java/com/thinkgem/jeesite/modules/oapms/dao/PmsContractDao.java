package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsContract;

import java.util.List;

@MyBatisDao
public interface PmsContractDao extends CrudDao<PmsContract> {

    //查询所有数据列表
    List<PmsContract> findList(PmsContract pmsContract);

    //根据id查询某一条数据；
    PmsContract selectById(String id);

    public int deletePms(String id);

    String selectFileNameById(String attachmentAddress);
}
