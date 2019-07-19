package com.thinkgem.jeesite.modules.invoice.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.invoice.dao.InvoiceApplyDao;
import com.thinkgem.jeesite.modules.invoice.entity.InvoiceApply;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceApplyService extends CrudService<InvoiceApplyDao, InvoiceApply> {

    @Autowired
    private ActTaskService actTaskService;
    @Autowired
    private InvoiceApplyDao invoiceApplyDao;

    @Transactional(readOnly = false)
    public Page<InvoiceApply> findPages(Page<InvoiceApply> page, InvoiceApply invoiceApply) {
        invoiceApply.setPage ( page );
        page.setList ( invoiceApplyDao.findList ( invoiceApply ) );
        return page;
    }

    @Transactional(readOnly = false)
    public Page<InvoiceApply> findPages2(Page<InvoiceApply> page, InvoiceApply invoiceApply) {
        invoiceApply.setPage ( page );

        if ("俞伶群".equals ( UserUtils.getUser ().getName () )
                || "李晓萌".equals ( UserUtils.getUser ().getName () )
                || "郭晓敏".equals ( UserUtils.getUser ().getName () )) {
            page.setList ( invoiceApplyDao.findAllList1 () );
        } else {
            page.setList ( invoiceApplyDao.findAllList ( invoiceApply.getUser ().getId () ) );
        }
        return page;
    }

    @Transactional(readOnly = false)
    public void save(InvoiceApply InvoiceApply) {
        if (StringUtils.isBlank ( InvoiceApply.getId () )) {
            InvoiceApply.preInsert ();
            dao.insert ( InvoiceApply );
            //启动流程
            startGet ( InvoiceApply );

        } else {//驳回列表
            InvoiceApply.preUpdate ();
            dao.update ( InvoiceApply );
            if ("yes".equals ( InvoiceApply.getAct ().getFlag () )) {
                InvoiceApply.getAct ().setComment ( "[重申]" + InvoiceApply.getTaxName () );
                InvoiceApply.setStatu ( "审核中" );
                dao.updateStatu ( InvoiceApply );
            } else {
                InvoiceApply.getAct ().setComment ( "[销毁]" + InvoiceApply.getAct ().getComment () );
                InvoiceApply.setStatu ( "销毁" );
                dao.updateStatu ( InvoiceApply );
                dao.delete ( InvoiceApply );
            }
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "rebut", "yes".equals ( InvoiceApply.getAct ().getFlag () ) ? "1" : "0" );
            String taskId = dao.selectTaskIdByProcinsId ( InvoiceApply.getAct ().getProcInsId () );
            actTaskService.complete ( taskId, InvoiceApply.getAct ().getProcInsId (),
                    InvoiceApply.getAct ().getComment (), InvoiceApply.getTaxName (), vars );
        }
    }

    public void startGet(InvoiceApply invoiceApply) {
        Map<String, Object> vars = new HashMap<String, Object> ();
        vars.put ( "total", invoiceApply.getTotal () );
        //节点1 审核人商务部长
        vars.put ( "userTask1", UserUtils.getByRoleEnname ( "thd_shangwu_fu" ).get ( 0 ) );
        //节点2 审核人财务总监
        vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_caiwuzongjian" ).get ( 0 ) );
        //节点3 审核人开票人
        vars.put ( "userTask3", UserUtils.getByRoleEnname ( "thd_chuna" ).get ( 0 ) );
        actTaskService.startProcess ( ActUtils.THD_TICKET[0], ActUtils.THD_TICKET[1], invoiceApply.getId (),
                invoiceApply.getTaxName (), vars );
    }

    @Transactional(readOnly = false)
    public void auditSave(InvoiceApply invoiceApply) {
        // 设置审核意见
        invoiceApply.getAct ().setComment (
                ("yes".equals ( invoiceApply.getAct ().getFlag () ) ? "[同意]" : "[驳回]") + invoiceApply.getAct ().getComment () );
        invoiceApply.preUpdate ();

        // 对不同环节的业务逻辑进行操作
        String taskDefKey = invoiceApply.getAct ().getTaskDefKey ();
        // 各个审核环节

        if ("userTask1".equals ( taskDefKey )) {
            invoiceApply.setOneText ( invoiceApply.getAct ().getComment () );
            dao.updateOneText ( invoiceApply );
        } else if ("userTask2".equals ( taskDefKey )) {
            invoiceApply.setProneText ( invoiceApply.getAct ().getComment () );
            dao.updateProneText ( invoiceApply );
        } else if ("userTask3".equals ( taskDefKey )) {
            invoiceApply.setPrtwoText ( invoiceApply.getAct ().getComment () );
            dao.updatePrtwoText ( invoiceApply );
        }
        if (!"yes".equals ( invoiceApply.getAct ().getFlag () )) {
            invoiceApply.setStatu ( "驳回" );
            dao.updateStatu ( invoiceApply );
        }
        if ("yes".equals ( invoiceApply.getAct ().getFlag () ) && "userTask3".equals ( taskDefKey )) {
            invoiceApply.setStatu ( "审核通过" );
            dao.updateStatu ( invoiceApply );
        }
        Map<String, Object> vars = Maps.newHashMap ();
        vars.put ( "agree", "yes".equals ( invoiceApply.getAct ().getFlag () ) ? "1" : "0" );
        actTaskService.complete ( invoiceApply.getAct ().getTaskId (), invoiceApply.getAct ().getProcInsId (),
                invoiceApply.getAct ().getComment (), vars );
    }

    public InvoiceApply getByProcInsId(String procInsId) {

        return dao.getByProcInsId ( procInsId );
    }

    @Transactional(readOnly = false)
    public void deleteInvoiceApply(String procInsId) {
        invoiceApplyDao.deleteInvoiceApply ( procInsId );

    }

    @Transactional(readOnly = false)
    public void deleteTask(String procInsId) {
        invoiceApplyDao.deleteTask ( procInsId );

    }

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param page
     * @param invoiceApply
     * @return
     */
    @Transactional(readOnly = false)
    public Page<InvoiceApply> findPage3(Page<InvoiceApply> page, InvoiceApply invoiceApply) {
        invoiceApply.setPage ( page );
        System.out.println ( UserUtils.getUser ().getOffice ().getArea ().getName () );
        page.setList ( dao.findList2 ( UserUtils.getUser ().getOffice ().getArea ().getName () ) );
        return page;
    }
}
