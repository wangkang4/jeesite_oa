package com.thinkgem.jeesite.modules.invoice.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.invoice.entity.InvoiceApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface InvoiceApplyDao extends CrudDao<InvoiceApply> {


    //修改审核状态
    void updateStatu(InvoiceApply InvoiceApply);

    void updateOneText(InvoiceApply InvoiceApply);

    //财务总监审批
    void updateProneText(InvoiceApply InvoiceApply);

    //开票人意见
    void updatePrtwoText(InvoiceApply InvoiceApply);

    //通过流程实例Id查询任务id
    //String selectTaskIdByProcinsId(String ProcinsId);
    //通过流程实例ID得到Invoice实例
    InvoiceApply getByProcInsId(String procInsId);

    String selectTaskIdByProcinsId(String procInsId);

    List<InvoiceApply> findAllList(String uid);

    List<InvoiceApply> findAllList1();

    void deleteInvoiceApply(String procInsId);

    void deleteTask(String procInsId);

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param name
     * @return
     */
    List<InvoiceApply> findList2(@Param(value = "name") String name);
}
