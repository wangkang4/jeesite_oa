package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsPayment;

import java.util.List;

@MyBatisDao
public interface PmsPaymentDao extends CrudDao<PmsPayment> {

    //列表展示；
    List<PmsPayment> findList(PmsPayment pmsPayment);

    //根据id查找某条数据；
    PmsPayment selectById(String id);

    //删除；
    public void deletePms(String paymentId);
}
