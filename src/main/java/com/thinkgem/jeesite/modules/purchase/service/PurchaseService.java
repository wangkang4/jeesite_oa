package com.thinkgem.jeesite.modules.purchase.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.purchase.dao.PurchaseDao;
import com.thinkgem.jeesite.modules.purchase.entity.Purchase;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

@Service
public class PurchaseService extends CrudService<PurchaseDao, Purchase> {
    /**
     * 注入DAO层属性
     */
    @Autowired
    private PurchaseDao purchaseDao;

    /**
     * 注入流程actTaskService属性
     */
    @Autowired
    private ActTaskService actTaskService;

    /**
     * 提交保存操作
     */
    @Transactional(readOnly = false)
    public void save(Purchase purchase) {
        if (StringUtils.isBlank ( purchase.getId () )) {// 提交申请
            purchase.preInsert ();
            purchase.setStatu ( "审核中" );
            dao.insert ( purchase );
            startGet ( purchase );
        } else {
            purchase.preUpdate ();
            dao.update ( purchase );
            if ("yes".equals ( purchase.getAct ().getFlag () )) {
                purchase.getAct ().setComment ( "[重申]" + purchase.getpName () );
                purchase.setStatu ( "审核中" );
                dao.updateStatu ( purchase );
            } else {
                purchase.getAct ().setComment ( "[销毁]" + purchase.getAct ().getComment () );
                purchase.setStatu ( "销毁" );
                dao.updateStatu ( purchase );
                dao.delete ( purchase );
            }
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "pass1", "yes".equals ( purchase.getAct ().getFlag () ) ? "4" : "0" );
            String taskId = dao.selectTaskIdByProcinsId ( purchase.getAct ().getProcInsId () );
            // 获得用户名
            String areaName = UserUtils.getUser ().getOffice ().getArea ().getName ();
            // 启动部署成功的工作流

            String userName = UserUtils.getUser ().getLoginName ();
            // 办事处负责人
            Boolean a = Arrays.asList ( ActUtils.ACT_EXA_DIRECTOR ).contains ( userName );
            // 部门领导
            Boolean b = Arrays.asList ( ActUtils.ACT_EXA_PURCHASE ).contains ( userName );

            if (a || "北京".equals ( areaName )) {// 如果是北京区的或者是办事处负责人
                /* 将流程路径走pass==1 */
                vars.put ( "pass", "1" );
            } else if (b) {// 如果是非北京的部门领导
                vars.put ( "pass", "2" );
            } else if (!a && !b) {// 如果是非北京的普通员工
                vars.put ( "pass", "3" );
            }
            actTaskService.complete ( taskId, purchase.getAct ().getProcInsId (), purchase.getAct ().getComment (),
                    purchase.getpName (), vars );
        }
    }

    /**
     * 插叙列表
     *
     * @param page
     * @param purchase
     * @return
     */
    @Transactional(readOnly = false)
    public Page<Purchase> findPages(Page<Purchase> page, Purchase purchase) {
        purchase.setPage ( page );
        page.setList ( purchaseDao.findList ( purchase ) );
        return page;
    }

    /**
     * 启动流程
     *
     * @param purchase
     */
    private void startGet(Purchase purchase) {
        Map<String, Object> vars = Maps.newHashMap ();
        vars.put ( "money", purchase.getMoney () );

        /**
         * @update Mr.dong 采购申请流程思路： 首先获取当前人的上级领导 1.如果直属上级是总裁 则：提交人--财务总监--各地行政
         *         2.如果直属上级是办事处级别负责人 则：提交人--直属上级--财务总监--各地行政 3.如果直属上级是部门级别负责人
         *         则：提交人--直属上级的上级--财务总监--各地行政
         */

        /** 获取当前人的直属上级 */
        String leaderId = UserUtils.getUser ().getLeader ();// 直属上级id
        String leaderName = UserUtils.get ( leaderId ).getName ();// 直属上级姓名
        /** 获取直属上级的角色 */
        String roleStr = UserUtils.get ( leaderId ).getRoleNames ();// 如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
        String[] roles = roleStr.split ( "," );//
        /** 判断是否为这一角色的人 */
        Boolean thd_a = Arrays.asList ( roles ).contains ( "桃花岛总裁" );
        Boolean thd_b = Arrays.asList ( roles ).contains ( "桃花岛办事处级别负责人" );
        Boolean thd_c = Arrays.asList ( roles ).contains ( "桃花岛部门级别负责人" );

        if (thd_a) {
            vars.put ( "userTask1", UserUtils.getByRoleEnname ( "thd_caiwuzongjian" ).get ( 0 ) );
            vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_general_manager" ).get ( 0 ) );
        } else if (thd_b) {
            vars.put ( "userTask1", leaderName );
            vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_caiwuzongjian" ).get ( 0 ) );
            vars.put ( "userTask3", UserUtils.getByRoleEnname ( "thd_general_manager" ).get ( 0 ) );
        } else if (thd_c) {
            /** 获取上级的上级领导姓名 */
            String leader_leader = UserUtils.get ( UserUtils.get ( leaderId ).getLeader () ).getName ();
            vars.put ( "userTask1", leader_leader );
            vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_caiwuzongjian" ).get ( 0 ) );
            vars.put ( "userTask3", UserUtils.getByRoleEnname ( "thd_general_manager" ).get ( 0 ) );
        } else {
            return;
        }
        actTaskService.startProcess ( ActUtils.THD_PURCHASE[0], ActUtils.THD_PURCHASE[1], purchase.getId (),
                purchase.getpName (), vars );
    }

    /**
     * 审核意见
     *
     * @param purchase
     */
    @Transactional(readOnly = false)
    public void auditSave(Purchase purchase) {
        // 设置审核意见
        purchase.getAct ().setComment (
                ("yes".equals ( purchase.getAct ().getFlag () ) ? "[同意]" : "[驳回]") + purchase.getAct ().getComment () );
        purchase.preUpdate ();

        /** 获取当前审核人的角色 */
        String roleStr = UserUtils.get ( UserUtils.getUser ().getId () ).getRoleNames ();// 如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
        String[] roles = roleStr.split ( "," );
        /** 判断是否为这一角色的人 */
        Boolean thd_a = Arrays.asList ( roles ).contains ( "桃花岛办事处级别负责人" );
        Boolean thd_b = Arrays.asList ( roles ).contains ( "桃花岛财务总监" );
        Boolean thd_c = Arrays.asList ( roles ).contains ( "桃花岛总经理" );
        Map<String, Object> vars = Maps.newHashMap ();

        /** 流程正常走向 同意则agree==1 */
        vars.put ( "agree", "yes".equals ( purchase.getAct ().getFlag () ) ? "1" : "0" );

        // 各个审核环节
        if (thd_a && !thd_b) {
            purchase.setPrtwoText ( purchase.getAct ().getComment () );
            dao.updatePrtwoText ( purchase );
        }
        if (thd_b) {
            purchase.setPrthreeText ( purchase.getAct ().getComment () );
            dao.updatePrthreeText ( purchase );
        }
        if (thd_c) {
            purchase.setPrfourText ( purchase.getAct ().getComment () );
            dao.updatePrfourText ( purchase );
        }

        if (!"yes".equals ( purchase.getAct ().getFlag () )) {
            purchase.setStatu ( "驳回" );
            dao.updateStatu ( purchase );
        }
        if ("yes".equals ( purchase.getAct ().getFlag () ) && thd_c) {
            purchase.setStatu ( "审核通过" );
            dao.updateStatu ( purchase );
            vars.put ( "agree", "yes".equals ( purchase.getAct ().getFlag () ) ? "3" : "0" );
        }
        actTaskService.complete ( purchase.getAct ().getTaskId (), purchase.getAct ().getProcInsId (),
                "采购申请-" + purchase.getAct ().getComment (), vars );

    }

    /**
     * 根据流程实例id获取数据
     *
     * @param procInsId
     * @return
     */
    public Purchase getByProcInsId(String procInsId) {

        return dao.getByProcInsId ( procInsId );
    }

    /**
     * 删除数据
     *
     * @param procInsId
     */
    @Transactional(readOnly = false)
    public void deletePurchase(String procInsId) {
        purchaseDao.deletePurchase ( procInsId );
    }

    /**
     * 删除流程数据
     *
     * @param procInsId
     */
    @Transactional(readOnly = false)
    public void deleteTask(String procInsId) {
        purchaseDao.deleteTask ( procInsId );
    }

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param page
     * @param purchase
     * @return
     */
    @Transactional(readOnly = false)
    public Page<Purchase> findPage2(Page<Purchase> page, Purchase purchase) {
        purchase.setPage ( page );
        System.out.println ( UserUtils.getUser ().getOffice ().getArea ().getName () );
        page.setList ( dao.findList2 ( UserUtils.getUser ().getOffice ().getArea ().getName () ) );
        return page;
    }

    /**
     * 财务人员查看员工申请列表
     *
     * @param page
     * @param purchase
     * @return
     */
    public Page<Purchase> findPage4(Page<Purchase> page, Purchase purchase) {
        purchase.setPage ( page );
        page.setList ( dao.findList4 ( purchase ) );
        return page;
    }
}
