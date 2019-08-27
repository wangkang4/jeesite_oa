package com.thinkgem.jeesite.modules.overtime.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.overtime.dao.OverTimeDao;
import com.thinkgem.jeesite.modules.overtime.entity.DownloadOverTime;
import com.thinkgem.jeesite.modules.overtime.entity.OverTime;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class OverTimeService extends CrudService<OverTimeDao, OverTime> {

    @Autowired
    private ActTaskService actTaskService;

    @Autowired
    private OverTimeDao overTimeDao;

    @Autowired
    private SystemService systemService;

    public OverTime getByProcInsId(String procInsId) {
        return dao.getByProcInsId ( procInsId );
    }

    @Override
    public Page<OverTime> findPage(Page<OverTime> page, OverTime overTime) {
        overTime.setPage ( page );
        page.setList ( dao.findList ( overTime ) );
        return page;
    }

    //查询全公司的列表
    public Page<OverTime> findAllPage(Page<OverTime> page, OverTime overTime) {
        overTime.setPage ( page );
        page.setList ( dao.findAllList ( overTime ) );
        return page;
    }

    public String getOfficeNameByUserId(String id) {
        return dao.getOfficeNameByUserId ( id );
    }

    public Office getOfficeByUserId(String id) {
        return dao.getOfficeByUserId ( id );
    }

    /**
     * 保存或编辑申请信息
     */
    @Override
    @Transactional(readOnly = false)
    public void save(OverTime overTime) {
        if (StringUtils.isBlank ( overTime.getId () )) {
            overTime.preInsert ();
            overTime.setStatus ( "审核中" );
            dao.insert ( overTime );
            //启动部署成功的工作流
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "days", overTime.getDays () );
            /*获取当前登录人的上级的id*/
            String leaderId = UserUtils.getUser ().getLeader ();
            /*获取当前登录人的上级的姓名*/
            String leaderName = UserUtils.get ( leaderId ).getName ();
            /*通过id获取指定人的角色分配*/
            String roleStr = UserUtils.get ( leaderId ).getRoleNames ();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
            String[] roles = roleStr.split ( "," );//
            /*根据角色名获取用户名集合*/
            List<String> listb = UserUtils.getByRoleEnname ( "thd_beijing" );
            List<String> listz = UserUtils.getByRoleEnname ( "thd_general_manager" );
            //北京只有一个角色
            String enname = listb.get ( 0 );
            //总经理角色用户名
            String manaName = listz.get ( 0 );
            /*判断是否为这一角色的人*/
            Boolean b1 = Arrays.asList ( roles ).contains ( "桃花岛北京区负责人" );
            if (b1) {
                vars.put ( "userTask1", enname );
                vars.put ( "userTask2", manaName );
            } else {
                Boolean b2 = Arrays.asList ( roles ).contains ( "桃花岛办事处级别负责人" );
                Boolean b3 = Arrays.asList ( roles ).contains ( "桃花岛总裁" );
                if (b2 || b3) {
                    //只走一个流程节点
                    vars.put ( "userTask1", leaderName );
                } else {
                    vars.put ( "userTask1", leaderName );
                    /*获取上级的上级领导姓名*/
                    String leader_leader = UserUtils.get ( UserUtils.get ( leaderId ).getLeader () ).getName ();
                    vars.put ( "userTask2", leader_leader );
                }
            }
            actTaskService.startProcess ( ActUtils.THD_OVERTIME[0], ActUtils.THD_OVERTIME[1], overTime.getId (), overTime.getReason (), vars );

        } else {
            overTime.preUpdate ();
            dao.update ( overTime );
            if ("yes".equals ( overTime.getAct ().getFlag () )) {
                overTime.getAct ().setComment ( "[重申]" + overTime.getReason () );
                overTime.setStatus ( "审核中" );
                dao.update ( overTime );
            } else {
                overTime.getAct ().setComment ( "[销毁]" + overTime.getAct ().getComment () );
                overTime.setStatus ( "销毁" );
                dao.update ( overTime );
                String id = overTime.getId ();
                overTimeDao.delete ( id );
            }
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "rebut", "yes".equals ( overTime.getAct ().getFlag () ) ? "1" : "0" );
            String pid = overTime.getProcInsId ();
            //任务id
            String taskId = overTimeDao.getTaskIdByPId ( pid );
            actTaskService.complete ( taskId, overTime.getAct ().getProcInsId (), overTime.getAct ().getComment (), overTime.getReason (), vars );
        }
    }

    /**
     * 不同环节的审核内容保存
     */
    @Transactional(readOnly = false)
    public void auditSave(OverTime overTime) {
        //设置审核意见
        overTime.getAct ().setComment ( ("yes".equals ( overTime.getAct ().getFlag () ) ? "[同意]" : "[驳回]") + overTime.getAct ().getComment () );
        overTime.preUpdate ();
        //对不同环节的业务逻辑进行操作
        //String taskDefKey = overTime.getAct().getTaskDefKey();
        //获取当前用户ID
        String useId = UserUtils.getUser ().getId ();
        /*通过id获取指定人的角色分配*/
        String roleStr = UserUtils.get ( useId ).getRoleNames ();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
        String[] roles = roleStr.split ( "," );//
        /*判断是否为这一角色的人*/
        Boolean b1 = Arrays.asList ( roles ).contains ( "桃花岛部门级别负责人" );
        Boolean b2 = Arrays.asList ( roles ).contains ( "桃花岛北京区负责人" );
        overTime.setStatus ( "审核中" );
        overTime.setLeaderText ( overTime.getAct ().getComment () );
        if (b1 || b2) {
            if (checkApproveNo ( overTime.getLeaderText () )) {
                overTime.setStatus ( "驳回" );
            }
            dao.updatePrText ( overTime );
            //提交流程任务
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "agree", "yes".equals ( overTime.getAct ().getFlag () ) ? "1" : "0" );
            actTaskService.complete ( overTime.getAct ().getTaskId (), overTime.getAct ().getProcInsId (), overTime.getAct ().getComment (), vars );
        } else {
            if (checkApproveNo ( overTime.getLeaderText () )) {
                overTime.setStatus ( "驳回" );
            }
            if (checkApproveYes ( overTime.getLeaderText () )) {
                overTime.setStatus ( "审核通过" );
                String opinion = overTime.getLeaderText ();
                boolean bool = opinion != null && opinion.startsWith ( "[同意]" );
                if (bool) {
                    User user = overTime.getUser ();
                    double day = overTime.getDays ();
                    //查询总计的加班时间
                    double num = systemService.selectNumOverTimeById ( user );
                    //查询可用加班时间
                    double use = systemService.selectUseOverTimeById ( user );
                    //计算出新的总计加班时间
                    double numDays = num + day;
                    //计算出新的可用加班时间
                    double useDays = use + day;
                    user.setNumOverTimeDays ( numDays );
                    user.setUseOverTimeDays ( useDays );
                    systemService.updateUseOverTimeById ( user );
                    systemService.updateNumOverTimeById ( user );
                }
            }
            dao.updateLeaderText ( overTime );
            //提交流程任务
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "agree", "yes".equals ( overTime.getAct ().getFlag () ) ? "3" : "0" );
            actTaskService.complete ( overTime.getAct ().getTaskId (), overTime.getAct ().getProcInsId (), overTime.getAct ().getComment (), vars );

        }

    }

    private boolean checkApproveNo(String result) {
        if (result != null && result != "" && result.contains ( "驳回" )) {
            return true;
        }
        return false;
    }

    private boolean checkApproveYes(String result) {
        if (result != null && result != "" && result.contains ( "同意" )) {
            return true;
        }
        return false;
    }

    /**
     * 查询本月的加班状况
     *
     * @param year
     * @param month
     * @return
     */
    @Transactional(readOnly = false)
    public List<OverTime> monthAll(String year, String month) {
        return overTimeDao.monthAll ( year, month );
    }

    /**
     * @Description: 导出上个月加班数据列表
     * @author: gangzi
     * @params: [overTime]
     * @return: java.util.List<com.thinkgem.jeesite.modules.overtime.entity.DownloadOverTime>
     * @date: 2019/8/20
     * @exception:
     */
    public List<DownloadOverTime> downList(String year, String month){
        return overTimeDao.downList(year, month);
    }
}
