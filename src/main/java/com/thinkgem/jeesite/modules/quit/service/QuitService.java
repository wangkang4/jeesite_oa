package com.thinkgem.jeesite.modules.quit.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.quit.dao.QuitDao;
import com.thinkgem.jeesite.modules.quit.entity.Quit;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

@Service
public class QuitService extends CrudService<QuitDao, Quit> {

    /**
     * 依赖注入dao层属性
     */
    @Autowired
    private QuitDao quitDao;

    /**
     * 依赖注入actTaskServic属性
     */
    @Autowired
    private ActTaskService actTaskService;

    /**
     * 表单提交/修改/销毁
     */
    @Transactional(readOnly = false)
    public void save(Quit quit) {
        if (StringUtils.isBlank ( quit.getId () )) {//提交申请
            quit.preInsert ();
            quit.setStatu ( "审核中" );
            dao.insert ( quit );
            startGet ( quit );
        } else {
            quit.preUpdate ();
            dao.update ( quit );
            if ("yes".equals ( quit.getAct ().getFlag () )) {
                quit.getAct ().setComment ( "[重申]" + quit.getUser ().getLoginName () + "-离职申请" );
                quit.setStatu ( "审核中" );
                dao.updateStatu ( quit );
            } else {
                quit.getAct ().setComment ( "[销毁]" + quit.getAct ().getComment () );
                quit.setStatu ( "销毁" );
                dao.updateStatu ( quit );
                dao.delete ( quit );
            }
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "rebut", "yes".equals ( quit.getAct ().getFlag () ) ? "1" : "0" );
            String taskId = dao.selectTaskIdByProcinsId ( quit.getAct ().getProcInsId () );
            actTaskService.complete ( taskId, quit.getAct ().getProcInsId (),
                    quit.getAct ().getComment (), quit.getUser ().getLoginName () + "-离职申请", vars );
        }
    }

    /**
     * 流程审核
     *
     * @param quit
     */
    @Transactional(readOnly = false)
    public void auditSave(Quit quit) {
        // 设置审核意见
        quit.getAct ().setComment (
                ("yes".equals ( quit.getAct ().getFlag () ) ? "[同意]" : "[驳回]") + quit.getAct ().getComment () );
        quit.preUpdate ();

        /**获取当前审核人的角色*/
        String roleStr = UserUtils.get ( UserUtils.getUser ().getId () ).getRoleNames ();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
        String[] roles = roleStr.split ( "," );
        /**判断是否为这一角色的人*/
        Boolean thd_a = Arrays.asList ( roles ).contains ( "桃花岛办事处级别负责人" );
        Boolean thd_b = Arrays.asList ( roles ).contains ( "桃花岛财务总监" );
        Boolean thd_c = Arrays.asList ( roles ).contains ( "桃花岛行政级别" );
        Boolean thd_d = Arrays.asList ( roles ).contains ( "桃花岛人事经理" );
        Boolean thd_e = Arrays.asList ( roles ).contains ( "桃花岛财务主管" );
        Boolean thd_f = Arrays.asList ( roles ).contains ( "桃花岛总裁" );
        Map<String, Object> vars = Maps.newHashMap ();

        // 对不同环节的业务逻辑进行操作
        String taskDefKey = quit.getAct ().getTaskDefKey ();

        // 各个审核环节
        /**办事处级别*/
        if (thd_a && !thd_b) {
            quit.setProneText ( quit.getAct ().getComment () );
            dao.updateProneText ( quit );
        }
        /**各地行政助理*/
        if (thd_c && !thd_d) {
            quit.setPrtwoText ( quit.getAct ().getComment () );
            dao.updatePrtwoText ( quit );
        }
        /**人事经理*/
        if (thd_d) {
            quit.setPrthreeText ( quit.getAct ().getComment () );
            dao.updatePrthreeText ( quit );
        }
        /**财务主管*/
        if (thd_e) {
            quit.setPrfourText ( quit.getAct ().getComment () );
            dao.updatePrfourText ( quit );
        }
        /**财务总监*/
        if (thd_b) {
            quit.setPrfiveText ( quit.getAct ().getComment () );
            dao.updatePrfiveText ( quit );
        }
        /**总裁*/
        if (thd_b) {
            quit.setPrsixText ( quit.getAct ().getComment () );
            dao.updatePrsixText ( quit );
        }
        if (!"yes".equals ( quit.getAct ().getFlag () )) {
            quit.setStatu ( "驳回" );
            dao.updateStatu ( quit );
        }
        /**正常流程走向 */
        vars.put ( "agree", "yes".equals ( quit.getAct ().getFlag () ) ? "1" : "0" );
        if ("yes".equals ( quit.getAct ().getFlag () ) && (thd_e || thd_b)) {
            quit.setStatu ( "审核通过" );
            dao.updateStatu ( quit );
            /**流程结束走向*/
            vars.put ( "agree", "yes".equals ( quit.getAct ().getFlag () ) ? "3" : "0" );
        }
        actTaskService.complete ( quit.getAct ().getTaskId (), quit.getAct ().getProcInsId (),
                quit.getAct ().getComment (), vars );

    }

    /**
     * 通过流程实例id获取信息
     *
     * @param procInsId
     * @return
     */
    public Quit getByProcInsId(String procInsId) {

        return dao.getByProcInsId ( procInsId );
    }

    /**
     * 流程执行步骤
     *
     * @param quit
     */
    private void startGet(Quit quit) {

        /**
         * @update Mr.dong
         * 离职流程思路：
         * 首先获取当前人的上级领导
         * 	1.如果直属上级是总裁
         * 	则：提交人——俞跃舒（总裁）——人事行政经理——财务总监
         * 	2.如果直属上级是办事处级别负责人
         * 	提交人——直属上级——各地行政助理——人事行政经理——财务主管
         * 	3.如果直属上级是部门级别负责人
         * 	提交人——直属上级的上级——各地行政助理——人事行政经理——财务主管
         */

        Map<String, Object> vars = Maps.newHashMap ();
        /**获取当前人的直属上级*/
        String leaderId = UserUtils.getUser ().getLeader ();//直属上级id
        String leaderName = UserUtils.get ( leaderId ).getName ();//直属上级姓名
        /**获取直属上级的角色*/
        String roleStr = UserUtils.get ( leaderId ).getRoleNames ();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
        String[] roles = roleStr.split ( "," );//
        /**判断是否为这一角色的人*/
        Boolean thd_a = Arrays.asList ( roles ).contains ( "桃花岛总裁" );
        Boolean thd_b = Arrays.asList ( roles ).contains ( "桃花岛办事处级别负责人" );
        Boolean thd_c = Arrays.asList ( roles ).contains ( "桃花岛部门级别负责人" );
        if (thd_a) {
            vars.put ( "userTask1", UserUtils.getByRoleEnname ( "CEO" ).get ( 0 ) );
            vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_renshi" ).get ( 0 ) );
            vars.put ( "userTask3", UserUtils.getByRoleEnname ( "thd_caiwuzongjian" ).get ( 0 ) );
        } else if (thd_b) {
            vars.put ( "userTask1", leaderName );
            if (Arrays.asList ( roles ).contains ( "桃花岛上海区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_shanghai_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles ).contains ( "桃花岛北京区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_beijing_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles ).contains ( "桃花岛合肥区负责人" ) || Arrays.asList ( roles ).contains ( "桃花岛执行董事" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_hefei_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles ).contains ( "桃花岛杭州区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_hangzhou_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles ).contains ( "桃花岛济南区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_jinan_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles ).contains ( "桃花岛福州区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_fuzhou_xingzheng" ).get ( 0 ) );
            } else {
                return;
            }
            vars.put ( "userTask3", UserUtils.getByRoleEnname ( "thd_renshi" ).get ( 0 ) );
            vars.put ( "userTask4", UserUtils.getByRoleEnname ( "thd_caiwuzhuguan" ).get ( 0 ) );
        } else if (thd_c) {
            /**获取上级的上级领导姓名*/
            String leader_leader = UserUtils.get ( UserUtils.get ( leaderId ).getLeader () ).getName ();
            /**获取上级的上级领导角色*/
            String roleStr_leader = UserUtils.get ( UserUtils.get ( UserUtils.get ( leaderId ).getLeader () ).getId () ).getRoleNames ();
            String[] roles_leader = roleStr_leader.split ( "," );
            vars.put ( "userTask1", leader_leader );
            if (Arrays.asList ( roles_leader ).contains ( "桃花岛上海区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_shanghai_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles_leader ).contains ( "桃花岛北京区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_beijing_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles_leader ).contains ( "桃花岛合肥区负责人" ) || Arrays.asList ( roles_leader ).contains ( "桃花岛执行董事" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_hefei_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles_leader ).contains ( "桃花岛杭州区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_hangzhou_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles_leader ).contains ( "桃花岛济南区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_jinan_xingzheng" ).get ( 0 ) );
            } else if (Arrays.asList ( roles_leader ).contains ( "桃花岛福州区负责人" )) {
                vars.put ( "userTask2", UserUtils.getByRoleEnname ( "thd_fuzhou_xingzheng" ).get ( 0 ) );
            } else {
                return;
            }
            vars.put ( "userTask3", UserUtils.getByRoleEnname ( "thd_renshi" ).get ( 0 ) );
            vars.put ( "userTask4", UserUtils.getByRoleEnname ( "thd_caiwuzhuguan" ).get ( 0 ) );
        }

        actTaskService.startProcess ( ActUtils.THD_QUIT[0], ActUtils.THD_QUIT[1], quit.getId (),
                quit.getUser ().getLoginName () + "-离职申请", vars );
    }

    /**
     * 查询提交人列表页
     *
     * @param page
     * @param quit
     * @return
     */
    @Transactional(readOnly = false)
    public Page<Quit> findPages(Page<Quit> page, Quit quit) {
        quit.setPage ( page );
        page.setList ( quitDao.findList ( quit ) );
        return page;
    }

    /**
     * 查询员工列表页
     *
     * @param page
     * @param quit
     * @return
     */
    @Transactional(readOnly = false)
    public Page<Quit> findPages2(Page<Quit> page, Quit quit) {
        quit.setPage ( page );

        if ("李国强".equals ( UserUtils.getUser ().getLoginName () )
                || "俞伶群".equals ( UserUtils.getUser ().getLoginName () )
                || "郭晓敏".equals ( UserUtils.getUser ().getLoginName () )
                || "方娜".equals ( UserUtils.getUser ().getLoginName () )) {
            page.setList ( quitDao.findAllList1 () );
        } else if ("俞跃舒".equals ( UserUtils.getUser ().getLoginName () )) {
            page.setList ( quitDao.findAllList () );
        }
        return page;
    }

    /**
     * 通过流程实例id删除对应数据
     *
     * @param procInsId
     */
    @Transactional(readOnly = false)
    public void deleteQuit(String procInsId) {
        quitDao.deleteQuit ( procInsId );

    }

    /**
     * 通过流程实例id删除对应流程数据
     *
     * @param procInsId
     */
    @Transactional(readOnly = false)
    public void deleteTask(String procInsId) {
        quitDao.deleteTask ( procInsId );

    }

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param page
     * @param quit
     * @return
     */
    @Transactional(readOnly = false)
    public Page<Quit> findPage3(Page<Quit> page, Quit quit) {
        quit.setPage ( page );
        System.out.println ( UserUtils.getUser ().getOffice ().getArea ().getName () );
        page.setList ( dao.findList2 ( UserUtils.getUser ().getOffice ().getArea ().getName () ) );
        return page;
    }

    /**
     * 财务商务人员查看员工离职信息列表
     *
     * @param page
     * @param quit
     * @return
     */
    @Transactional(readOnly = false)
    public Page<Quit> findPage4(Page<Quit> page, Quit quit) {
        quit.setPage ( page );
        System.out.println ( UserUtils.getUser ().getOffice ().getArea ().getName () );
        page.setList ( dao.findList4 ( UserUtils.getUser ().getOffice ().getArea ().getName () ) );
        return page;
    }

}
